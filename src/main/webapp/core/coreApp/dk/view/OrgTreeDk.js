/**
 *  
 */
Ext.define("core.dk.view.OrgTreeDk", {
  extend: "Ext.tree.Panel",
  alias: "widget.orgTreeDk",
  rootVisible: false, // 不展示根节点
  displayField: "text",
  animate: false, // 去掉一些动画效果
  store: "core.dk.store.OrgStore",
  tools: [{
      type: 'refresh',
      qtip: '刷新',
      handler: function(event, toolEl, header) {
        header.ownerCt.getStore().reload();
      }
    }]
});