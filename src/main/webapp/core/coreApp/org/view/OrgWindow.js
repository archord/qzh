Ext.define("core.org.view.OrgWindow", {
  extend: 'Ext.window.Window',
  alias: "widget.orgWindow",
  id: "orgWindowId",
  align: "left",
  frame: true,
  bodyStyle: 'background:transparent',
  title: "添加承包方信息",
//  layout: "border",
  listeners: {
    show: function(_this) {
      if (_this.extraParas) {
        _this.down("form").getForm().findField("parentName").setValue(_this.extraParas.obj.parentName);
        _this.down("form").getForm().findField("parentId").setValue(_this.extraParas.obj.parentId);
        _this.down("form").getForm().findField("isAdd").setValue(0);
        _this.down("form").getForm().findField("orgId").setValue(_this.extraParas.obj.orgId);
        _this.down("form").getForm().findField("orgName").setValue(_this.extraParas.obj.orgName);
        _this.down("form").getForm().findField("orgCoding").setValue(_this.extraParas.obj.orgCoding);
//        _this.down("form").getForm().findField("postalCode").setValue(_this.extraParas.obj.postalCode);
        _this.down("form").getForm().findField("authOrgName").setValue(_this.extraParas.obj.authOrgName);
        _this.down("form").getForm().findField("authPeople").setValue(_this.extraParas.obj.authPeople);
        _this.down("form").getForm().findField("authPhone").setValue(_this.extraParas.obj.authPhone);
        _this.down("form").getForm().findField("fbfName").setValue(_this.extraParas.obj.fbfName);
        _this.down("form").getForm().findField("fbfLegalPerson").setValue(_this.extraParas.obj.fbfLegalPerson);
        _this.down("form").getForm().findField("fbfPhone").setValue(_this.extraParas.obj.fbfPhone);
        _this.down("form").getForm().findField("fbfAddress").setValue(_this.extraParas.obj.fbfAddress);
        _this.down("form").getForm().findField("orgMember").setValue(_this.extraParas.obj.orgMember);
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
      height: 250,
      width: 570,
//      collapsible: true, // 可以被折叠
      xtype: "orgform"
    }]
});