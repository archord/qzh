/**
 *  
 */
Ext.define("core.qzh_get.view.OrgTreeQzhGet_Qzh", {
  extend: "Ext.tree.Panel",
  alias: "widget.orgTreeQzh_QzhGet",
  rootVisible: false, // 不展示根节点
  displayField: "text",
  animate: false, // 去掉一些动画效果
  store: "core.qzh_get.store.OrgStore_QzhGet_Qzh",
  tools: [{
      type: 'refresh',
      qtip: '刷新',
      handler: function(event, toolEl, header) {
        header.ownerCt.getStore().reload();
      }
    }]
});