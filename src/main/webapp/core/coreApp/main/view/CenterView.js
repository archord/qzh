/**
 * 程序布局放大中间的部分
 */
Ext.define("core.main.view.CenterView", {
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
          html: "<div style=\"margin: 20 10 20 10;\"><p class=\"MsoNormal\" style=\"MARGIN: 0cm 0cm 0pt; TEXT-ALIGN: center\" align=\"center\"><b><span style=\"COLOR: #122e6d; FONT-FAMILY: 宋体; mso-ascii-font-family: 'Times New Roman'; mso-hansi-font-family: 'Times New Roman'; mso-bidi-font-size: 10.5pt\"><font size=\"3\">中华人民共和国农村土地承包经营权证管理办法</font></span></b></p>\n\
\n\
\n\
<p style=\"MARGIN: 4.5pt 2.25pt 0pt; TEXT-INDENT: 22.5pt; LINE-HEIGHT: 15pt; font-size: 11pt;\">第一条 为稳定和完善农村土地承包关系，维护承包方依法取得的土地承包经营权，加强农村土地承包经营权证管理，根据《中华人民共和国农村土地承包法》，制定本办法。</p>\n\
<p style=\"MARGIN: 4.5pt 2.25pt 0pt; TEXT-INDENT: 22.5pt; LINE-HEIGHT: 15pt; font-size: 11pt;\">第二条 农村土地承包经营权证是农村土地承包合同生效后，国家依法确认承包方享有土地承包经营权的法律凭证。农村土地承包经营权证只限承包方使用。</p>\n\
<p style=\"MARGIN: 4.5pt 2.25pt 0pt; TEXT-INDENT: 22.5pt; LINE-HEIGHT: 15pt; font-size: 11pt;\">第三条 承包耕地、园地、荒山、荒沟、荒丘、荒滩等农村土地从事种植业生产活动，承包方依法取得农村土地承包经营权后，应颁发农村土地承包经营权证予以确认。承包草原、水面、滩涂从事养殖业生产活动的，依照《中华人民共和国草原法》、《中华人民共和国渔业法》等有关规定确权发证。</p>\n\
<p style=\"MARGIN: 4.5pt 2.25pt 0pt; TEXT-INDENT: 22.5pt; LINE-HEIGHT: 15pt; font-size: 11pt;\">第四条 实行家庭承包经营的承包方，由县级以上地方人民政府颁发农村土地承包经营权证。实行其它方式承包经营的承包方，经依法登记，由县级以上地方人民政府颁发农村土地承包经营权证。县级以上地方人民政府农业行政主管部门负责农村土地承包经营权证的备案、登记、发放等具体工作。</p>\n\
<p style=\"MARGIN: 4.5pt 2.25pt 0pt; TEXT-INDENT: 22.5pt; LINE-HEIGHT: 15pt; font-size: 11pt;\">第五条 农村土地承包经营权证所载明的权利有效期限，应与依法签订的土地承包合同约定的承包期一致。</p>\n\
<p style=\"MARGIN: 4.5pt 2.25pt 0pt; TEXT-INDENT: 22.5pt; LINE-HEIGHT: 15pt; font-size: 11pt;\">第六条 农村土地承包经营权证应包括以下内容：</p>\n\
<p style=\"MARGIN: 4.5pt 2.25pt 0pt; TEXT-INDENT: 22.5pt; LINE-HEIGHT: 15pt; font-size: 11pt;\">（一）名称和编号；</p>\n\
<p style=\"MARGIN: 4.5pt 2.25pt 0pt; TEXT-INDENT: 22.5pt; LINE-HEIGHT: 15pt; font-size: 11pt;\">（二）发证机关及日期；</p>\n\
<p style=\"MARGIN: 4.5pt 2.25pt 0pt; TEXT-INDENT: 22.5pt; LINE-HEIGHT: 15pt; font-size: 11pt;\">（三）承包期限和起止日期；</p>\n\
<p style=\"MARGIN: 4.5pt 2.25pt 0pt; TEXT-INDENT: 22.5pt; LINE-HEIGHT: 15pt; font-size: 11pt;\">（四）承包土地名称、坐落、面积、用途；</p>\n\
<p style=\"MARGIN: 4.5pt 2.25pt 0pt; TEXT-INDENT: 22.5pt; LINE-HEIGHT: 15pt; font-size: 11pt;\">（五）农村土地承包经营权变动情况；</p>\n\
<p style=\"MARGIN: 4.5pt 2.25pt 0pt; TEXT-INDENT: 22.5pt; LINE-HEIGHT: 15pt; font-size: 11pt;\">（六）其他应当注明的事项。</p>\n\
</div>"
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