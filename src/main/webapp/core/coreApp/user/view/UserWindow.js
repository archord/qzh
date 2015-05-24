Ext.define("core.user.view.UserWindow", {
  extend: 'Ext.window.Window',
  alias: "widget.userWindow",
  id: "userWindowId",
  align: "left",
  frame: true,
  bodyStyle: 'background:transparent',
  title: "添加承包方信息",
//  layout: "border",
  listeners: {
    show: function(_this) {
      if (_this.extraParas) {
        _this.down("form").getForm().findField("orgName").setValue(_this.extraParas.obj.orgName);
        _this.down("form").getForm().findField("isAdd").setValue(0);
        _this.down("form").getForm().findField("orgId").setValue(_this.extraParas.obj.orgId);
        _this.down("form").getForm().findField("id").setValue(_this.extraParas.obj.id);
        _this.down("form").getForm().findField("name").setValue(_this.extraParas.obj.name);
        _this.down("form").getForm().findField("password").setValue(_this.extraParas.obj.password);
        _this.down("form").getForm().findField("password2").setValue(_this.extraParas.obj.password);
      } else {
        _this.down("form").getForm().findField("isAdd").setValue(1);
      }
    }
  },
  items: [{
      //xtype : 'panel',
      region: 'center',
      // iconCls:'goodtype_tree',
      margins: '5 0 5 0',
      height: 180,
      width: 300,
//      collapsible: true, // 可以被折叠
      xtype: "userform"
    }]
});