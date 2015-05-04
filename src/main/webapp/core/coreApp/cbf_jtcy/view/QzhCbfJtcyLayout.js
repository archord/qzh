/**
 *  
 */
Ext.define("core.cbf_jtcy.view.QzhCbfJtcyLayout", {
  extend: 'Ext.panel.Panel',
  alias: 'widget.qzhCbfJtcyLayout',
  id: "qzhCbfJtcyLayoutId",
  title: "<center height=40>家庭成员管理</center>",
  closable: true,
  defaults: {
    split: true, // 可以设置好看点的折叠效果
    collapsible: true, // 可以被折叠
    bodyStyle: 'padding:1px'
  },
  layout: 'border',
  items: [Ext.create('core.cbf_jtcy.view.OrgTreeQzhCbfJtcy', {
      title: "地区列表",
      region: 'west',
      id: "orgTreeQzhCbfJtcyId",
      // iconCls:'goodtype_tree',
      store: Ext.create('core.cbf_jtcy.store.OrgStore_QzhCbfJtcy'),
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
          xtype: "qzhCbfJtcyGrid",
          //bbar: [],
//          store: Ext.create("core.cbf_jtcy.store.FbfStore", {}),
          title: "权证列表"

        }]
    }]
});