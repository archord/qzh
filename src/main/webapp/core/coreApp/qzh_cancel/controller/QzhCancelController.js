Ext.define("core.qzh_cancel.controller.QzhCancelController", {
//	mixins:{
//		gridUtils:"core.qzh_cancel.utils.GridUtils"
//	},
//	
  extend: "Ext.app.Controller",
  init: function() {
    var self = this;
    this.control({
      /**showbefor*/
      "qzhCancelLayout": {
        beforeshow: function(layout, opt) {
          //alert(layout.getXType());
//          var grid = layout.down("product_grid");
//          var store = grid.getStore();
//          store.filter("id", 0);
        }
      },
      "orgTreeQzhCancel": {
        itemclick: function(tree, record, item, index, e, eOpts) {
          //cannot find qzhCancelWindow, so pform is undefined
          var pform = Ext.getCmp("qzhCancelWindowId");
          if (false) { // null  undefined NaN empty string("") 0 false //if( typeof foo !== 'undefined' )
            console.log(pform);
            console.log(pform.findField("orgName").getValue());
            pform.findField("isAdd").setValue("1");
            if (record.raw) {
              if (record.raw.orgLevel < 3) {
                pform.findField("orgName").setValue("必须在左侧选择村级以下区域");
                pform.findField("orgId").setValue("");
                pform.findField("orgLevel").setValue(record.raw.orgLevel);
              } else {
                pform.findField("orgName").setValue(record.raw.orgName);
                pform.findField("orgId").setValue(record.raw.orgId);
                pform.findField("orgLevel").setValue(record.raw.orgLevel);
              }
            }
          }
        }
      },
      "qzhCancelGrid": {
        itemclick: function(tree, record, item, index, e, eOpts) {
        }
      },
      "qzhCancelGrid button[ref=add]": {
        click: function(btn) {
          var lzhhtWin = Ext.create("core.qzh_cancel.view.QzhCancelWindow");
          lzhhtWin.show();
        }
      },
      "qzhCancelGrid button[ref=edit]": {
        click: function(btn) {
          var tree = btn.up('qzhCancelGrid');
          var curSelNode = tree.getSelectionModel().getSelection();
          if (curSelNode.length > 0) {
            var win = Ext.create("core.qzh_cancel.view.QzhCancelWindow");
            win.extraParas = {obj: curSelNode[0].raw, idAdd: 0, orgLevel: 3};
            win.show();
          } else {
            Ext.MessageBox.alert("提示", "请在下面选择权证！");
          }
        }
      },
      "qzhCancelGrid button[ref=del]": {
        click: function(btn) {
          var cbhtTree = btn.up('qzhCancelGrid');
          var curSelNode = cbhtTree.getSelectionModel().getSelection();
          if (curSelNode.length > 0) {//curSelNode[0].raw
            Ext.MessageBox.confirm("注意", "是否删除该记录？", function(btn) {
              if (btn === "yes") {
                var ids = "";
                for (var i = 0; i < curSelNode.length; i++) {
                  ids += curSelNode[i].raw.id + ",";
                }
                ids = ids.substring(0, ids.length - 1);
                Ext.Ajax.request({
                  waitMsg: '正在进行处理,请稍后...',
                  url: "./cbjyqzQzzx/remove_cbjyqzQzzx.do",
                  params: {
                    ids: ids
                  }, // 根据id删除
                  method: "POST",
                  timeout: 4000,
                  success: function(response, opts) {
                    var resObj = Ext.decode(response.responseText);
                    if (resObj.success) {
                      var store = Ext.getCmp("qzhCancelGridId").getStore();
                      store.reload();
                      Ext.Msg.alert("提示", "删除成功！");
                    } else {
                      Ext.Msg.alert("提示", "删除失败！");
                    }
                  }
                });
              }
            });
          } else {
            Ext.MessageBox.alert("提示", "请在下方选择承包合同！");
          }
        }
      },
      "qzhCancelWindow button[ref=save]": {
        click: function(btn) {
          var pform = btn.up("qzhCancelWindow").down("form").getForm();
          if (pform.isValid()) {
            pform.submit({
              url: "./cbjyqzQzzx/add_cbjyqzQzzx.do",
              success: function(form, action) {
                Ext.MessageBox.confirm("提示", "保存成功！是否继续？", function(btn) {
                  if (btn === "yes") {
                    pform.reset();
                  }
                });

                var store = Ext.getCmp("qzhCancelGridId").getStore();
                store.load();
              },
              failure: function(form, action) {
                var resObj = Ext.decode(action.response.responseText);
                Ext.MessageBox.show({
                  title: '错误',
                  buttons: Ext.MessageBox.OK,
                  icon: Ext.MessageBox.ERROR,
                  msg: resObj.msg
                });
              }
            });
          }
        }
      },
      "qzhWindow_get button[ref=save]": {
        click: function(btn) {
          var tree = btn.up('qzhWindow_get').down('qzhCancelGrid_Qzh');
          var curSelNode = tree.getSelectionModel().getSelection();
          if (curSelNode.length > 0) {
            var window = Ext.getCmp("qzhCancelWindowId");
            var pform = window.down("form").getForm();
            pform.findField("cbjyqzbm").setValue(curSelNode[0].raw.cbjyqzbm);
            pform.findField("orgId").setValue(curSelNode[0].raw.orgId);
            pform.findField("orgName").setValue(curSelNode[0].raw.orgName);
          }
          btn.up('.window').close();
        }
      }

    });
  },
  views: [
    "core.qzh_cancel.view.QzhCancelLayout",
    "core.qzh_cancel.view.OrgTreeQzhCancel",
    "core.qzh_cancel.view.QzhCancelWindow",
    "core.qzh_cancel.view.QzhWindow_get",
    "core.qzh_cancel.view.QzhCancelGrid",
    "core.qzh_cancel.view.OrgTreeQzhCancel_Qzh",
    "core.qzh_cancel.view.QzhCancelGrid_Qzh",
    "core.qzh_cancel.view.QzhCancelForm"
  ],
  stores: ["core.qzh_cancel.store.CbjyqzQzzxStore", "core.qzh_cancel.store.OrgStore_QzhCancel",
    "core.qzh_cancel.store.OrgStore_QzhCancel_Qzh", "core.qzh_cancel.store.CbjyqzdjbStore",
    "core.combobox.store.ZjlxdmbStore", "core.combobox.store.SfdmbStore"],
  models: ["core.qzh_cancel.model.CbjyqzQzzxModel", "core.qzh.model.CbjyqzdjbModel"]
});