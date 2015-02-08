/**
 *  
 */
Ext.define("core.qzh_get.view.QzhGetLayout", {
  extend: 'Ext.panel.Panel',
  alias: 'widget.qzhGetLayout',
  id: "qzhGetLayoutId",
  title: "<center height=40>权证领取</center>",
  closable: true,
  defaults: {
    split: true, // 可以设置好看点的折叠效果
    collapsible: true, // 可以被折叠
    bodyStyle: 'padding:1px'
  },
  layout: 'border',
  items: [Ext.create('core.qzh_get.view.OrgTreeQzhGet', {
      title: "地区列表",
      region: 'west',
      id: "orgTreeQzhGetId",
      // iconCls:'goodtype_tree',
      store: Ext.create('core.qzh_get.store.OrgStore_QzhGet'),
      margins: '5 2 5 5',
      width: 150
    }), {
      xtype: "panel",
      region: "center",
      border: 0,
      header: false,
      layout: "fit",
      margins: '5 0 5 0',
      items: [{
          // iconCls:'good_table',
          collapsible: true, // 可以被折叠
          //xtype : 'panel',
          region: 'center',
          height: 200,
          xtype: "qzhGetGrid",
          bbar: [],
//          store: Ext.create("core.qzh_get.store.FbfStore", {}),
          title: "权证领取记录"

        }]
    }]
});