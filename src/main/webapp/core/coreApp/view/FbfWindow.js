Ext.define("core.view.FbfWindow", {
  extend: 'Ext.Window',
  alias: "widget.fbfwindow",
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
      title: "地区列表",
      region: 'west',
      // iconCls:'goodtype_tree',
      xtype: "orgTreeCbht",
      id: "orgTreeCbht_fbf",
      store: Ext.create('core.store.OrgStore'),
      margins: '5 2 5 5',
      width: 150,
      split: true,
      collapsible: true,
      floatable: false
    }, Ext.create('core.view.PeopleGrid', {
      title: "人员列表",
      region: 'center',
      id: "peopleGridId",
      // iconCls:'goodtype_tree',
      store: Ext.create('core.store.PeopleStore'),
      height: 300,
      margins: '5 0 5 0',
      multiSelect: false,
      tbar: []
    })],
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