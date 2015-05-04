Ext.define("core.cbf.view.CbfWindow", {
  extend: 'Ext.window.Window',
  alias: "widget.cbfWindow",
  id: "cbfWindowId",
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
        _this.down("form").getForm().findField("cbfbm").setValue(_this.extraParas.obj.cbfbm);
        _this.down("form").getForm().findField("cbflx").setValue(_this.extraParas.obj.cbflx);
        _this.down("form").getForm().findField("cbfmc").setValue(_this.extraParas.obj.cbfmc);
        _this.down("form").getForm().findField("cbfxb").setValue(_this.extraParas.obj.cbfxb);
        _this.down("form").getForm().findField("cbfmz").setValue(_this.extraParas.obj.cbfmz);
        _this.down("form").getForm().findField("cbfzjlx").setValue(_this.extraParas.obj.cbfzjlx);
        _this.down("form").getForm().findField("cbfzjhm").setValue(_this.extraParas.obj.cbfzjhm);
        _this.down("form").getForm().findField("cbfdz").setValue(_this.extraParas.obj.cbfdz);
        _this.down("form").getForm().findField("yzbm").setValue(_this.extraParas.obj.yzbm);
        _this.down("form").getForm().findField("lxdh").setValue(_this.extraParas.obj.lxdh);
        _this.down("form").getForm().findField("cbfcysl").setValue(_this.extraParas.obj.cbfcysl);
        _this.down("form").getForm().findField("cbfdcrq").setValue(_this.extraParas.obj.cbfdcrq);
        _this.down("form").getForm().findField("cbfdcy").setValue(_this.extraParas.obj.cbfdcy);
        _this.down("form").getForm().findField("cbfdcjs").setValue(_this.extraParas.obj.cbfdcjs);
        _this.down("form").getForm().findField("gsjs").setValue(_this.extraParas.obj.gsjs);
        _this.down("form").getForm().findField("gsjsr").setValue(_this.extraParas.obj.gsjsr);
        _this.down("form").getForm().findField("gsshrq").setValue(_this.extraParas.obj.gsshrq);
        _this.down("form").getForm().findField("gsshr").setValue(_this.extraParas.obj.gsshr);

        var cbfJtcyGrid = Ext.getCmp("cbfJtcyGridId");
        var store = cbfJtcyGrid.getStore();
        store.load({params: {cbfbm: _this.extraParas.obj.cbfbm}});
      } else {
        var orgTree = Ext.getCmp("orgTreeCbfId");
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
      height: 240,
      width: 750,
//      collapsible: true, // 可以被折叠
      xtype: "cbfform"
    }, {
      // iconCls:'good_table',
//      collapsible: true, // 可以被折叠
      //xtype : 'panel',
      region: 'south ',
      height: 200,
      width: 750,
      margins: '5 0 5 0',
      xtype: "cbfJtcyGrid",
      //bbar: [],
//          store: Ext.create("core.cbht.store.FbfStore", {}),
      title: "承包方家庭成员"

    }]
});