/**
 *  
 * */
Ext.define("core.qzh_get.view.QzhGetGrid", {
  extend: "Ext.grid.Panel",
  alias: "widget.qzhGetGrid",
  id: "qzhGetGridId",
  store: "core.qzh_get.store.CbjyqzStore",
  border: 0,
  selModel: {
    selType: "checkboxmodel"
  },
  multiSelect: true,
  frame: true,
  tbar: [
    {xtype: 'button', text: '领取权证', ref: 'add', iconCls: 'table_add'}, '|',
    {xtype: 'button', text: '查看领取详情', ref: 'edit', iconCls: 'table_edit'}, '|',
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
    store: 'core.qzh_get.store.CbjyqzStore',
    dock: 'bottom',
    displayInfo: true
  },
  enableKeyNav: true, //可以使用键盘控制上下
  columnLines: true, //展示竖线
  columns: [
    {xtype: 'rownumberer'},
    {text: "承包经营权证编码", dataIndex: "cbjyqzbm", field: {
        xtype: "textfield"
      }},
    {text: "发证机关", dataIndex: "fzjg", field: {
        xtype: "textfield"
      }},
    {text: "发证日期", dataIndex: "fzrq", field: {
        xtype: "textfield"
      }},
    {text: "是否领取", dataIndex: "qzsfly", field: {
        xtype: "textfield"
      }},
    {text: "领取日期", dataIndex: "qzlqrq", field: {
        xtype: "textfield"
      }},
    {text: "领取人姓名", dataIndex: "qzlqrxm", field: {
        xtype: "textfield"
      }},
    {text: "领取人证件类型", dataIndex: "qzlqrzjlx", field: {
        xtype: "textfield"
      }},
    {text: "领取人证件号码", dataIndex: "qzlqrzjhm", field: {
        xtype: "textfield"
      }}
  ],
  initComponent: function() {
    this.callParent(arguments);
  }
});