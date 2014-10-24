Ext.define("core.view.CbhtWindow", {
  extend: 'Ext.window.Window',
  alias: "widget.cbhtwindow",
  align: "left",
  frame: true,
  bodyStyle: 'background:transparent',
  title: "添加承包合同",
//  layout: "border",
  listeners: {
    show: function(_this) {
      if (_this.extraParas) {
      } else {
      }
    }
  },
  items: [{
      //xtype : 'panel',
      region: 'center',
      // iconCls:'goodtype_tree',
      margins: '5 0 5 0',
      height: 220,
      width: 750,
//      collapsible: true, // 可以被折叠
      xtype: "cbhtform"
    }, {
      // iconCls:'good_table',
//      collapsible: true, // 可以被折叠
      //xtype : 'panel',
      region: 'south ',
      height: 200,
      width: 750,
      margins: '5 0 5 0',
      xtype: "cbhtdkgrid",
      bbar: [],
//          store: Ext.create("core.store.PeopleStore", {}),
      title: "承包地块"

    }],
  buttons: [{
      xtype: "button",
      text: '保存',
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