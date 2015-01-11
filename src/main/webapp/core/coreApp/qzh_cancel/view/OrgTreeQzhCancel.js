/**
 *  
 */
Ext.define("core.qzh_cancel.view.OrgTreeQzhCancel", {
  extend: "Ext.tree.Panel",
  alias: "widget.orgTreeQzhCancel",
  rootVisible: false, // 不展示根节点
  displayField: "text",
  animate: false, // 去掉一些动画效果
  store: "core.qzh_cancel.store.OrgStore_QzhCancel",
  tools: [{
      type: 'refresh',
      qtip: '刷新',
      handler: function(event, toolEl, header) {
        header.ownerCt.getStore().reload();
      }
    }]
});