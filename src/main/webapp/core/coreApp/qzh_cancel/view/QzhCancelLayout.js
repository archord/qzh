/**
 *  
 */
Ext.define("core.qzh_cancel.view.QzhCancelLayout", {
  extend: 'Ext.panel.Panel',
  alias: 'widget.qzhCancelLayout',
  id: "qzhCancelLayoutId",
  title: "<center height=40>权证注销</center>",
  closable: true,
  defaults: {
    split: true, // 可以设置好看点的折叠效果
    collapsible: true, // 可以被折叠
    bodyStyle: 'padding:1px'
  },
  layout: 'border',
  items: [Ext.create('core.qzh_cancel.view.OrgTreeQzhCancel', {
      title: "地区列表",
      region: 'west',
      id: "orgTreeQzhCancelId",
      // iconCls:'goodtype_tree',
      store: Ext.create('core.qzh_cancel.store.OrgStore_QzhCancel'),
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
          xtype: "qzhCancelGrid",
          bbar: [],
//          store: Ext.create("core.qzh_cancel.store.FbfStore", {}),
          title: "权证领取记录"

        }]
    }]
});