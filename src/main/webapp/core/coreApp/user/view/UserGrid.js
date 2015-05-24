/**
 *  
 * */
Ext.define("core.user.view.UserGrid", {
  extend: "Ext.grid.Panel",
  alias: "widget.usergrid",
  id: "usergridId",
  store: "core.user.store.UserStore",
  border: 0,
  selModel: {
    selType: "checkboxmodel"
  },
  multiSelect: true,
  frame: true,
  tbar: [
    {xtype: 'button', text: '添加用户', ref: 'add', iconCls: 'table_add'}, '|',
    {xtype: 'button', text: '查看用户详细', ref: 'edit', iconCls: 'table_edit'}, '|',
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
    store: 'core.user.store.UserStore',
    dock: 'bottom',
    displayInfo: true
  },
  enableKeyNav: true, //可以使用键盘控制上下
  columnLines: true, //展示竖线
  columns: [
    {xtype: 'rownumberer'},
    {text: "发包方编码", dataIndex: "userbm", width: 100, field: {
        xtype: "textfield"
      }},
    {text: "发包方名称", dataIndex: "usermc", width: 100, field: {
        xtype: "textfield"
      }},
    {text: "负责人名称", dataIndex: "userfzrxm", width: 100, field: {
        xtype: "textfield"
      }},
    {text: "负责人证件类型", dataIndex: "fzrzjlx", width: 90, field: {
        xtype: "textfield"
      }},
    {text: "负责人证件号码", dataIndex: "fzrzjhm", width: 100, field: {
        xtype: "textfield"
      }},
    {text: "联系电话", dataIndex: "lxdh", width: 80, field: {
        xtype: "textfield"
      }},
    {text: "发包方地址", dataIndex: "userdz", width: 150, field: {
        xtype: "textfield"
      }},
    {text: "邮政编码", dataIndex: "yzbm", width: 70, field: {
        xtype: "textfield"
      }},
    {text: "发包方调查员", dataIndex: "userdcy", width: 80, field: {
        xtype: "textfield"
      }},
    {text: "发包方调查日期", dataIndex: "userdcrq", width: 80, field: {
        xtype: "textfield"
      }}
  ],
  initComponent: function() {
    this.callParent(arguments);
  }
});