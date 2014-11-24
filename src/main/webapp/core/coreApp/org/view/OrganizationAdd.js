
Ext.define('core.org.view.OrganizationAdd', {
  extend: 'Ext.panel.Panel',
  title: "增加县级组织机构",
//	iconCls:'table_login',
//  width: 600,
//  height: 450,
  alias: "widget.addOrganization",
  modal: true,
//  closable: true,
  closeAction: "destroy",
  //layout : "column",

  items: [{
      border: false,
      columnWidth: .7,
      xtype: "form",
      ref: "orgAddForm",
      layout: {
        type: "table",
        columns: 2
      },
      align: "left",
      defaults: {
        margin: "10 15 10 15",
        selectOnFocus: true,
        labelWidth: 80,
        width: 200,
        msgTarget: "side" //提示信息现在的位置
      },
      items: [{
          xtype: "textfield",
          fieldLabel: "主键",
          name: "orgId",
          value: "",
          hidden: true
        }, {
          xtype: "textfield",
          fieldLabel: "组织机构级别",
          name: "orgLevel",
          value: 1 ,
          hidden: true
        }, {
          xtype: "textfield",
          fieldLabel: "组织机构级别",
          name: "parentId",
          value: 0 ,
          hidden: true
        }, {
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
          colspan: 2,
          width: 430,
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
          colspan: 2,
          width: 430,
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
    }
//    , {
//      colspan: 2,
//      xtype: "button",
//      text: '保存',
//      ref: "save",
//      width: 50
//    }
  ],
  buttons: [{
      xtype: "button",
      text: '保存',
      ref: "save",
      width: 50
    }]
});
