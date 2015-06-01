Ext.define("core.dk.view.OrgWindowAll_dk", {
  extend: 'Ext.window.Window',
  alias: "widget.OrgWindowAll_dk",
  id: "OrgWindowAll_dkId",
  align: "left",
  frame: true,
  bodyStyle: 'background:transparent',
  title: "选择组织机构",
//  layout: "border",
  listeners: {
    show: function(_this) {
    }
  },
  items: [{
//      title: "组织机构列表",
      alias: "widget.orgTreeAll_dk",
      region: 'west',
      // iconCls:'goodtype_tree',
      xtype: "treepanel",
      rootVisible: false, // 不展示根节点
      displayField: "text",
      animate: false, // 去掉一些动画效果
      store: 'core.main.store.OrgStore',
      margins: '5 2 5 5',
      width: 300,
      height: 200,
      tbar: [{xtype: 'tbfill'}, {
          xtype: "button",
          ref: "save",
          iconCls: 'table_save',
          text: "确定"
        }]
    }]
});