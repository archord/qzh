/**
 *  
 * */
Ext.define("core.dk.view.DkGrid", {
  extend: "Ext.grid.Panel",
  alias: "widget.dkgrid",
  id: "dkgrid",
  store: "core.dk.store.DkStore",
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
    store: 'core.dk.store.DkStore',
    dock: 'bottom',
    displayInfo: true
  },
  enableKeyNav: true, //可以使用键盘控制上下
  columnLines: true, //展示竖线
  columns: [
    {xtype: 'rownumberer'},
    {text: "地块编码", dataIndex: "dkbm", width: 100, field: {
        xtype: "textfield"
      }},
    {text: "地块名称", dataIndex: "dkmc", width: 100, field: {
        xtype: "textfield"
      }},
    {text: "地块类别", dataIndex: "dklb", width: 70, field: {
        xtype: "textfield"
      }},
    {text: "土地用途", dataIndex: "tdyt", width: 70, field: {
        xtype: "textfield"
      }},
    {text: "实测面积", dataIndex: "scmj", width: 70, field: {
        xtype: "textfield"
      }},
    {text: "地块东至", dataIndex: "dkdz", width: 70, field: {
        xtype: "textfield"
      }},
    {text: "地块西至", dataIndex: "dkxz", width: 70, field: {
        xtype: "textfield"
      }},
    {text: "地块南至", dataIndex: "dknz", width: 70, field: {
        xtype: "textfield"
      }},
    {text: "地块北至", dataIndex: "dkbz", width: 70, field: {
        xtype: "textfield"
      }}
  ],
  initComponent: function() {
    this.callParent(arguments);
  }
});