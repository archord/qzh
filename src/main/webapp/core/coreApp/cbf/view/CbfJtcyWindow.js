Ext.define("core.cbf.view.CbfJtcyWindow", {
  extend: 'Ext.window.Window',
  alias: "widget.cbfJtcyWindow",
  id: "cbfJtcyWindowId",
  align: "left",
  frame: true,
  bodyStyle: 'background:transparent',
  title: "添加家庭成员信息",
//  layout: "border",
  listeners: {
    show: function(_this) {
      if (_this.extraParas) {
        if (_this.extraParas.isAdd === 0) {
          _this.down("form").getForm().findField("isAdd").setValue(0);
          _this.down("form").getForm().findField("id").setValue(_this.extraParas.obj.id);
          _this.down("form").getForm().findField("cbfbm").setValue(_this.extraParas.obj.cbfbm);
          _this.down("form").getForm().findField("cyxm").setValue(_this.extraParas.obj.cyxm);
          _this.down("form").getForm().findField("cyxb").setValue(_this.extraParas.obj.cyxb);
          _this.down("form").getForm().findField("cymz").setValue(_this.extraParas.obj.cymz);
          _this.down("form").getForm().findField("cyzjlx").setValue(_this.extraParas.obj.cyzjlx);
          _this.down("form").getForm().findField("cyzjhm").setValue(_this.extraParas.obj.cyzjhm);
          _this.down("form").getForm().findField("yhzgx").setValue(_this.extraParas.obj.yhzgx);
          _this.down("form").getForm().findField("cybz").setValue(_this.extraParas.obj.cybz);
          _this.down("form").getForm().findField("sfgyr").setValue(_this.extraParas.obj.sfgyr);
        } else {
          _this.down("form").getForm().findField("isAdd").setValue(1);
          _this.down("form").getForm().findField("cbfbm").setValue(_this.extraParas.cbfbm);
          _this.down("form").getForm().findField("id").setValue(0);
        }
      } else {
        Ext.MessageBox.alert("系统错误", "请刷新网页或联系管理员!");
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
      xtype: "cbfJtcyForm"
    }]
});