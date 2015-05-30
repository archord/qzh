/**
 *  
 */
Ext.define("core.org.view.OrgLayout", {
  extend: 'Ext.panel.Panel',
  alias: 'widget.orgLayout',
  title: "<center height=40>组织机构管理</center>",
  closable: true,
  defaults: {
    split: true, // 可以设置好看点的折叠效果
    collapsible: true, // 可以被折叠
    bodyStyle: 'padding:1px'
  },
  layout: 'border',
  items: [{
      title: "组织机构列表",
      region: 'west',
      // iconCls:'goodtype_tree',
      xtype: "orgTree",
      id: "orgStoreId",
      store: Ext.create('core.org.store.OrgStoreTree'),
      margins: '5 2 5 5',
      width: 200
    },  {
      xtype: "panel",
      region: "center",
      border: 0,
      header: false,
      layout: "border",
      items: [{
          // iconCls:'good_table',
          collapsible: true, // 可以被折叠
          //xtype : 'panel',
          region: 'center',
          height: 300,
          margins: '5 0 5 0',
          xtype: "orggrid",
          //bbar: [],
//          store: Ext.create("core.user.store.UserStore", {}),
          title: "组织机构列表"

        }]
    }]
});