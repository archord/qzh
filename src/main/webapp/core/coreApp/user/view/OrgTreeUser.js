/**
 *  
 */
Ext.define("core.user.view.OrgTreeUser", {
  extend: "Ext.tree.Panel",
  alias: "widget.orgTreeUser",
  rootVisible: false, // 不展示根节点
  displayField: "text",
  animate: false, // 去掉一些动画效果
  store: "core.user.store.OrgStore",
  tools: [{
      type: 'refresh',
      qtip: '刷新',
      handler: function(event, toolEl, header) {
        header.ownerCt.getStore().reload();
      }
    }]
});