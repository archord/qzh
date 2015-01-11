/**
 *  
 */
Ext.define("core.cbf_jtcy.view.OrgTreeQzhCbfJtcy_Qzh", {
  extend: "Ext.tree.Panel",
  alias: "widget.orgTreeQzh_QzhCbfJtcy",
  rootVisible: false, // 不展示根节点
  displayField: "text",
  animate: false, // 去掉一些动画效果
  store: "core.cbf_jtcy.store.OrgStore_QzhCbfJtcy_Qzh",
  tools: [{
      type: 'refresh',
      qtip: '刷新',
      handler: function(event, toolEl, header) {
        header.ownerCt.getStore().reload();
      }
    }]
});