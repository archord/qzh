/**北部*/
Ext.define("core.view.TopView", {
  extend: "Ext.panel.Panel",
  alias: 'widget.topview',
  id: "topview",
  height: 50,
  bodyStyle: {
    background: '#7598e0'
  },
  layout: "border",
  items: [{
      region: 'west',
      width: 450,
      bodyStyle: {
        background: '#7598e0',
        border: 0,
        padding: '10px'
      },
      html: "<h1><font color=white size=5>&nbsp;&nbsp;&nbsp;&nbsp;农村土地承包经营权管理系统</font></h1>"
    }, {
      layout: {
        type: 'hbox',
        pack: 'start',
        align: 'stretch'
      },
      region: 'east',
      bodyStyle: {
        background: '#7598e0',
        border: 0,
        padding: '10px'
      },
      items: [{
          padding: '0 20 0 0',
          ref: "logininfo",
          xtype: "displayfield",
          id: "displaylogin",
          value: "<font color=white><b>未登录</b></font>"
        }, {
          width: 60,
          margin: '0 20 0 0',
          xtype: "button",
          ref: "logout",
          text: "注销"
        }, {
          width: 80,
          margin: '0 20 0 0',
          xtype: "button",
          ref: "exit",
          text: "退出系统"
        }]
    }]
});
