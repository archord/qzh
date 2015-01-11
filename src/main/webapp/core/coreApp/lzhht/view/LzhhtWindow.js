Ext.define("core.lzhht.view.LzhhtWindow", {
  extend: 'Ext.window.Window',
  alias: "widget.lzhhtwindow",
  align: "left",
  frame: true,
  bodyStyle: 'background:transparent',
  title: "添加地块信息",
  listeners: {
    show: function(_this) {
      if (_this.extraParas) {
        _this.down("form").getForm().findField("orgName").setValue("修改地块信息");
        _this.down("form").getForm().findField("isAdd").setValue(0);
        _this.down("form").getForm().findField("orgId").setValue(_this.extraParas.lzhht.orgId);
        _this.down("form").getForm().findField("orgLevel").setValue(_this.extraParas.orgLevel);
        _this.down("form").getForm().findField("id").setValue(_this.extraParas.lzhht.id);
        _this.down("form").getForm().findField("ycbhtbm").setValue(_this.extraParas.lzhht.ycbhtbm);
        _this.down("form").getForm().findField("lzhtbm").setValue(_this.extraParas.lzhht.lzhtbm);
        _this.down("form").getForm().findField("cbfbm").setValue(_this.extraParas.lzhht.cbfbm);
        _this.down("form").getForm().findField("srfbm").setValue(_this.extraParas.lzhht.srfbm);
        _this.down("form").getForm().findField("lzfs").setValue(_this.extraParas.lzhht.lzfs);
        _this.down("form").getForm().findField("lzqx").setValue(_this.extraParas.lzhht.lzqx);
        _this.down("form").getForm().findField("lzqxksrq").setValue(_this.extraParas.lzhht.lzqxksrq);
        _this.down("form").getForm().findField("lzqxjsrq").setValue(_this.extraParas.lzhht.lzqxjsrq);
        _this.down("form").getForm().findField("lzmj").setValue(_this.extraParas.lzhht.lzmj);
        _this.down("form").getForm().findField("lzdks").setValue(_this.extraParas.lzhht.lzdks);
        _this.down("form").getForm().findField("lzqtdyt").setValue(_this.extraParas.lzhht.lzqtdyt);
        _this.down("form").getForm().findField("lzhtdyt").setValue(_this.extraParas.lzhht.lzhtdyt);
        _this.down("form").getForm().findField("lzjgsm").setValue(_this.extraParas.lzhht.lzjgsm);
        _this.down("form").getForm().findField("htqdrq").setValue(_this.extraParas.lzhht.htqdrq);
      } else {
        var orgTree = Ext.getCmp("orgTreeLzhht");
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
      ref: "lzhhtform",
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
          fieldLabel: "承包合同编码",
          name: "ycbhtbm",
          allowBlank: false,
          blankText: '承包合同编码不能为空'
        }, {
          xtype: "textfield",
          fieldLabel: "流转合同编码",
          name: "lzhtbm",
          allowBlank: false,
          blankText: '流转合同编码不能为空',
          readOnly: false
        }, {
          xtype: "textfield",
          fieldLabel: "承包方编码",
          name: "cbfbm",
          allowBlank: false,
          blankText: '承包方编码不能为空',
          readOnly: false
        }, {
          xtype: "textfield",
          fieldLabel: "受让方编码",
          name: "srfbm",
          allowBlank: false,
          blankText: '受让方编码不能为空',
          readOnly: false
        }, {
          xtype: "combobox",
          fieldLabel: "流转方式",
          name: "lzfs",
          store: "core.combobox.store.CbjyqqdfsdmbStore",
          displayField: 'qdfs',
          valueField: 'dm',
          allowBlank: true,
          readOnly: false
        }, {
          xtype: "textfield",
          fieldLabel: "流转期限",
          name: "lzqx",
          allowBlank: true,
          readOnly: false
        }, {
          xtype: "datefield",
          fieldLabel: "流转开始日期",
          name: "lzqxksrq",
          format: 'Y-m-d',
          allowBlank: true,
          readOnly: false
        }, {
          xtype: "datefield",
          fieldLabel: "流转结束日期",
          name: "lzqxjsrq",
          format: 'Y-m-d',
          allowBlank: true,
          readOnly: false
        }, {
          xtype: "textfield",
          fieldLabel: "流转面积",
          name: "lzmj",
          value: "0",
          allowBlank: true,
          readOnly: false
        }, {
          xtype: "textfield",
          fieldLabel: "流转地块数",
          value: "0",
          name: "lzdks",
          allowBlank: true,
          readOnly: false
        }, {
          xtype: "combobox",
          fieldLabel: "流转前土地用途",
          name: "lzqtdyt",
          store: "core.combobox.store.TdytdmbStore",
          displayField: 'tdytd',
          valueField: 'dm',
          allowBlank: true,
          readOnly: false
        }, {
          xtype: "combobox",
          fieldLabel: "流转后土地用途",
          name: "lzhtdyt",
          store: "core.combobox.store.TdytdmbStore",
          displayField: 'tdytd',
          valueField: 'dm',
          allowBlank: true,
          readOnly: false
        }, {
          width: 430,
          colspan: 2,
          xtype: "textfield",
          fieldLabel: "流转费用说明",
          name: "lzjgsm",
          allowBlank: true,
          readOnly: false
        }, {
          colspan: 2,
          xtype: "datefield",
          fieldLabel: "合同签订日期",
          name: "htqdrq",
          format: 'Y-m-d',
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