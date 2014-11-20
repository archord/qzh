Ext.define("core.view.qzh.QzhGenerateWindow", {
  extend: 'Ext.Window',
  alias: "widget.qzhGenerateWindow",
  bodyStyle: 'background:transparent',
  title: "生成权证",
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
      xtype: "orgTreeQzhGenerate_Cbht",
      id: "OrgTreeQzhGenerate_CbhtId",
      store: Ext.create('core.store.OrgStore'),
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
      xtype: "qzhCbhtGrid",
      multiSelect: false,
//          store: Ext.create("core.store.PeopleStore", {}),
      title: "承包合同列表"
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