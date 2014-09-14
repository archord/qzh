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

/**
 * 类别管理布局类
 */
Ext.define("core.view.MapLayout", {
  extend: 'Ext.panel.Panel',
  alias: 'widget.mapLayout',
  title: "<center height=40>地图信息</center>",
  closable: true,
  defaults: {
    split: true, // 可以设置好看点的折叠效果
    collapsible: true, // 可以被折叠
    bodyStyle: 'padding:1px'
  },
  layout: 'border',
  items: [{
      title: "地区列表",
      region: 'west',
      // iconCls:'goodtype_tree',
      xtype: "orgTreeShow3",
      margins: '5 2 5 5',
      width: 150
    }, Ext.create('GeoExt.panel.Map', {
    border: true,
    region: "center",
    // we do not want all overlays, to try the OverlayLayerContainer
    map: new OpenLayers.Map({allOverlays: false}),
    //center: [146.1569825, -41.6109735],
    //zoom: 6,
    layers: [
      new OpenLayers.Layer.WMS("底图",
              "http://localhost:8081/geoserver/cite/wms", {
        layers: "cite:geotif08"
      }, {
        buffer: 0,
        visibility: false,
        projection: "EPSG:2351",
        maxExtent: new OpenLayers.Bounds(
                2.7427118263325926E7, 4533983.087420987, 2.7429978295091048E7, 4537211.441640869)
      }
      ),
      new OpenLayers.Layer.WMS("矢量图",
              "http://localhost:8081/geoserver/cite/wms", {
        layers: "cite:Export_Output",
        transparent: true,
        format: "image/gif"
      }, {
        isBaseLayer: false,
        buffer: 0,
        projection: "EPSG:2351",
        maxExtent: new OpenLayers.Bounds(
                2.7427533132E7, 4534310.627248583, 2.7429808179E7, 4536783.197)
      }
      )
    ]
  })]
});