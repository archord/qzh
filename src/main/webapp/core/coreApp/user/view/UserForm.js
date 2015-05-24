Ext.apply(Ext.form.VTypes, {
    password : function(val, field) {
        if (field.initialPassField) {
            var pwd = Ext.getCmp(field.initialPassField);
            return (val == pwd.getValue());
        }
        return true;
    },
    passwordText : '两次输入的密码必须相同！'
});
Ext.define("core.user.view.UserForm", {
  extend: "Ext.form.Panel",
  alias: "widget.userform",
  align: "left",
  frame: true,
  defaults: {
    margin: "7 0 0 15",
    selectOnFocus: true
  },
  layout: {
    type: "table",
    columns: 1
  },
  tbar: [{xtype: 'tbfill'},{
      xtype: "button",
      ref: "save",
      iconCls: 'table_save',
      text: "保存"
    }],
  items: [{
      xtype: "textfield",
      fieldLabel: "所属区域",
      name: "orgName",
      allowBlank: false,
      blankText: '所属区域不能为空',
      readOnly: true,
      listeners: {
        render: function(component) {
          component.getEl().on('click', function(event, el) {
//            component.setValue("TEXT");
            var win;
            if (!win) {
              win = Ext.create("core.main.view.OrgWindow");
            }
            if (win.isVisible()) {
              win.hide();
            } else {
              win.show();
            }
          });
        }
      }
    }, {
      xtype: "textfield",
      fieldLabel: "区域级别",
      name: "isAdd",
      value: "1",
      hidden: true
    }, {
      xtype: "textfield",
      fieldLabel: "区域等级",
      name: "orgLevel",
      value: "0",
      hidden: true
    }, {
      xtype: "textfield",
      fieldLabel: "主键",
      name: "id",
      value: "",
      hidden: true
    }, {
      xtype: "textfield",
      fieldLabel: "组织机构id",
      name: "orgId",
      value: "",
      hidden: true
    }, {
      xtype: "textfield",
      fieldLabel: "用户名",
      name: "name",
      allowBlank: false,
      blankText: '用户名不能为空'
    }, {
      xtype: "textfield",
      inputType: 'password',
      vtype: 'password',
      fieldLabel: "密码",
      name: "password",
      id: "password",
      allowBlank: false,
      blankText: '密码不能为空',
      readOnly: false
    }, {
      xtype: "textfield",
      inputType: 'password',
      vtype: 'password',
      fieldLabel: "再次输入密码",
      name: "password2",
      id: "password2",
      allowBlank: false,
      readOnly: false,
      initialPassField: 'password'
    }]
});