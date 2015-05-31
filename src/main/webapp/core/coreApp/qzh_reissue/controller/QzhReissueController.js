Ext.define("core.qzh_reissue.controller.QzhReissueController", {
//	mixins:{
//		gridUtils:"core.qzh_reissue.utils.GridUtils"
//	},
//	
  extend: "Ext.app.Controller",
  init: function() {
    var self = this;
    this.control({
      /**showbefor*/
      "qzhReissueLayout": {
        beforeshow: function(layout, opt) {
          //alert(layout.getXType());
//          var grid = layout.down("product_grid");
//          var store = grid.getStore();
//          store.filter("id", 0);
        }
      },
      "orgTreeQzhReissue": {
        itemclick: function(tree, record, item, index, e, eOpts) {
          var grid = Ext.getCmp("qzhReissueGridId");
          var store = grid.getStore();
          if (record.raw) {
            store.load({params: {orgId: record.raw.orgId}});
          }
        }
      },
      "orgTreeQzh_QzhReissue": {
        itemclick: function(tree, record, item, index, e, eOpts) {
          var grid = Ext.getCmp("qzhReissueGrid_QzhId");
          var store = grid.getStore();
          if (record.raw) {
            store.load({params: {orgId: record.raw.orgId}});
          }
        }
      },
      "qzhReissueGrid": {
        itemclick: function(tree, record, item, index, e, eOpts) {
        }
      },
      "qzhReissueGrid button[ref=add]": {
        click: function(btn) {
          var lzhhtWin = Ext.create("core.qzh_reissue.view.QzhReissueWindow");
          lzhhtWin.show();
        }
      },
      "qzhReissueGrid button[ref=edit]": {
        click: function(btn) {
          var tree = btn.up('qzhReissueGrid');
          var curSelNode = tree.getSelectionModel().getSelection();
          if (curSelNode.length > 0) {
            var win = Ext.create("core.qzh_reissue.view.QzhReissueWindow");
            win.extraParas = {obj: curSelNode[0].raw, idAdd: 0, orgLevel: 3};
            win.show();
          } else {
            Ext.MessageBox.alert("提示", "请在下面选择权证！");
          }
        }
      },
      "qzhReissueGrid button[ref=del]": {
        click: function(btn) {
          var cbhtTree = btn.up('qzhReissueGrid');
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
                  url: "./cbjyqzQzbf/remove_cbjyqzQzbf.do",
                  params: {
                    ids: ids
                  }, // 根据id删除
                  method: "POST",
                  timeout: 4000,
                  success: function(response, opts) {
                    var resObj = Ext.decode(response.responseText);
                    if (resObj.success) {
                      var store = Ext.getCmp("qzhReissueGridId").getStore();
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
      "qzhReissueWindow button[ref=save]": {
        click: function(btn) {
          var pform = btn.up("qzhReissueWindow").down("form").getForm();
          if (pform.isValid()) {
            pform.submit({
              url: "./cbjyqzQzbf/add_cbjyqzQzbf.do",
              success: function(form, action) {
                Ext.MessageBox.confirm("提示", "保存成功！是否继续？", function(btn) {
                  if (btn === "yes") {
                    pform.reset();
                  }
                });

                var store = Ext.getCmp("qzhReissueGridId").getStore();
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
          }else{
            Ext.MessageBox.alert("提示", "请完整填写所有项！");
          }
        }
      },
      "qzhWindow_get button[ref=save]": {
        click: function(btn) {
          var tree = btn.up('qzhWindow_get').down('qzhReissueGrid_Qzh');
          var curSelNode = tree.getSelectionModel().getSelection();
          if (curSelNode.length > 0) {
            var window = Ext.getCmp("qzhReissueWindowId");
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
    "core.qzh_reissue.view.QzhReissueLayout",
    "core.qzh_reissue.view.OrgTreeQzhReissue",
    "core.qzh_reissue.view.QzhReissueWindow",
    "core.qzh_reissue.view.QzhWindow_get",
    "core.qzh_reissue.view.QzhReissueGrid",
    "core.qzh_reissue.view.OrgTreeQzhReissue_Qzh",
    "core.qzh_reissue.view.QzhReissueGrid_Qzh",
    "core.qzh_reissue.view.QzhReissueForm"
  ],
  stores: ["core.qzh_reissue.store.CbjyqzQzbfStore", "core.qzh_reissue.store.OrgStore_QzhReissue",
    "core.qzh_reissue.store.OrgStore_QzhReissue_Qzh", "core.qzh_reissue.store.CbjyqzdjbStore",
    "core.combobox.store.ZjlxdmbStore", "core.combobox.store.SfdmbStore"],
  models: ["core.qzh_reissue.model.CbjyqzQzbfModel", "core.qzh.model.CbjyqzdjbModel"]
});