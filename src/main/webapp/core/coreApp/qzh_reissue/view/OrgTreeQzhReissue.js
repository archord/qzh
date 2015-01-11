/**
 *  
 */
Ext.define("core.qzh_reissue.view.OrgTreeQzhReissue", {
  extend: "Ext.tree.Panel",
  alias: "widget.orgTreeQzhReissue",
  rootVisible: false, // 不展示根节点
  displayField: "text",
  animate: false, // 去掉一些动画效果
  store: "core.qzh_reissue.store.OrgStore_QzhReissue",
  tools: [{
      type: 'refresh',
      qtip: '刷新',
      handler: function(event, toolEl, header) {
        header.ownerCt.getStore().reload();
      }
    }]
});