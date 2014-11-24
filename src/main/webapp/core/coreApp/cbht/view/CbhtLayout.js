/**
 *  
 */
Ext.define("core.cbht.view.CbhtLayout", {
  extend: 'Ext.panel.Panel',
  alias: 'widget.cbhtLayout',
  id: "cbhtLayout",
  title: "<center height=40>承包合同管理</center>",
  closable: true,
  defaults: {
    split: true, // 可以设置好看点的折叠效果
    collapsible: true, // 可以被折叠
    bodyStyle: 'padding:1px'
  },
  layout: 'border',
  items: [Ext.create('core.cbht.view.OrgTreeCbht', {
      title: "地区列表",
      region: 'west',
      id: "orgTreeCbht",
      // iconCls:'goodtype_tree',
      store: Ext.create('core.cbht.store.OrgStore'),
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
          xtype: "cbhtgrid",
          bbar: [],
//          store: Ext.create("core.cbht.store.FbfStore", {}),
          title: "承包合同列表"

        }]
    }]
});