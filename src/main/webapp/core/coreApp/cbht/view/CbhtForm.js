Ext.define("core.cbht.view.CbhtForm", {
  extend: 'Ext.form.Panel',
  alias: "widget.cbhtform",
  align: "left",
  id: "cbhtformid",
  frame: true,
//  xtype: "form",
//  ref: "cbhtform",
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
  tbar: [{
      xtype: "button",
      ref: "save",
      iconCls: 'table_save',
      text: "保存"
    }],
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
      fieldLabel: "承包合同编码",
      name: "cbhtbm",
      allowBlank: false,
      blankText: '承包合同编码不能为空'
    }, {
      xtype: "textfield",
      fieldLabel: "原承包合同编码",
      name: "ycbhtbm",
      allowBlank: false,
      blankText: '原承包合同编码不能为空'
    }, {
      xtype: "textfield",
      fieldLabel: "发包方编码",
      name: "fbfbm",
      allowBlank: false,
      blankText: '发包方编码不能为空',
      readOnly: false,
      listeners: {
        render: function(component) {
          component.getEl().on('click', function(event, el) {
//            component.setValue("TEXT");
            var cbhtWin;
            if (!cbhtWin) {
              cbhtWin = Ext.create("core.cbht.view.FbfWindow_cbht");
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
      fieldLabel: "承包方编码",
      name: "cbfbm",
      allowBlank: false,
      blankText: '承包方编码不能为空',
      readOnly: false,
      listeners: {
        render: function(component) {
          component.getEl().on('click', function(event, el) {
//            component.setValue("TEXT");
            var cbhtWin;
            if (!cbhtWin) {
              cbhtWin = Ext.create("core.cbht.view.CbfWindow_cbht");
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
      xtype: "datefield",
      fieldLabel: "承包期限起",
      name: "cbqxq",
      format: 'Y-m-d',
      allowBlank: true,
      readOnly: false
    }, {
      xtype: "datefield",
      fieldLabel: "承包期限止",
      name: "cbqxz",
      format: 'Y-m-d',
      allowBlank: true,
      readOnly: false
    }, {
      xtype: "textfield",
      fieldLabel: "承包合同总面积",
      name: "htzmj",
      allowBlank: true,
      readOnly: false
    }, {
      xtype: "textfield",
      fieldLabel: "承包地块总数",
      name: "cbdkzs",
      allowBlank: true,
      readOnly: false
    }, {
      xtype: "combobox",
      fieldLabel: "承包方式",
      name: "cbfs",
      store: "core.combobox.store.CbjyqqdfsdmbStore",
      displayField: 'qdfs',
      valueField: 'dm',
      allowBlank: true,
      readOnly: false
    }, {
      margin: "7 15 10 15",
      xtype: "datefield",
      fieldLabel: "签订时间",
      name: "qdsj",
      value: new Date(),
      format: 'Y-m-d',
      allowBlank: true,
      readOnly: false
    }]
});