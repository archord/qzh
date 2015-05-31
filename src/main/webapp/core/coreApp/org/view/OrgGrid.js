/**
 *  
 * */
Ext.define("core.org.view.OrgGrid", {
  extend: "Ext.grid.Panel",
  alias: "widget.orggrid",
  id: "orggridId",
  store: "core.org.store.OrgStore",
  border: 0,
  selModel: {
    selType: "checkboxmodel"
  },
  multiSelect: true,
  frame: true,
  tbar: [
    {xtype: 'button', text: '添加机构', ref: 'add', iconCls: 'table_add'}, '|',
    {xtype: 'button', text: '查看详细', ref: 'edit', iconCls: 'table_edit'}, '|',
    {xtype: 'button', text: '删除', ref: 'del', iconCls: 'table_remove'},
    "->",
    '按名称查询:',
    {
      xtype: 'triggerfield',
      triggerCls: Ext.baseCSSPrefix + 'form-search-trigger',
      listeners: {
        "change": function(_this, _new, _old, _opt) {
          var _store = _this.ownerCt.ownerCt.getStore();
          _store.clearFilter(false);
          _store.filter("name", _new);
        }
      },
      onTriggerClick: function() {
        var _store = this.ownerCt.ownerCt.getStore();
        _store.clearFilter(false);
        _store.filter("name", this.getValue());
      }
    },
    '按编号查询:',
    {
      xtype: 'triggerfield',
      triggerCls: Ext.baseCSSPrefix + 'form-search-trigger',
      listeners: {
        "change": function(_this, _new, _old, _opt) {
          var _store = _this.ownerCt.ownerCt.getStore();
          _store.clearFilter(false);
          _store.filter("id", _new);
        }
      },
      onTriggerClick: function() {
        var _store = this.ownerCt.ownerCt.getStore();
        _store.clearFilter(false);
        _store.filter("id", this.getValue());
      }
    }
  ],
  bbar: {
    xtype: 'pagingtoolbar',
    store: 'core.org.store.OrgStore',
    dock: 'bottom',
    displayInfo: true
  },
  enableKeyNav: true, //可以使用键盘控制上下
  columnLines: true, //展示竖线
  columns: [
    {xtype: 'rownumberer'},
//    {text: "组织Id", dataIndex: "orgId", width: 100, field: {xtype: "textfield"}},
    {text: "组织代码", dataIndex: "orgCoding", width: 100, field: {xtype: "textfield"}},
    {text: "组织名称", dataIndex: "orgName", width: 100, field: {xtype: "textfield"}},
    {text: "上级名称", dataIndex: "parentName", width: 100, field: {xtype: "textfield"}},
    {text: "组织成员", dataIndex: "orgMember", width: 100, field: {xtype: "textfield"}},
    {text: "发包方名称", dataIndex: "fbfName", width: 100, field: {xtype: "textfield"}},
    {text: "法人代表", dataIndex: "fbfLegalPerson", width: 100, field: {xtype: "textfield"}},
    {text: "联系电话", dataIndex: "fbfPhone", width: 100, field: {xtype: "textfield"}},
    {text: "地址", dataIndex: "fbfAddress", width: 150, field: {xtype: "textfield"}},
    {text: "签证机关", dataIndex: "authOrgName", width: 100, field: {xtype: "textfield"}},
    {text: "签证人", dataIndex: "authPeople", width: 100, field: {xtype: "textfield"}},
    {text: "联系电话", dataIndex: "authPhone", width: 100, field: {xtype: "textfield"}}
  ],
  initComponent: function() {
    this.callParent(arguments);
  }
});