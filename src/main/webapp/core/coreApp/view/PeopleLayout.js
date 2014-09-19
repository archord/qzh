/**
 * 类别管理布局类
 */
Ext.define("core.view.PeopleLayout", {
  extend: 'Ext.panel.Panel',
  alias: 'widget.peopleLayout',
  title: "<center height=40>发包方信息管理</center>",
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
      xtype: "orgTreePeo",
      store: Ext.create('core.store.OrgStore'),
      margins: '5 2 5 5',
      width: 150
    }, {
      xtype: "panel",
      region: "center",
      border: 0,
      header: false,
      layout: "border",
      items: [{
          //xtype : 'panel',
          region: 'north',
          // iconCls:'goodtype_tree',
          margins: '5 0 5 0',
          height: 270,
          title: '发包方详细信息',
          collapsible: true, // 可以被折叠
          xtype: "peopleform"

        }, {
          // iconCls:'good_table',
          collapsible: true, // 可以被折叠
          //xtype : 'panel',
          region: 'center',
          height: 300,
          margins: '5 0 5 0',
          xtype: "people_grid",
          bbar: [],
//          store: Ext.create("core.store.PeopleStore", {}),
          title: "人员列表"

        }]
    }]
//  ,
//  initComponent: function() {
//    Ext.apply(this, {
//      store: Ext.create('core.store.OrgStore')
//    });
//
//    this.callParent(arguments);
//  }
});