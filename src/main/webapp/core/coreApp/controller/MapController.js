Ext.define("core.controller.MapController", {
//	mixins:{
//		gridUtils:"core.utils.GridUtils"
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
          console.log(nodeId);
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
          console.log(mapPanel);
          mapPanel.map.addLayer(layerArr[0]);
          mapPanel.map.addLayer(layerArr[1]);
          // =============== notify(): ============== 
        },
        failure: function(response) {
          alert("Sorry, there was an error requesting data !!!");
        }
      });
    }
  },
  views: [
    "core.view.MapLayout",
    "core.view.OrgTreeMap"
  ],
  stores: ["core.store.OrgStore"],
  models: []
});