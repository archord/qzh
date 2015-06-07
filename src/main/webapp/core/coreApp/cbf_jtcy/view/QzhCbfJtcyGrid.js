/**
 *  
 * */
Ext.define("core.cbf_jtcy.view.QzhCbfJtcyGrid", {
  extend: "Ext.grid.Panel",
  alias: "widget.qzhCbfJtcyGrid",
  id: "qzhCbfJtcyGridId",
  store: "core.cbf_jtcy.store.CbfjtcyStore",
  border: 0,
  selModel: {
    selType: "checkboxmodel"
  },
  multiSelect: true,
  frame: true,
  tbar: [
    {xtype: 'button', text: '添加家庭成员', ref: 'add', iconCls: 'table_add'}, '|',
    {xtype: 'button', text: '查看家庭成员详情', ref: 'edit', iconCls: 'table_edit'}, '|',
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
    store: 'core.cbf_jtcy.store.CbfjtcyStore',
    dock: 'bottom',
    displayInfo: true
  },
  enableKeyNav: true, //可以使用键盘控制上下
  columnLines: true, //展示竖线
  columns: [
    {xtype: 'rownumberer'},
    {text: "承包经营权证编码", dataIndex: "cbfbm", width: 120, field: {
        xtype: "textfield"
      }},
    {text: "发包方编码", dataIndex: "cyxm", width: 120, field: {
        xtype: "textfield"
      }},
    {text: "承包方编码", dataIndex: "cyxb", width: 120, field: {
        xtype: "textfield"
      }},
    {text: "承包方式", dataIndex: "cymz", width: 70, field: {
        xtype: "textfield"
      }},
    {text: "承包期限", dataIndex: "cyzjlx", width: 70, field: {
        xtype: "textfield"
      }},
    {text: "承包期限起", dataIndex: "cyzjhm", width: 70, field: {
        xtype: "textfield"
      }},
    {text: "承包期限止", dataIndex: "yhzgx", width: 70, field: {
        xtype: "textfield"
      }},
    {text: "承包期限起", dataIndex: "cybz", width: 70, field: {
        xtype: "textfield"
      }},
    {text: "承包期限止", dataIndex: "sfgyr", width: 70, field: {
        xtype: "textfield"
      }}
  ],
  initComponent: function() {
    this.callParent(arguments);
  }
});