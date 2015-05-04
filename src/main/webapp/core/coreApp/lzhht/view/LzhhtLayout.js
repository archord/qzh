/**
 *  
 */
Ext.define("core.lzhht.view.LzhhtLayout", {
  extend: 'Ext.panel.Panel',
  alias: 'widget.lzhhtLayout',
  id: "lzhhtLayout",
  title: "<center height=40>流转合同管理</center>",
  closable:true,
  defaults: {
    split: true, // 可以设置好看点的折叠效果
    collapsible: true, // 可以被折叠
    bodyStyle: 'padding:1px'
  },
  layout: 'border',
  items: [Ext.create('core.lzhht.view.OrgTreeLzhht', {
      title: "地区列表",
      region: 'west',
      id: "orgTreeLzhht",
      // iconCls:'goodtype_tree',
      store: Ext.create('core.lzhht.store.OrgStore'),
      margins: '5 2 5 5',
      width: 150
    }), {
      xtype: "panel",
      region: "center",
      border: 0,
      header: false,
      layout: "fit",
      items: [{
          // iconCls:'good_table',
          collapsible: true, // 可以被折叠
          //xtype : 'panel',
          region: 'center',
          height: 200,
          margins: '5 0 5 0',
          xtype: "lzhhtGrid",
          //bbar: [],
//          store: Ext.create("core.lzhht.store.FbfStore", {}),
          title: "流转合同列表"

        }]
    }]
});