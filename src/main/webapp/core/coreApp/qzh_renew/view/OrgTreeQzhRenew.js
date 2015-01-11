/**
 *  
 */
Ext.define("core.qzh_renew.view.OrgTreeQzhRenew", {
  extend: "Ext.tree.Panel",
  alias: "widget.orgTreeQzhRenew",
  rootVisible: false, // 不展示根节点
  displayField: "text",
  animate: false, // 去掉一些动画效果
  store: "core.qzh_renew.store.OrgStore_QzhRenew",
  tools: [{
      type: 'refresh',
      qtip: '刷新',
      handler: function(event, toolEl, header) {
        header.ownerCt.getStore().reload();
      }
    }]
});