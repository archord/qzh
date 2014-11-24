/**
 *  
 */
Ext.define("core.cbf.view.CbfLayout", {
  extend: 'Ext.panel.Panel',
  alias: 'widget.cbfLayout',
  title: "<center height=40>承包方信息管理</center>",
  closable:true,
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
      xtype: "orgTreeCbf",
      store: Ext.create('core.cbf.store.OrgStore'),
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
          height: 360,
          title: '承包方详细信息',
          collapsible: true, // 可以被折叠
          xtype: "cbfform"

        }, {
          // iconCls:'good_table',
          collapsible: true, // 可以被折叠
          //xtype : 'panel',
          region: 'center',
          height: 200,
          margins: '5 0 5 0',
          xtype: "cbfgrid",
          bbar: [],
//          store: Ext.create("core.cbf.store.FbfStore", {}),
          title: "人员列表"

        }]
    }]
//  ,
//  initComponent: function() {
//    Ext.apply(this, {
//      store: Ext.create('core.cbf.store.OrgStore')
//    });
//
//    this.callParent(arguments);
//  }
});