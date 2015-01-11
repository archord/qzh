/**
 *  
 */
Ext.define("core.qzh_reissue.view.QzhReissueLayout", {
  extend: 'Ext.panel.Panel',
  alias: 'widget.qzhReissueLayout',
  id: "qzhReissueLayoutId",
  title: "<center height=40>权证补发</center>",
  closable: true,
  defaults: {
    split: true, // 可以设置好看点的折叠效果
    collapsible: true, // 可以被折叠
    bodyStyle: 'padding:1px'
  },
  layout: 'border',
  items: [Ext.create('core.qzh_reissue.view.OrgTreeQzhReissue', {
      title: "地区列表",
      region: 'west',
      id: "orgTreeQzhReissueId",
      // iconCls:'goodtype_tree',
      store: Ext.create('core.qzh_reissue.store.OrgStore_QzhReissue'),
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
          xtype: "qzhReissueGrid",
          bbar: [],
//          store: Ext.create("core.qzh_reissue.store.FbfStore", {}),
          title: "权证列表"

        }]
    }]
});