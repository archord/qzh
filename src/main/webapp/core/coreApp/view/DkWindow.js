Ext.define("core.view.DkWindow", {
  extend: 'Ext.window.Window',
  alias: "widget.dkwindow",
  align: "left",
  frame: true,
  bodyStyle: 'background:transparent',
  title: "添加地块信息",
  listeners: {
    show: function(_this) {
      if (_this.extraParas) {
        _this.down("form").getForm().findField("orgName").setValue("修改地块信息");
        _this.down("form").getForm().findField("isAdd").setValue(0);
        _this.down("form").getForm().findField("orgId").setValue(_this.extraParas.dk.orgId);
        _this.down("form").getForm().findField("orgLevel").setValue(_this.extraParas.orgLevel);
        _this.down("form").getForm().findField("id").setValue(_this.extraParas.dk.id);
        _this.down("form").getForm().findField("dkbm").setValue(_this.extraParas.dk.dkbm);
        _this.down("form").getForm().findField("dkmc").setValue(_this.extraParas.dk.dkmc);
        _this.down("form").getForm().findField("syqxz").setValue(_this.extraParas.dk.syqxz);
        _this.down("form").getForm().findField("dklb").setValue(_this.extraParas.dk.dklb);
        _this.down("form").getForm().findField("tdlylx").setValue(_this.extraParas.dk.tdlylx);
        _this.down("form").getForm().findField("dldj").setValue(_this.extraParas.dk.dldj);
        _this.down("form").getForm().findField("tdyt").setValue(_this.extraParas.dk.tdyt);
        _this.down("form").getForm().findField("sfjbnt").setValue(_this.extraParas.dk.sfjbnt);
        _this.down("form").getForm().findField("scmj").setValue(_this.extraParas.dk.scmj);
        _this.down("form").getForm().findField("dkdz").setValue(_this.extraParas.dk.dkdz);
        _this.down("form").getForm().findField("dkxz").setValue(_this.extraParas.dk.dkxz);
        _this.down("form").getForm().findField("dknz").setValue(_this.extraParas.dk.dknz);
        _this.down("form").getForm().findField("dkbz").setValue(_this.extraParas.dk.dkbz);
        _this.down("form").getForm().findField("dkbzxx").setValue(_this.extraParas.dk.dkbzxx);
        _this.down("form").getForm().findField("zjrxm").setValue(_this.extraParas.dk.zjrxm);
      } else {
        var orgTree = Ext.getCmp("orgTreeDk");
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
      columnWidth: .7,
      xtype: "form",
      ref: "dkform",
      defaults: {
        margin: "7 15 0 15",
        selectOnFocus: true,
      },
      layout: {
        type: "table",
        columns: 2
      },
      defaultType: 'textfield',
      border: 0,
      items: [{
          colspan: 2,
          xtype: "textfield",
          width: 430,
          fieldLabel: "所属区域",
          name: "orgName",
          allowBlank: true,
          blankText: '必须在右侧选择村级以下区域',
          readOnly: true
        }, {
          xtype: "textfield",
          fieldLabel: "区域级别",
          name: "isAdd",
          value: "1",
          hidden: true
        }, {
          xtype: "textfield",
          fieldLabel: "组织机构id",
          name: "orgId",
          value: "0",
          hidden: true
        }, {
          xtype: "textfield",
          fieldLabel: "区域级别",
          name: "orgLevel",
          value: "0",
          hidden: true
        }, {
          xtype: "textfield",
          fieldLabel: "主键",
          name: "id",
          value: "0",
          hidden: true
        }, {
          xtype: "textfield",
          fieldLabel: "地块编码",
          name: "dkbm",
          allowBlank: false,
          blankText: '地块编码不能为空'
        }, {
          xtype: "textfield",
          fieldLabel: "地块名称",
          name: "dkmc",
          allowBlank: false,
          blankText: '地块名称不能为空',
          readOnly: false
        }, {
          xtype: "textfield",
          fieldLabel: "所有权性质",
          name: "syqxz",
          allowBlank: true,
          readOnly: false
        }, {
          xtype: "textfield",
          fieldLabel: "地块类别",
          name: "dklb",
          allowBlank: true,
          readOnly: false
        }, {
          xtype: "textfield",
          fieldLabel: "土地利用类型",
          name: "tdlylx",
          allowBlank: true,
          readOnly: false
        }, {
          xtype: "textfield",
          fieldLabel: "地力等级",
          name: "dldj",
          allowBlank: true,
          readOnly: false
        }, {
          xtype: "textfield",
          fieldLabel: "土地用途",
          name: "tdyt",
          value: "0",
          allowBlank: true,
          readOnly: false
        }, {
          xtype: "textfield",
          fieldLabel: "是否基本农田",
          value: "0",
          name: "sfjbnt",
          allowBlank: true,
          readOnly: false
        }, {
          colspan: 2,
          xtype: "textfield",
          fieldLabel: "实测面积",
          name: "scmj",
          value: "0",
          allowBlank: true,
          readOnly: false
        }, {
          xtype: "textfield",
          fieldLabel: "地块东至",
          name: "dkdz",
          allowBlank: true,
          readOnly: false
        }, {
          xtype: "textfield",
          fieldLabel: "地块西至",
          name: "dkxz",
          allowBlank: true,
          readOnly: false
        }, {
          xtype: "textfield",
          fieldLabel: "地块南至",
          name: "dknz",
          allowBlank: true,
          readOnly: false
        }, {
          xtype: "textfield",
          fieldLabel: "地块北至",
          name: "dkbz",
          allowBlank: true,
          readOnly: false
        }, {
          width: 430,
          colspan: 2,
          xtype: "textfield",
          fieldLabel: "地块备注信息",
          name: "dkbzxx",
          allowBlank: true,
          readOnly: false
        }, {
          margin: "7 15 10 15",
          width: 430,
          colspan: 2,
          xtype: "textfield",
          fieldLabel: "指界人姓名",
          name: "zjrxm",
          allowBlank: true,
          readOnly: false
        }]}],
  buttons: [{
      xtype: "button",
      text: '保存',
      ref: "save",
      width: 50
    }, {
      xtype: "button",
      text: '取消',
      width: 50,
      //margin : "10 10 10 20",
      handler: function(_btn) {
        _btn.up('.window').close();
      }
    }]
});