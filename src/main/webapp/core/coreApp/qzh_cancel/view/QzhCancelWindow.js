Ext.define("core.qzh_cancel.view.QzhCancelWindow", {
  extend: 'Ext.window.Window',
  alias: "widget.qzhCancelWindow",
  id: "qzhCancelWindowId",
  align: "left",
  frame: true,
  bodyStyle: 'background:transparent',
  title: "注销权证",
//  layout: "border",
  listeners: {
    show: function(_this) {
      if (_this.extraParas) {
        _this.down("form").getForm().findField("orgName").setValue("修改承包合同信息");
        _this.down("form").getForm().findField("isAdd").setValue(0);
        _this.down("form").getForm().findField("orgId").setValue(_this.extraParas.obj.orgId);
        _this.down("form").getForm().findField("orgLevel").setValue(_this.extraParas.orgLevel);
        _this.down("form").getForm().findField("id").setValue(_this.extraParas.obj.id);
        _this.down("form").getForm().findField("cbjyqzbm").setValue(_this.extraParas.obj.cbjyqzbm);
        _this.down("form").getForm().findField("zxyy").setValue(_this.extraParas.obj.zxyy);
        _this.down("form").getForm().findField("zxrq").setValue(_this.extraParas.obj.zxrq);
        
//        var dkgrid = Ext.getCmp("cbhtdkgrid");
//        var store = dkgrid.getStore();
//        store.load({params: {cbhtId: _this.extraParas.obj.id}});
      } else {
        var orgTree = Ext.getCmp("orgTreeQzhCancelId");
        var curSelNode = orgTree.getSelectionModel().getSelection();
        if (curSelNode.length>0) {
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
      xtype: "qzhCancelForm"
    }]
});