Ext.define("core.map.controller.MapController", {
//	mixins:{
//		gridUtils:"core.map.utils.GridUtils"
//	},
//	
  extend: "Ext.app.Controller",
  init: function() {
    var self = this;
    this.control({
      /**showbefor*/
      "mapLayout": {
        beforeshow: function(layout, opt) {
          //alert(layout.getXType());
//          var grid = layout.down("product_grid");
//          var store = grid.getStore();
//          store.filter("id", 0);
        }
      },
      "orgTreeMap": {
        itemclick: function(tree, record, item, index, e, eOpts) {
          var nodeId = record.raw.orgId;
          //console.log(nodeId);
          loadMapParameter(nodeId);
        }
      }

    });

    function loadMapParameter(nodeId) {

      var filepath = 'data/layers.json';
      OpenLayers.Request.GET({
        url: "jsonServlet?filepath=" + filepath,
        params: {
          num: 100
        },
        success: function(response) {
          var layerArr = new Array();
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

          var mapPanel = Ext.getCmp("mapPanelId");
          //console.log(mapPanel);
          mapPanel.map.addLayer(layerArr[0]);
          mapPanel.map.addLayer(layerArr[1]);

          // 弹出层显示点击的区域元素的属性
          var featureInfo = new OpenLayers.Control.WMSGetFeatureInfo({
            url: '/geoserver/cite/wms',
            title: 'Identify features by clicking',
            //infoFormat: 'application/gml+xml',
            //format: new OpenLayers.Format.GML,
            queryVisible: true,
            output: 'features',
            eventListeners: {
              "getfeatureinfo": function(event) {
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
          // =============== notify(): ============== 
        },
        failure: function(response) {
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