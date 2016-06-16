Ext.require([
    'GeoExt.panel.Map',
    'GeoExt.data.MapfishPrintProvider',
    'GeoExt.panel.PrintMap'
]);


OpenLayers.ProxyHost = "./proxy?targetURL=";

var curShp;
var retlayer;
var mapPanel;
var searchPanel;
var hilightLayer;
var layerArr = [];
var layerVectorArr = [];
var selectControl;
var polygonLayer;
var tmplayers;

var id2map = [
    {"id": 350, "base": "A", "shp": "a0", tag: "tuocun0"},
    {"id": 10, "base": "A", "shp": "a1", tag: "tuocun1"},
    {"id": 11, "base": "A", "shp": "a2", tag: "tuocun2"},
    {"id": 12, "base": "A", "shp": "a3", tag: "tuocun3"},
    {"id": 13, "base": "A", "shp": "a4", tag: "tuocun4"},
    {"id": 14, "base": "A", "shp": "a5", tag: "tuocun5"},
    {"id": 303, "base": "B", "shp": "b0", tag: "youcun0"},
    {"id": 18, "base": "B", "shp": "b1", tag: "youcun1"},
    {"id": 19, "base": "B", "shp": "b2", tag: "youcun2"},
    {"id": 20, "base": "B", "shp": "b3", tag: "youcun3"},
    {"id": 21, "base": "B", "shp": "b4", tag: "youcun4"},
    {"id": 22, "base": "B", "shp": "b5", tag: "youcun5"},
    {"id": 23, "base": "B", "shp": "b6", tag: "youcun6"},
    {"id": 24, "base": "B", "shp": "b7", tag: "youcun7"},
    {"id": 26, "base": "C", "shp": "d0", tag: "ykk"},
    {"id": 29, "base": "D", "shp": "c0", tag: "aznbz"},
    {"id": 472, "base": "E", "shp": "e0", tag: "kelakule_shp01"},
    {"id": 481, "base": "E", "shp": "e1", tag: "kelakule_shp01"},
    {"id": 482, "base": "E", "shp": "e2", tag: "kelakule_shp02"},
    {"id": 483, "base": "E", "shp": "e3", tag: "kelakule_shp03"},
];


var vector_style = new OpenLayers.Style({
    'fillOpacity': .4,
    'strokeColor': '#aaee77',
    'strokeWidth': 3,
    'pointRadius': 8
});
var vector_style_select = new OpenLayers.Style({
    'fillOpacity': .9,
    'strokeColor': '#aaee77',
    'strokeWidth': 3,
    'pointRadius': 8
});
var vector_style_map = new OpenLayers.StyleMap({
    'default': vector_style,
    'select': vector_style_select
});

layerArr = new Array();
function loadLayers(filepath)
{

    OpenLayers.Request.GET({
        url: "jsonServlet?filepath=" + filepath,
        params: {
            num: 100
        },
        success: function (response) {
            layerArr = new Array();
            var format = new OpenLayers.Format.JSON();
            var layers = format.read(response.responseText);
            tmplayers = layers;
            for (var i = 0; i < layers.length; i++) {
                layerArr[i] = new OpenLayers.Layer.WMS(layers[i].name, layers[i].url,
                        {
                            layers: layers[i].params.layers,
                            transparent: layers[i].params.transparent
                        }, {
                    isBaseLayer: layers[i].options.isBaseLayer,
                    buffer: layers[i].options.buffer,
                    visibility: layers[i].options.visibility,
                    projection: layers[i].options.projection,
                    maxExtent: new OpenLayers.Bounds(
                            layers[i].options.maxExtent.left, layers[i].options.maxExtent.bottom,
                            layers[i].options.maxExtent.right, layers[i].options.maxExtent.top
                            )
                });
            }
        },
        failure: function (response) {
            alert("Sorry, there was an error requesting data !!!");
        }
    });
}

layerVectorArr = new Array();
function loadVectorLayers(filepath)
{
    OpenLayers.Request.GET({
        url: "jsonServlet?filepath=" + filepath,
        params: {
            num: 1000
        },
        success: function (response) {
            var format = new OpenLayers.Format.JSON();

            var layers = format.read(response.responseText);
            for (var i = 0; i < layers.length; i++) {
                var layer;
                layer = new OpenLayers.Layer.Vector(layers[i].name, {
                    projection: new OpenLayers.Projection('EPSG:2351'),
                    protocol: new OpenLayers.Protocol.HTTP({
                        url: layers[i].url,
                        format: new OpenLayers.Format.GML({
                            extractAttributes: true
                        })
                    }),
                    strategies: [new OpenLayers.Strategy.Fixed()],
                    styleMap: vector_style_map,
                    eventListeners: {
                        featuresadded: function (event) {
//                             console.log(this.getDataExtent());
                            mapPanel = Ext.getCmp("mapPanelId");
                            mapPanel.map.zoomToExtent(this.getDataExtent());
                        }
                    }
                }
                );
                layerVectorArr.push(layer);
            }
            // =============== notify(): ============== 
        },
        failure: function (response) {
            alert("Sorry, there was an error requesting data !!!");
        }
    });
}

function findLayerByName(name)
{
    for (var i = 0; i < layerArr.length; i++)
    {
        if (name == layerArr[i].name)
        {
            return layerArr[i];//retlayer; 
            break;
        }
    }
}

function findLayerVectorByName(name)
{
    for (var i = 0; i < layerVectorArr.length; i++)
    {
        if (name == layerVectorArr[i].name)
        {
            return layerVectorArr[i];//retlayer; 
            break;
        }
    }
}

function featureSelected(feature)
{
    var content =
            "<table border='1' cellpadding='5' cellspacing='0' width='300' height='200'>"
            + "<tr>"
            + "<td>名字</td><td>" + feature.attributes.mingzi + "</td>"
            + "</tr>"
            + "<tr>"
            + "<td>地块编码</td><td>" + feature.attributes.DKBM + "</td>"
            + "</tr>"
            + "<tr>"
            + "<td>面积</td><td>" + feature.attributes.SCMJ + "</td>"
            + "</tr>"
            + "<tr>"
            + "<td>承包方名称</td><td>" + feature.attributes.CBFMC + "</td>"
            + "</tr>"
            + "</table>";

    popupOpts = Ext.apply({
        title: '详情',
        location: feature,
//                                        width: 200,
        html: content,
        maximizable: true,
        collapsible: true,
        anchorPosition: 'auto',
        alwaysOnTop: true
    }, null);
    var popup = Ext.create('GeoExt.window.Popup', popupOpts);
    popup.on({
        close: function () {
            if (OpenLayers.Util.indexOf(feature.layer.selectedFeatures,
                    feature) > -1) {
                selectControl.unselect(feature);
            }
        }
    });
    feature.popup = popup;
    popup.show();

}

function featureUnSelected(feature)
{
//  mapPanel.map.removePopup(feature.popup);
    feature.popup.destroy();
    feature.popup = null;
}

var txtCBFMC = Ext.create('Ext.form.field.Text', {
    xtype: 'textfield',
    fieldLabel: '户主姓名',
    width: 250,
    name: 'CBFMC'
});

var btnSearch = Ext.create('Ext.button.Button', {
    text: 'go',
    bodyPadding: 10,
    enableToggle: true,
    handler: function () {

        mapPanel = Ext.getCmp("mapPanelId");

        if (hilightLayer)
        {
            mapPanel.map.removeLayer(hilightLayer);
        }
        hilightLayer = new OpenLayers.Layer.Vector(curShp, {
            protocol: new OpenLayers.Protocol.WFS({
                version: "1.0.0",
                url: "/geoserver/cite/wfs",
                featureType: curShp,
                featureNS: "http://www.opengeospatial.net/cite"
            }),
            filter: new OpenLayers.Filter.Comparison({//比较操作符
                type: OpenLayers.Filter.Comparison.LIKE, //精确查询
                property: "cite:CBFMC",
                value: "*" + txtCBFMC.value + "*"
            }),
            strategies: [new OpenLayers.Strategy.Fixed()],
            eventListeners: {
                featuresadded: function (event) {
//                             console.log(this.getDataExtent());
                    mapPanel = Ext.getCmp("mapPanelId");
                    mapPanel.map.zoomToExtent(this.getDataExtent());
                }
            }
        });
        //alert(curShp);
        mapPanel.map.addLayer(hilightLayer);
    }
});

var printProvider = Ext.create('GeoExt.data.MapfishPrintProvider', {
    id: "printProvider",
    method: "GET", //"POST", //recommended for production use
    capabilities: printCapabilities, // provide url instead for lazy loading
    customParams: {
        mapTitle: "当前视图",
        comment: ""
    }
});

var btnScreenshot = Ext.create('Ext.button.Button', {
    text: '保存截图',
    bodyPadding: 10,
    enableToggle: true,
    handler: function () {
        mapPanel = Ext.getCmp("mapPanelId");
        var layers=mapPanel.map.layers[0].params.LAYERS;
        var extent=mapPanel.map.getExtent();
        var width=500;
        var height=300;
        console.log("layers="+layers);
        console.log("extent="+extent);
        
        window.location.href = "./toWord.jsp?host=localhost&layers="+layers+"&bbox="+extent+"&width="+width+"&height="+height;
    }
});

Ext.define("core.map.controller.MapController", {
    extend: "Ext.app.Controller",
    init: function () {

        loadLayers('/data/layers.json');
        loadVectorLayers('/data/layers_vector.json');

        mapPanel = Ext.getCmp("mapPanelId");
        searchPanel = Ext.getCmp("searchPanel");

        if (searchPanel.items.length == 0)
        {
            searchPanel.items.add(txtCBFMC);
            searchPanel.items.add(btnSearch);
//            searchPanel.items.add(btnScreenshot);
        }

        var self = this;
        this.control({
            "mapLayout": {
                beforeshow: function (layout, opt) {
                }
            },
            "orgTreeMap": {
                itemclick: function (tree, record, item, index, e, eOpts) {
                    while (mapPanel.map.layers.length > 0) {
                        mapPanel.map.removeLayer(mapPanel.map.layers[0]);
                    }

                    for (var i = 0; i < id2map.length; i++) {
                        if (record.get('id') == id2map[i].id) {

                            curShp = id2map[i].tag;
                            mapPanel.map.addLayer(findLayerByName(id2map[i].base));
                            mapPanel.map.addLayer(findLayerByName(id2map[i].shp));
                            polygonLayer = findLayerVectorByName(id2map[i].shp);

                            mapPanel.map.addLayer(polygonLayer);


                            if (selectControl) {
                                mapPanel.map.removeControl(selectControl);
                            }

                            selectControl = new OpenLayers.Control.SelectFeature(polygonLayer, {
                                toggle: true,
                                onSelect: featureSelected,
                                onUnselect: featureUnSelected
                            });
                            mapPanel.map.addControl(selectControl);
                            selectControl.activate();
                        }
                    }
                }
            }
        });
    },
    views: [
        "core.map.view.MapLayout",
        "core.map.view.OrgTreeMap"
    ],
    stores: ["core.map.store.OrgStore"],
    models: []
});