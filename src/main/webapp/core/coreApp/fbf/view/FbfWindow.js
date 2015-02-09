Ext.define("core.fbf.view.FbfWindow", {
  extend: 'Ext.window.Window',
  alias: "widget.fbfWindow",
  id: "fbfWindowId",
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
        _this.down("form").getForm().findField("fbfbm").setValue(_this.extraParas.obj.fbfbm);
        _this.down("form").getForm().findField("fbfmc").setValue(_this.extraParas.obj.fbfmc);
        _this.down("form").getForm().findField("fbffzrxm").setValue(_this.extraParas.obj.fbffzrxm);
        _this.down("form").getForm().findField("fzrzjlx").setValue(_this.extraParas.obj.fzrzjlx);
        _this.down("form").getForm().findField("fzrzjhm").setValue(_this.extraParas.obj.fzrzjhm);
        _this.down("form").getForm().findField("lxdh").setValue(_this.extraParas.obj.lxdh);
        _this.down("form").getForm().findField("fbfdz").setValue(_this.extraParas.obj.fbfdz);
        _this.down("form").getForm().findField("yzbm").setValue(_this.extraParas.obj.yzbm);
        _this.down("form").getForm().findField("fbfdcy").setValue(_this.extraParas.obj.fbfdcy);
        _this.down("form").getForm().findField("fbfdcrq").setValue(_this.extraParas.obj.fbfdcrq);
        _this.down("form").getForm().findField("fbfdcjs").setValue(_this.extraParas.obj.fbfdcjs);

      } else {
        var orgTree = Ext.getCmp("orgTreeFbfId");
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
      height: 280,
      width: 600,
//      collapsible: true, // 可以被折叠
      xtype: "fbfform"
    }]
});