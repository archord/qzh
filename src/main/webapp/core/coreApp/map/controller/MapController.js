Ext.require([
    'GeoExt.data.MapfishPrintProvider',
    'GeoExt.panel.PrintMap'
]);


OpenLayers.ProxyHost = "./proxy?targetURL=";

var layerArr;
var curShp;
var retlayer;
var mapPanel;

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
    {"id": 24, "base": "B", "shp": "b7", tag: "youcun7"}
];


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


var hilightLayer;

var txtCBFMC = Ext.create('Ext.form.field.Text', {
    xtype: 'textfield',
    fieldLabel: '所有权人姓名',
    width: 250,
    name: 'CBFMC'
});

var btnSearch = Ext.create('Ext.button.Button', {
    text: 'go',
    bodyPadding: 10,
    enableToggle: true,
    handler: function () {

         mapPanel = Ext.getCmp("mapPanelId");
//        if (hilightLayer)
//        {
//            mapPanel.map.removeLayer(hilightLayer);
//        }
        hilightLayer = new OpenLayers.Layer.Vector(curShp, {
            protocol: new OpenLayers.Protocol.WFS({
                version: "1.0.0",
                url: "http://localhost:8081/geoserver/cite/wfs",
                featureType: curShp,
                featureNS: "http://www.opengeospatial.net/cite"
            }),
            filter: new OpenLayers.Filter.Comparison({//比较操作符
                type: OpenLayers.Filter.Comparison.LIKE, //精确查询
                property: "cite:CBFMC",
                value: "*" + txtCBFMC.value + "*"
            }),
            strategies: [new OpenLayers.Strategy.Fixed()]
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

//alert(printProvider);
//var printExtent = Ext.create('GeoExt.plugins.PrintExtent', {
//    printProvider: Ext.create('GeoExt.data.MapfishPrintProvider', {
//    id: "printProvider",
//    method: "GET", //"POST", //recommended for production use
//    capabilities: printCapabilities, // provide url instead for lazy loading
//    customParams: {
//        mapTitle: "当前视图",
//        comment: ""
//    }
//})
//});


var btnScreenshot = Ext.create('Ext.button.Button', {
    text: '保存截图',
    bodyPadding: 10,
    enableToggle: true,
    handler: function () {
        var printPage = Ext.create('GeoExt.data.PrintPage', {
            printProvider: printProvider
        });
        printPage.fit(mapPanel, false);
        printProvider.print(mapPanel, printPage);

//        mapPanel.plugins[0].print();
    }
});





Ext.define("core.map.controller.MapController", {
    extend: "Ext.app.Controller",
    init: function () {
        var filepath = '/data/layers.json';
        loadMapParameter(filepath);
        mapPanel = Ext.getCmp("mapPanelId");
        searchPanel = Ext.getCmp("searchPanel");
        searchPanel.items.add(txtCBFMC);
        searchPanel.items.add(btnSearch);
        searchPanel.items.add(btnScreenshot);
        
//        mapPanel.plugins[0].addPage();

        var self = this;
        this.control({
            "mapLayout": {
                beforeshow: function (layout, opt) {
                }
            },
            "orgTreeMap": {
                itemclick: function (tree, record, item, index, e, eOpts) {
//                    console.log(record.get('id'));
//                    console.log(record.get('orgName'));
//                    console.log(item);

                    while (mapPanel.map.layers.length > 0) {
                        mapPanel.map.removeLayer(mapPanel.map.layers[0]);
                    }
                    for (var i = 0; i < id2map.length; i++) {
                        if (record.get('id') == id2map[i].id) {
                            mapPanel.map.addLayer(findLayerByName(id2map[i].base));
                            mapPanel.map.addLayer(findLayerByName(id2map[i].shp));
                            curShp = id2map[i].tag;
                        }
                    }
                    
                    
                }
            }
        });

        function loadMapParameter(nodeId) {
            var filepath = '/data/layers.json';
            OpenLayers.Request.GET({
                url: "jsonServlet?filepath=" + filepath,
                params: {
                    num: 100
                },
                success: function (response) {
                    layerArr = new Array();
                    var format = new OpenLayers.Format.JSON();
                    var layers = format.read(response.responseText);
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
    },
    views: [
        "core.map.view.MapLayout",
        "core.map.view.OrgTreeMap"
    ],
    stores: ["core.map.store.OrgStore"],
    models: []
});