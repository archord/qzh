Ext.define("core.qzh_renew.view.QzhRenewForm", {
  extend: 'Ext.form.Panel',
  alias: "widget.qzhRenewForm",
  align: "left",
  id: "qzhRenewFormId",
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
      fieldLabel: "承包经营权证（登记簿）编码",
      name: "cbjyqzbm",
      allowBlank: false,
      blankText: '承包经营权证（登记簿）编码不能为空',
      listeners: {
        render: function(component) {
          component.getEl().on('click', function(event, el) {
//            component.setValue("TEXT");
            var cbhtWin;
            if (!cbhtWin) {
              cbhtWin = Ext.create("core.qzh_renew.view.QzhWindow_get");
            }
            if (cbhtWin.isVisible()) {
              cbhtWin.hide();
            } else {
              cbhtWin.show();
            }
          });
        }
      }
    }, {
      xtype: "textfield",
      fieldLabel: "换发原因",
      name: "qzhfyy",
      allowBlank: false,
      readOnly: false
    }, {
      xtype: "datefield",
      fieldLabel: "换发日期",
      name: "hfrq",
      format: 'Y-m-d',
      allowBlank: false,
      readOnly: false
    }, {
      xtype: "combobox",
      fieldLabel: "是否领取",
      name: "qzsfly",
      store: "core.combobox.store.SfdmbStore",
      displayField: 'sf',
      valueField: 'dm',
      allowBlank: true,
      readOnly: false,
      hidden: true
    }, {
      xtype: "datefield",
      fieldLabel: "领取日期",
      name: "qzhflqrq",
      format: 'Y-m-d',
      value: new Date(),
      allowBlank: false,
      readOnly: false
    }, {
      xtype: "textfield",
      fieldLabel: "领取人姓名",
      name: "qzhflqrxm",
      allowBlank: false,
      readOnly: false
    }, {
      xtype: "combobox",
      fieldLabel: "领取人证件类型",
      name: "hflqrzjlx",
      store: "core.combobox.store.ZjlxdmbStore",
      displayField: 'zjlx',
      valueField: 'dm',
      allowBlank: false,
      readOnly: false
    }, {
      xtype: "textfield",
      fieldLabel: "领取人证件号码",
      name: "hflqrzjhm",
      allowBlank: false,
      readOnly: false
    }],
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