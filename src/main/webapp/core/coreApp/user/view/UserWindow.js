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
        _this.down("form").getForm().findField("orgName").setValue("修改承包合同信息");
        _this.down("form").getForm().findField("isAdd").setValue(0);
        _this.down("form").getForm().findField("orgId").setValue(_this.extraParas.obj.orgId);
        _this.down("form").getForm().findField("orgLevel").setValue(_this.extraParas.orgLevel);
        _this.down("form").getForm().findField("id").setValue(_this.extraParas.obj.id);
        _this.down("form").getForm().findField("userbm").setValue(_this.extraParas.obj.userbm);
        _this.down("form").getForm().findField("usermc").setValue(_this.extraParas.obj.usermc);
        _this.down("form").getForm().findField("userfzrxm").setValue(_this.extraParas.obj.userfzrxm);
        _this.down("form").getForm().findField("fzrzjlx").setValue(_this.extraParas.obj.fzrzjlx);
        _this.down("form").getForm().findField("fzrzjhm").setValue(_this.extraParas.obj.fzrzjhm);
        _this.down("form").getForm().findField("lxdh").setValue(_this.extraParas.obj.lxdh);
        _this.down("form").getForm().findField("userdz").setValue(_this.extraParas.obj.userdz);
        _this.down("form").getForm().findField("yzbm").setValue(_this.extraParas.obj.yzbm);
        _this.down("form").getForm().findField("userdcy").setValue(_this.extraParas.obj.userdcy);
        _this.down("form").getForm().findField("userdcrq").setValue(_this.extraParas.obj.userdcrq);
        _this.down("form").getForm().findField("userdcjs").setValue(_this.extraParas.obj.userdcjs);

      } else {
        var orgTree = Ext.getCmp("orgTreeUserId");
        var curSelNode = orgTree.getSelectionModel().getSelection();
        if (curSelNode.length > 0 && curSelNode[0].raw) {
          _this.down("form").getForm().findField("orgName").setValue(curSelNode[0].raw.orgName);
          _this.down("form").getForm().findField("isAdd").setValue(1);
          _this.down("form").getForm().findField("orgId").setValue(curSelNode[0].raw.orgId);
          _this.down("form").getForm().findField("orgLevel").setValue(curSelNode[0].raw.orgLevel);
        }
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