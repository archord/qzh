
Ext.define("core.qzh.view.QzhCbhtGrid", {
  extend: "Ext.grid.Panel",
  alias: "widget.qzhCbhtGrid",
  id: "qzhCbhtGridId",
  store: "core.qzh.store.CbhtStore_qz",
  border: 0,
  selModel: {
    selType: "checkboxmodel"
  },
  multiSelect: true,
  frame: true,
  tbar: [
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
    store: 'core.qzh.store.CbhtStore_qz',
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
    {text: "发包方编码", dataIndex: "fbfbm", width: 120, field: {
        xtype: "textfield"
      }},
    {text: "承包方编码", dataIndex: "cbfbm", width: 120, field: {
        xtype: "textfield"
      }},
    {text: "承包方姓名", dataIndex: "cbfmc", width: 70, field: {
        xtype: "textfield"
      }}
  ],
  initComponent: function() {
    this.callParent(arguments);
  }
});