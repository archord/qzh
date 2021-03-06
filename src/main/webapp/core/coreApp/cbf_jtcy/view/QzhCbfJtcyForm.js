Ext.define("core.cbf_jtcy.view.QzhCbfJtcyForm", {
  extend: 'Ext.form.Panel',
  alias: "widget.qzhCbfJtcyForm",
  align: "left",
  id: "qzhCbfJtcyFormId",
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
              cbhtWin = Ext.create("core.cbf_jtcy.view.QzhWindow");
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
      fieldLabel: "发证机关",
      name: "fzjg",
      allowBlank: false,
      readOnly: false
    }, {
      xtype: "datefield",
      fieldLabel: "发证日期",
      name: "fzrq",
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
      allowBlank: false,
      readOnly: false
    }, {
      xtype: "datefield",
      fieldLabel: "领取日期",
      name: "qzlqrq",
      format: 'Y-m-d',
      value: new Date(),
      allowBlank: true,
      readOnly: false
    }, {
      xtype: "textfield",
      fieldLabel: "领取人姓名",
      name: "qzlqrxm",
      allowBlank: true,
      readOnly: false
    }, {
      xtype: "combobox",
      fieldLabel: "领取人证件类型",
      name: "qzlqrzjlx",
      store: "core.combobox.store.ZjlxdmbStore",
      displayField: 'zjlx',
      valueField: 'dm',
      allowBlank: true,
      readOnly: false
    }, {
      xtype: "textfield",
      fieldLabel: "领取人证件号码",
      name: "qzlqrzjhm",
      allowBlank: true,
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