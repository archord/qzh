/**
 *  
 */
Ext.define("core.qzh_get.view.OrgTreeQzhGet", {
  extend: "Ext.tree.Panel",
  alias: "widget.orgTreeQzhGet",
  rootVisible: false, // 不展示根节点
  displayField: "text",
  animate: false, // 去掉一些动画效果
  store: "core.qzh_get.store.OrgStore_QzhGet",
  tools: [{
      type: 'refresh',
      qtip: '刷新',
      handler: function(event, toolEl, header) {
        header.ownerCt.getStore().reload();
      }
    }]
});