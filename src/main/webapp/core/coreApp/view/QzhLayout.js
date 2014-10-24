/**
 * 类别管理布局类
 */
Ext.define("core.view.DkLayout", {
  extend: 'Ext.panel.Panel',
  alias: 'widget.dkLayout',
  id: "dkLayout",
  title: "<center height=40>地块信息管理</center>",
  closable:true,
  defaults: {
    split: true, // 可以设置好看点的折叠效果
    collapsible: true, // 可以被折叠
    bodyStyle: 'padding:1px'
  },
  layout: 'border',
  items: [Ext.create('core.view.OrgTreeDk', {
      title: "地区列表",
      region: 'west',
      id: "orgTreeDk",
      // iconCls:'goodtype_tree',
      store: Ext.create('core.store.OrgStore'),
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
          xtype: "dkgrid",
          bbar: [],
//          store: Ext.create("core.store.PeopleStore", {}),
          title: "地块信息列表"

        }]
    }]
});