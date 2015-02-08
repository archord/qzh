Ext.define("core.cbf.view.CbfJtcyForm", {
  extend: 'Ext.form.Panel',
  alias: "widget.cbfJtcyForm",
  align: "left",
  id: "cbfJtcyFormId",
  labelWidth: 200,
  frame: true,
  defaults: {
    margin: "7 15 0 15",
    selectOnFocus: true
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
      readOnly: true,
      hidden: true
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
      value: 0,
      hidden: true
    }, {
      xtype: "textfield",
      fieldLabel: "承包方编码",
      name: "cbfbm",
      allowBlank: false,
      blankText: '承包方编码不能为空'
    }, {
      xtype: "textfield",
      fieldLabel: "成员姓名",
      name: "cyxm",
      allowBlank: false,
      readOnly: false
    }, {
      xtype: "combobox",
      fieldLabel: "成员性别",
      name: "cyxb",
      store: "core.combobox.store.XbdmbStore",
      displayField: 'xb',
      valueField: 'dm',
      allowBlank: false,
      readOnly: false
    }, {
      xtype: "textfield",
      fieldLabel: "成员民族",
      name: "cymz",
      allowBlank: false,
      readOnly: false
    }, {
      xtype: "combobox",
      fieldLabel: "成员证件类型",
      name: "cyzjlx",
      store: "core.combobox.store.ZjlxdmbStore",
      displayField: 'zjlx',
      valueField: 'dm',
      allowBlank: false,
      readOnly: false
    }, {
      xtype: "textfield",
      fieldLabel: "成员证件号码",
      name: "cyzjhm",
      allowBlank: false,
      readOnly: false
    }, {
      xtype: "textfield",
      fieldLabel: "与户主关系",
      name: "yhzgx",
      allowBlank: false,
      readOnly: false
    }, {
      xtype: "textfield",
      fieldLabel: "成员备注",
      name: "cybz",
      allowBlank: true,
      readOnly: false
    }, {
      xtype: "combobox",
      fieldLabel: "是否共有人",
      name: "sfgyr",
      store: "core.combobox.store.SfdmbStore",
      displayField: 'sf',
      valueField: 'dm',
      allowBlank: false,
      readOnly: false
    } ],
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