Ext.define("core.cbht.view.CbfWindow", {
  extend: 'Ext.Window',
  alias: "widget.cbfwindow",
  bodyStyle: 'background:transparent',
  title: "承包方列表",
  layout: "border",
  width: 600,
  height: 300,
  closeAction: 'hide',
  listeners: {
    show: function(_this) {
      if (_this.extraParas) {
      } else {
      }
    }
  },
  items: [{
      title: "组织机构列表",
      region: 'west',
      // iconCls:'goodtype_tree',
      xtype: "orgTreeCbht",
      id: "orgTreeCbht_cbf",
      store: Ext.create('core.cbf.store.OrgStore'),
      margins: '5 2 5 5',
      width: 150,
      split: true,
      collapsible: true,
      floatable: false
    }, {
      // iconCls:'good_table',
      collapsible: true, // 可以被折叠
      //xtype : 'panel',
      region: 'center',
      height: 300,
      margins: '5 0 5 0',
      xtype: "cbfgrid",
      multiSelect: false,
      tbar: [],
//          store: Ext.create("core.cbf.store.FbfStore", {}),
      title: "人员列表"

    }
    //使用下面的方法会报错
//    , Ext.create('core.cbf.view.FbfGrid', {
//      title: "人员列表",
//      region: 'center',
//      id: "fbfGridId",
//      // iconCls:'goodtype_tree',
//      store: Ext.create('core.cbf.store.FbfStore'),
//      height: 300,
//      margins: '5 0 5 0',
//      multiSelect: false,
//      tbar: []
//    })
  ],
  buttons: [{
      xtype: "button",
      text: '选择',
      ref: "save",
      width: 50
    }, {
      xtype: "button",
      text: '取消',
      width: 50,
      //margin : "10 10 10 20",
      handler: function(_btn) {
        _btn.up('.window').close();
      }
    }]
});