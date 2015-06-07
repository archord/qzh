/**
 *  
 * */
Ext.define("core.qzh_cancel.view.QzhCancelGrid", {
  extend: "Ext.grid.Panel",
  alias: "widget.qzhCancelGrid",
  id: "qzhCancelGridId",
  store: "core.qzh_cancel.store.CbjyqzQzzxStore",
  border: 0,
  selModel: {
    selType: "checkboxmodel"
  },
  multiSelect: true,
  frame: true,
  tbar: [
    {xtype: 'button', text: '注销权证', ref: 'add', iconCls: 'table_add'}, '|',
    {xtype: 'button', text: '查看注销详情', ref: 'edit', iconCls: 'table_edit'}, '|',
    {xtype: 'button', text: '删除', ref: 'del', iconCls: 'table_remove'},
//    "->",
//    '按名称查询:',
//    {
//      xtype: 'triggerfield',
//      triggerCls: Ext.baseCSSPrefix + 'form-search-trigger',
//      listeners: {
//        "change": function(_this, _new, _old, _opt) {
//          var _store = _this.ownerCt.ownerCt.getStore();
//          _store.clearFilter(false);
//          _store.filter("name", _new);
//        }
//      },
//      onTriggerClick: function() {
//        var _store = this.ownerCt.ownerCt.getStore();
//        _store.clearFilter(false);
//        _store.filter("name", this.getValue());
//      }
//    },
//    '按编号查询:',
//    {
//      xtype: 'triggerfield',
//      triggerCls: Ext.baseCSSPrefix + 'form-search-trigger',
//      listeners: {
//        "change": function(_this, _new, _old, _opt) {
//          var _store = _this.ownerCt.ownerCt.getStore();
//          _store.clearFilter(false);
//          _store.filter("id", _new);
//        }
//      },
//      onTriggerClick: function() {
//        var _store = this.ownerCt.ownerCt.getStore();
//        _store.clearFilter(false);
//        _store.filter("id", this.getValue());
//      }
//    }
  ],
  bbar: {
    xtype: 'pagingtoolbar',
    store: 'core.qzh_cancel.store.CbjyqzQzzxStore',
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
    {text: "注销原因", dataIndex: "zxyy", field: {
        xtype: "textfield"
      }},
    {text: "注销日期", dataIndex: "zxrq", field: {
        xtype: "textfield"
      }}
  ],
  initComponent: function() {
    this.callParent(arguments);
  }
});