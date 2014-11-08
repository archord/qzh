/**
 * 商品数据列表视图类
 * */
Ext.define("core.view.LzhhtGrid", {
  extend: "Ext.grid.Panel",
  alias: "widget.lzhhtGrid",
  id: "lzhhtgrid",
  store: "core.store.LzhhtStore",
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
    store: 'core.store.DkStore',
    dock: 'bottom',
    displayInfo: true
  },
  enableKeyNav: true, //可以使用键盘控制上下
  columnLines: true, //展示竖线
  columns: [
    {xtype: 'rownumberer'},
    {text: "承包合同编码", dataIndex: "ycbhtbm", width: 100, field: {
        xtype: "textfield"
      }},
    {text: "流转合同编码", dataIndex: "lzhtbm", width: 100, field: {
        xtype: "textfield"
      }},
    {text: "承包方编码", dataIndex: "cbfbm", width: 70, field: {
        xtype: "textfield"
      }},
    {text: "受让方编码", dataIndex: "srfbm", width: 70, field: {
        xtype: "textfield"
      }},
    {text: "流转方式", dataIndex: "lzfs", width: 70, field: {
        xtype: "textfield"
      }},
    {text: "流转期限", dataIndex: "lzqx", width: 70, field: {
        xtype: "textfield"
      }},
    {text: "流转开始日期", dataIndex: "lzqxksrq", width: 70, field: {
        xtype: "textfield"
      }},
    {text: "流转结束日期", dataIndex: "lzqxjsrq", width: 70, field: {
        xtype: "textfield"
      }},
    {text: "流转面积", dataIndex: "lzmj", width: 70, field: {
        xtype: "textfield"
      }},
    {text: "流转地块数", dataIndex: "lzdks", width: 70, field: {
        xtype: "textfield"
      }},
    {text: "流转前土地用途", dataIndex: "lzqtdyt", width: 70, field: {
        xtype: "textfield"
      }},
    {text: "流转后土地用途", dataIndex: "lzhtdyt", width: 70, field: {
        xtype: "textfield"
      }},
    {text: "流转费用说明", dataIndex: "lzjgsm", width: 70, field: {
        xtype: "textfield"
      }},
    {text: "合同签订日期", dataIndex: "htqdrq", width: 70, field: {
        xtype: "textfield"
      }}
  ],
  initComponent: function() {
    this.callParent(arguments);
  }
});