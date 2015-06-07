/**
 *  
 * */
Ext.define("core.qzh_reissue.view.QzhReissueGrid", {
  extend: "Ext.grid.Panel",
  alias: "widget.qzhReissueGrid",
  id: "qzhReissueGridId",
  store: "core.qzh_reissue.store.CbjyqzQzbfStore",
  border: 0,
  selModel: {
    selType: "checkboxmodel"
  },
  multiSelect: true,
  frame: true,
  tbar: [
    {xtype: 'button', text: '补发权证', ref: 'add', iconCls: 'table_add'}, '|',
    {xtype: 'button', text: '查看补发详情', ref: 'edit', iconCls: 'table_edit'}, '|',
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
    store: 'core.qzh_reissue.store.CbjyqzQzbfStore',
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
    {text: "补发原因", dataIndex: "qzbfyy", field: {
        xtype: "textfield"
      }},
    {text: "补发日期", dataIndex: "bfrq", field: {
        xtype: "textfield"
      }},
    {text: "领取日期", dataIndex: "qzbflqrq", field: {
        xtype: "textfield"
      }},
    {text: "领取人姓名", dataIndex: "qzbflqrxm", field: {
        xtype: "textfield"
      }},
    {text: "领取人证件类型", dataIndex: "bflqrzjlx", field: {
        xtype: "textfield"
      }},
    {text: "领取人证件号码", dataIndex: "bflqrzjhm", field: {
        xtype: "textfield"
      }}
  ],
  initComponent: function() {
    this.callParent(arguments);
  }
});