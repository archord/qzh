
Ext.define("core.cbht.view.CbhtGrid", {
  extend: "Ext.grid.Panel",
  alias: "widget.cbhtgrid",
  id: "cbhtgrid",
  store: "core.cbht.store.CbhtStore",
  border: 0,
  selModel: {
    selType: "checkboxmodel"
  },
  multiSelect: true,
  frame: true,
  tbar: [
    {xtype: 'button', text: '添加', ref: 'add', iconCls: 'table_add'}, '|',
    {xtype: 'button', text: '修改', ref: 'edit', iconCls: 'table_edit'}, '|',
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
    store: 'core.cbht.store.CbhtStore',
    dock: 'bottom',
    displayInfo: true
  },
  enableKeyNav: true, //可以使用键盘控制上下
  columnLines: true, //展示竖线
  columns: [
    {xtype: 'rownumberer'},
    {text: "承包合同编码", dataIndex: "cbhtbm", width: 120, field: {
        xtype: "textfield"
      }},
    {text: "原承包合同编码", dataIndex: "ycbhtbm", width: 120, field: {
        xtype: "textfield"
      }},
    {text: "发包方编码", dataIndex: "fbfbm", width: 120, field: {
        xtype: "textfield"
      }},
    {text: "承包方编码", dataIndex: "cbfbm", width: 120, field: {
        xtype: "textfield"
      }},
    {text: "承包方式", dataIndex: "cbfs", width: 70, field: {
        xtype: "textfield"
      }},
    {text: "承包期限起", dataIndex: "cbqxq", width: 70, field: {
        xtype: "textfield"
      }},
    {text: "承包期限止", dataIndex: "cbqxz", width: 70, field: {
        xtype: "textfield"
      }},
    {text: "同总面积", dataIndex: "htzmj", width: 70, field: {
        xtype: "textfield"
      }},
    {text: "地块总数", dataIndex: "cbdkzs", width: 70, field: {
        xtype: "textfield"
      }},
    {text: "签订时间", dataIndex: "qdsj", width: 70, field: {
        xtype: "textfield"
      }}
  ],
  initComponent: function() {
    this.callParent(arguments);
  }
});