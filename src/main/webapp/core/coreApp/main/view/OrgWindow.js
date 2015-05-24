Ext.define("core.main.view.OrgWindow", {
  extend: 'Ext.window.Window',
  alias: "widget.orgWindowAll",
  id: "orgWindowAllId",
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
      region: 'west',
      // iconCls:'goodtype_tree',
      xtype: "orgTreeAll",
      store: Ext.create('core.main.store.OrgStore'),
      margins: '5 2 5 5',
      width: 300,
      height: 200
    }]
});