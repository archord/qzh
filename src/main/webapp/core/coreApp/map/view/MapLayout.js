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
 *  
 */
Ext.define("core.map.view.MapLayout", {
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
      xtype: "orgTreeMap",
      store: Ext.create('core.map.store.OrgStore'),
      margins: '5 2 5 5',
      width: 150
    }, Ext.create('GeoExt.panel.Map', {
      alias: 'widget.mapPanel',
      id: 'mapPanelId',
      border: true,
      region: "center",
      // we do not want all overlays, to try the OverlayLayerContainer
      map: new OpenLayers.Map({allOverlays: false}),
      //center: [146.1569825, -41.6109735],
      //zoom: 6,
      layers: [ ]
    })]
});