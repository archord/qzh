/**
 *  
 */
Ext.define("core.main.view.OrgTree", {
  extend: "Ext.tree.Panel",
  alias: "widget.orgTreeAll",
  id: "orgTreeAllId",
  rootVisible: false, // 不展示根节点
  displayField: "text",
  animate: false, // 去掉一些动画效果
  store: "core.main.store.OrgStore",
  tbar: [{xtype: 'tbfill'},{
      xtype: "button",
      ref: "save",
      iconCls: 'table_save',
      text: "确定"
    }]
//  ,
//  tools: [{
//      type: 'refresh',
//      qtip: '刷新',
//      handler: function(event, toolEl, header) {
//        header.ownerCt.getStore().reload();
//      }
//    }]
});