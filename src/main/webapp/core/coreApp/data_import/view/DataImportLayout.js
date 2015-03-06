/**
 *  
 */
Ext.define("core.data_import.view.DataImportLayout", {
  extend: 'Ext.panel.Panel',
  alias: 'widget.dataImportLayout',
  title: "<center height=40>数据导入</center>",
  closable: true,
  defaults: {
    split: true, // 可以设置好看点的折叠效果
    collapsible: true, // 可以被折叠
    bodyStyle: 'padding:1px'
  },
  layout: 'border',
  items: [{
      xtype: "panel",
      region: "center",
      border: 0,
      header: false,
      layout: "border",
      items: [{
          //xtype : 'panel',
          region: 'center',
          // iconCls:'goodtype_tree',
          margins: '5 0 5 0',
          title: '数据导入',
          xtype: "dataImportForm",
          width:400,
          height:300

        }]
    }]
});