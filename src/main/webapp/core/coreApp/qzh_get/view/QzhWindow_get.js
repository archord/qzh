Ext.define("core.qzh_get.view.QzhWindow_get", {
  extend: 'Ext.Window',
  alias: "widget.qzhWindow_get",
  id: "qzhWindowId_get",
  bodyStyle: 'background:transparent',
  title: "选择权证",
  layout: "border",
  width: 700,
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
      xtype: "orgTreeQzh_QzhGet",
      id: "orgTreeQzhGet_QzhId",
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
      xtype: "qzhGetGrid_Qzh",
      multiSelect: false,
//          store: Ext.create("core.qzh_get.store.FbfStore", {}),
      title: "权证列表"
    }
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