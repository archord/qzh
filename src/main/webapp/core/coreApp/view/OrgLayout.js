/**
 * 类别管理布局类
 */
Ext.define("core.view.OrgLayout", {
  extend: 'Ext.panel.Panel',
  alias: 'widget.orgLayout',
  title: "<center height=40>组织机构管理</center>",
  closable: true,
  defaults: {
    split: true, // 可以设置好看点的折叠效果
    collapsible: true, // 可以被折叠
    bodyStyle: 'padding:1px'
  },
  layout: 'border',
  items: [{
      title: "组织机构列表",
      region: 'west',
      // iconCls:'goodtype_tree',
      xtype: "orgTree",
      margins: '5 2 5 5',
      width: 200
    }, {
      title: "增加组织机构",
      xtype: "addOrganization",
      margins: '5 2 5 5',
      region: "center"
    }]
});