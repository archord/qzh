/**
 *  
 */
Ext.define("core.org.view.OrgTree", {
  extend: "Ext.tree.Panel",
  alias: "widget.orgTree",
  rootVisible: false, // 不展示根节点
  displayField: "text",
  animate: false, // 去掉一些动画效果
  store: "core.org.store.OrgStoreTree",
  tools: [{
      type: 'refresh',
      qtip: '刷新',
      handler: function(event, toolEl, header) {
        header.ownerCt.getStore().reload();
      }
    }]
//  ,
//  rbar: [{
//      xtype: 'button',
//      tooltip: '添加县',
//      iconCls: 'tree_model_lv1',
//      ref: 'addXian'
//    }, {
//      xtype: 'button',
//      tooltip: '添加乡(镇)',
//      iconCls: 'tree_model_lv2',
//      disabled: true,
//      ref: 'addXiang'
//    }, {
//      xtype: 'button',
//      tooltip: '添加村',
//      iconCls: 'tree_model_lv3',
//      disabled: true,
//      ref: 'addCun'
//    }, {
//      xtype: 'button',
//      tooltip: '添加组',
//      iconCls: 'tree_model_lv4',
//      disabled: true,
//      ref: 'addZun'
//    }, {
//      xtype: 'button',
//      tooltip: '删除',
//      iconCls: 'tree_delete',
//      disabled: true,
//      ref: 'delOrg'
//    }]
});