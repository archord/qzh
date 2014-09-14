/**
 * 程序布局放大中间的部分
 */
Ext.define("core.view.CenterView", {
  extend: 'Ext.tab.Panel',
  alias: 'widget.centerview',
  id: 'centerview-id',
  //margins: '2 0 0 0',
  border: 0,
  x: 0,
  bodyStyle: 'padding:0px',
  menuAlign: "center",
  items: [{
      title: '<center height=40>首页</center>',
      iconCls: 'home',
      bodyPadding: 5,
      layout: 'fit',
      items: [{
          html: "农村土地承包经营权证管理系统"
        }],
      tabConfig: {//标签配置参数

      }
    }
//    , {
//      title: '<center height=40>系统管理</center>',
//      bodyPadding: 5,
//      layout: 'border',
//      xtype: 'orgLayout'
//    }
  ],
  initComponent: function() {
    this.callParent(arguments);
  }
});