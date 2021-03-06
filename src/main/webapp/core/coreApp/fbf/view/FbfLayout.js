/**
 *  
 */
Ext.define("core.fbf.view.FbfLayout", {
  extend: 'Ext.panel.Panel',
  alias: 'widget.fbfLayout',
  id: "fbfLayoutId",
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
      xtype: "orgTreeFbf",
      id: "orgTreeFbfId",
      store: Ext.create('core.fbf.store.OrgStore'),
      margins: '5 2 5 5',
      width: 150
    }, {
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
          xtype: "fbfgrid",
          bbar: [],
//          store: Ext.create("core.fbf.store.FbfStore", {}),
          title: "发包方列表"

        }]
    }]
});