/**北部*/
Ext.define("core.main.view.TopView", {
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
      width: 1920,
      heigh: 70,
      bodyStyle: {
//        background: '#7598e0',
        backgroundImage: 'url(/qzh/images/head_bg.png)',
        textAlign: 'right',
        border: 0,
        paddingTop: '25px',
        paddingRight: '10px'
      },
//      html: "<h1><font color=white size=5>&nbsp;&nbsp;&nbsp;&nbsp;Extjs4</font></h1>"
      html: "<div style=\"width:450px;\"><h3><font color=white size=4>&nbsp;&nbsp;&nbsp;&nbsp;农村土地承包经营权管理系统</font></h2></div>"
    }, {
      layout: {
        type: 'hbox',
        pack: 'start',
        align: 'stretch'
      },
      region: 'east',
      bodyStyle: {
        background: 'transparent !important;',
        border: 0,
        padding: '10px',
        color: 'white'
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
