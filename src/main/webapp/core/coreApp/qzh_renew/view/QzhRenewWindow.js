Ext.define("core.qzh_renew.view.QzhRenewWindow", {
  extend: 'Ext.window.Window',
  alias: "widget.qzhRenewWindow",
  id: "qzhRenewWindowId",
  align: "left",
  frame: true,
  bodyStyle: 'background:transparent',
  title: "领取权证",
//  layout: "border",
  listeners: {
    show: function(_this) {
      if (_this.extraParas) {
        _this.down("form").getForm().findField("orgName").setValue("修改承包合同信息");
        _this.down("form").getForm().findField("isAdd").setValue(0);
        _this.down("form").getForm().findField("orgId").setValue(_this.extraParas.obj.orgId);
        _this.down("form").getForm().findField("orgLevel").setValue(_this.extraParas.orgLevel);
        _this.down("form").getForm().findField("id").setValue(_this.extraParas.obj.id);
        _this.down("form").getForm().findField("cbhtbm").setValue(_this.extraParas.obj.cbhtbm);
        _this.down("form").getForm().findField("ycbhtbm").setValue(_this.extraParas.obj.ycbhtbm);
        _this.down("form").getForm().findField("fbfbm").setValue(_this.extraParas.obj.fbfbm);
        _this.down("form").getForm().findField("cbfbm").setValue(_this.extraParas.obj.cbfbm);
        _this.down("form").getForm().findField("cbfs").setValue(_this.extraParas.obj.cbfs);
        _this.down("form").getForm().findField("cbqxq").setValue(_this.extraParas.obj.cbqxq);
        _this.down("form").getForm().findField("cbqxz").setValue(_this.extraParas.obj.cbqxz);
        _this.down("form").getForm().findField("htzmj").setValue(_this.extraParas.obj.htzmj);
        _this.down("form").getForm().findField("cbdkzs").setValue(_this.extraParas.obj.cbdkzs);
        _this.down("form").getForm().findField("qdsj").setValue(_this.extraParas.obj.qdsj);
        
        var dkgrid = Ext.getCmp("cbhtdkgrid");
        var store = dkgrid.getStore();
        store.load({params: {cbhtId: _this.extraParas.obj.id}});
      } else {
        var orgTree = Ext.getCmp("orgTreeCbht");
        var curSelNode = orgTree.getSelectionModel().getSelection();
        if (curSelNode[0].raw) {
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
      height: 210,
      width: 600,
      collapsible: false, // 可以被折叠
      xtype: "qzhRenewForm"
    }]
});