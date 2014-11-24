Ext.define("core.dk.view.DkSearchWindow", {
  extend: 'Ext.Window',
  alias: "widget.dksearchwindow",
  bodyStyle: 'background:transparent',
  title: "地块列表",
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
      xtype: "orgTreeCbhtDkSearch",
      id: "orgTreeCbht_dkSearch",
      store: Ext.create('core.dk.store.OrgStore'),
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
      xtype: "dkgrid",
      multiSelect: true,
      tbar: [],
//          store: Ext.create("core.dk.store.FbfStore", {}),
      title: "地块列表"

    }
    //使用下面的方法会报错
//    , Ext.create('core.dk.view.FbfGrid', {
//      title: "人员列表",
//      region: 'center',
//      id: "fbfGridId",
//      // iconCls:'goodtype_tree',
//      store: Ext.create('core.dk.store.FbfStore'),
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