/*
 *
 */

Ext.require([
  'Ext.container.Viewport',
  'Ext.layout.container.Border',
  'GeoExt.tree.Panel',
  'Ext.tree.plugin.TreeViewDragDrop',
  'GeoExt.panel.Map',
  'GeoExt.tree.OverlayLayerContainer',
  'GeoExt.tree.BaseLayerContainer',
  'GeoExt.data.LayerTreeModel',
  'GeoExt.tree.View',
  'GeoExt.tree.Column'
]);

/**********************************************************************/
OpenLayers.ProxyHost = "./proxy?targetURL=";

var mapPanel, tree;
var layerArr, id2map;

id2map = [
  {"id": 1, "base": "A", "shp": "a0"},
  {"id": 11, "base": "A", "shp": "a1"},
  {"id": 2, "base": "B", "shp": "b0"},
  {"id": 21, "base": "B", "shp": "b1"},
];

// 根据文件路径加载图层    
function loadLayers(filepath)
{

  layerArr = new Array();

  OpenLayers.Request.GET({
    url: "jsonServlet?filepath=" + filepath,
    params: {
      num: 100
    },
    success: function(response) {
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
      // =============== notify(): ============== 
    },
    failure: function(response) {
      alert("Sorry, there was an error requesting data !!!");
    }
  });
}


var retlayer;
function findLayerByName(name)
{
  for (var i = 0; i < layerArr.length; i++)
  {
    if (name === layerArr[i].name)
    {
      return layerArr[i];//retlayer; 
      break;
    }
  }

}
/**********************************************************************/




Ext.application({
  name: 'Tree',
  launch: function() {
    // create a map panel with some layers that we will show in our layer tree
    // below.
    mapPanel = Ext.create('GeoExt.panel.Map', {
      border: true,
      region: "center",
      map: {allOverlays: false},
    });

    loadLayers('data/layers.json');

    var store = Ext.create('Ext.data.TreeStore', {
      model: 'GeoExt.data.LayerTreeModel',
      root: {
        expanded: true,
        children: [{
            id: 1,
            text: '托万克喀拉喀勒村',
            expanded: true,
            children: [
              {id: 11, text: '1小队', leaf: true},
              {id: 12, text: '2小队', leaf: true},
              {id: 13, text: '3小队', leaf: true},
              {id: 14, text: '4小队', leaf: true},
              {id: 15, text: '5小队', leaf: true}
            ]
          }, {
            id: 2,
            text: '尤喀克喀拉喀勒村',
            expanded: true,
            children: [
              {id: 21, text: '1小队', leaf: true},
              {id: 22, text: '2小队', leaf: true},
              {id: 23, text: '3小队', leaf: true},
              {id: 24, text: '4小队', leaf: true},
              {id: 25, text: '5小队', leaf: true},
              {id: 26, text: '6小队', leaf: true},
              {id: 27, text: '7小队', leaf: true}
            ]
          }]
      }
    });

        // 弹出层显示点击的区域元素的属性
        var featureInfo = new OpenLayers.Control.WMSGetFeatureInfo({
            url: 'http://192.168.13.23:8081/geoserver/cite/wms',
            title: 'Identify features by clicking',
            //infoFormat: 'application/gml+xml',
            //format: new OpenLayers.Format.GML,
            queryVisible: true,
            output: 'features',
            eventListeners: {
                "getfeatureinfo": function (event) {
                    var feature = event;
                    mapPanel.map.addPopup(new OpenLayers.Popup.FramedCloud(
                            "chicken",
                            mapPanel.map.getLonLatFromPixel(event.xy),
                            null,
                            event.text,
                            null,
                            true
                            ));
                }
            }
        });
        mapPanel.map.addControl(featureInfo);
        featureInfo.activate();

    // create the tree with the configuration from above
    tree = Ext.create('GeoExt.tree.Panel', {
      border: true,
      region: "west",
      title: "Layers",
      width: 250,
      split: true,
      collapsible: true,
      collapseMode: "mini",
      autoScroll: true,
      store: store,
      rootVisible: false,
      lines: false,
      listeners: {
        afterrender: function(node) {
          tree.expandAll(); //展开树     
        },
        itemclick: function(view, record, item, index, e, eOpts) {
          console.log(record.get('text'));
          while (mapPanel.map.layers.length > 0) {
            mapPanel.map.removeLayer(mapPanel.map.layers[0]);
          }
          for (var i = 0; i < id2map.length; i++) {
            if (record.get('id') == id2map[i].id) {
              mapPanel.map.addLayer(findLayerByName(id2map[i].base));
              mapPanel.map.addLayer(findLayerByName(id2map[i].shp));
              console.log(findLayerByName(id2map[i].base));
              console.log(findLayerByName(id2map[i].shp));
            }
          }
        }
      }

    });

    Ext.create('Ext.Viewport', {
      layout: "fit",
      hideBorders: true,
      items: {
        layout: "border",
        deferredRender: false,
        items: [mapPanel, tree]
      }
    });

  }
});