
Ext.define("core.org.view.OrgForm", {
  extend: "Ext.form.Panel",
  alias: "widget.orgform",
  align: "left",
  frame: true,
  defaults: {
    margin: "7 0 0 15",
    selectOnFocus: true
  },
  layout: {
    type: "table",
    columns: 2
  },
  tbar: [{xtype: 'tbfill'}, {
      xtype: "button",
      ref: "save",
      iconCls: 'table_save',
      text: "保存"
    }],
  items: [{
      xtype: "textfield",
      fieldLabel: "主键",
      name: "orgId",
      value: "0",
      hidden: true
    }, {
      xtype: "textfield",
      fieldLabel: "所属区域Id",
      name: "parentId",
      value: 0,
      hidden: true
    }, {
      xtype: "textfield",
      fieldLabel: "区域级别",
      name: "isAdd",
      value: "1",
      hidden: true
    }, {
      xtype: "textfield",
      fieldLabel: "组织机构级别",
      name: "orgLevel",
      value: 1,
      hidden: true
    }, {
      colspan: 2,
      width: 430,
      xtype: "textfield",
      fieldLabel: "上级区域",
      name: "parentName",
      allowBlank: false,
      blankText: '所属区域不能为空',
      readOnly: true,
      listeners: {
        render: function(component) {
          component.getEl().on('click', function(event, el) {
//            component.setValue("TEXT");
            var win;
            if (!win) {
              win = Ext.create("core.org.view.OrgWindowAll_org");
            }
            if (win.isVisible()) {
              win.hide();
            } else {
              win.show();
            }
          });
        }
      }
    },{
      xtype: "textfield",
      fieldLabel: "组织代码",
      name: "orgCoding",
      allowBlank: false, //不允许为空
      blankText: '组织代码不能为空', //错误提示内容 
      readOnly: false
    }, {
      xtype: "textfield",
      fieldLabel: "组织名称",
      name: "orgName",
      allowBlank: false, //不允许为空
      blankText: '组织名称不能为空', //错误提示内容 
      readOnly: false
    }, {
      xtype: "textfield",
      fieldLabel: "组织成员",
      name: "orgMember",
      allowBlank: true,
      readOnly: false
    }, {
      xtype: "textfield",
      fieldLabel: "发包方名称",
      name: "fbfName",
      allowBlank: true,
      readOnly: false
    }, {
      xtype: "textfield",
      fieldLabel: "法人代表",
      name: "fbfLegalPerson",
      allowBlank: true,
      readOnly: false
    }, {
      xtype: "textfield",
      fieldLabel: "联系电话",
      name: "fbfPhone",
      allowBlank: true,
      readOnly: false
    }, {
      xtype: "textfield",
      fieldLabel: "地址",
      name: "fbfAddress",
      allowBlank: true,
      readOnly: false
    }, {
      xtype: "textfield",
      fieldLabel: "鉴证机关",
      name: "authOrgName",
      allowBlank: true,
      readOnly: false
    }, {
      xtype: "textfield",
      fieldLabel: "鉴证人",
      name: "authPeople",
      allowBlank: true,
      readOnly: false
    }, {
      xtype: "textfield",
      fieldLabel: "联系电话",
      name: "authPhone",
      allowBlank: true,
      readOnly: false
    }]
});