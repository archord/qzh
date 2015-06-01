Ext.define("core.data_import.view.DataImportForm", {
  extend: "Ext.form.Panel",
  alias: "widget.dataImportForm",
  align: "left",
  frame: true,
  defaults: {
    margin: "7 0 0 15",
    selectOnFocus: true
  },
  layout: {
    type: "table",
    columns: 1
  },
  tbar: [],
  items: [{
      xtype: "filefield",
      name: "file",
      width: 300,
      allowBlank: false,
      readOnly: true,
      buttonText: "选择数据文件(Excel)",
      listeners: {
        change: function(fld, value) {
          var newValue = value.replace(/C:\\fakepath\\/g, '');
          fld.setRawValue(newValue);
        }
      }
    }, {
      xtype: "button",
      width: 80,
      text: "上传",
      handler: function() {
        var form = this.up('form').getForm();
        if (form.isValid()) {
          form.submit({
            url: './uploadData.do',
            waitMsg: '上传数据文件...',
            success: function(form, action) {
              Ext.Msg.alert('提示', '上传成功！');
            },
            failure: function(form, action) {
              var resObj = Ext.decode(action.response.responseText);
              Ext.Msg.alert('上传失败', resObj.msg);
            }
          });
        }
      }
    },
    {
      xtype: 'button',
      text: '下载数据导入模板',
      href: './data/import_template.xlsx',
      hrefTarget: '_blank' // used in 4.1
    }]
});