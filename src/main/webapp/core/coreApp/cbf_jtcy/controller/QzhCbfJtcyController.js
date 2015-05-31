Ext.define("core.cbf_jtcy.controller.QzhCbfJtcyController", {
//	mixins:{
//		gridUtils:"core.cbf_jtcy.utils.GridUtils"
//	},
//	
  extend: "Ext.app.Controller",
  init: function() {
    var self = this;
    this.control({
      /**showbefor*/
      "qzhCbfJtcyLayout": {
        beforeshow: function(layout, opt) {
          //alert(layout.getXType());
//          var grid = layout.down("product_grid");
//          var store = grid.getStore();
//          store.filter("id", 0);
        }
      },
      "orgTreeQzhCbfJtcy": {
        itemclick: function(tree, record, item, index, e, eOpts) {
          var grid = Ext.getCmp("qzhCbfJtcyGridId");
          var store = grid.getStore();
          if (record.raw) {
            store.load({params: {orgId: record.raw.orgId}});
          }
        }
      },
      "qzhCbfJtcyGrid": {
        itemclick: function(tree, record, item, index, e, eOpts) {
        }
      },
      "qzhCbfJtcyGrid button[ref=add]": {
        click: function(btn) {
          var lzhhtWin = Ext.create("core.cbf_jtcy.view.QzhCbfJtcyWindow");
          lzhhtWin.show();
        }
      },
      "qzhCbfJtcyGrid button[ref=edit]": {
        click: function(btn) {
          var lzhhtTree = btn.up('qzhCbfJtcyGrid');
          var curSelNode = lzhhtTree.getSelectionModel().getSelection();
          if (curSelNode.length > 0) {
            var lzhhtWin = Ext.create("core.cbf_jtcy.view.QzhCbfJtcyWindow");
            lzhhtWin.extraParas = {obj: curSelNode[0].raw, idAdd: 0, orgLevel: 3};
            lzhhtWin.show();
          }
        }
      },
      "qzhCbfJtcyGrid button[ref=del]": {
        click: function(btn) {
          var cbhtTree = btn.up('qzhCbfJtcyGrid');
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
                  url: "./cbf/remove_cbf.do",
                  params: {
                    ids: ids
                  }, // 根据id删除
                  method: "POST",
                  timeout: 4000,
                  success: function(response, opts) {
                    var resObj = Ext.decode(response.responseText);
                    if (resObj.success) {
                      var store = Ext.getCmp("qzhCbfJtcyGridId").getStore();
                      store.load();
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
      "qzhCbfJtcyWindow button[ref=save]": {
        click: function(btn) {
          var orgTree = Ext.getCmp("qzhCbhtGridId");
          var curSelNode = orgTree.getSelectionModel().getSelection();
          if (curSelNode.length > 0) {
            var cbhtIds = "";
            for (var a in curSelNode) {
              cbhtIds += curSelNode[a].raw.id + ",";
            }
            cbhtIds = cbhtIds.substring(0, cbhtIds.length - 1);
//            console.log(cbhtIds);

            Ext.Ajax.request({
              url: "./cbjyqzdjb/add_cbjyqzdjb.do?cbhtIds=" + cbhtIds,
              success: function(response, opts) {

                var dkgrid = Ext.getCmp("qzhCbfJtcyGridId");
                var store = dkgrid.getStore();
//                store.load({params: {cbhtId: cbhtId}});
                store.load();
                Ext.MessageBox.alert("提示", "保存成功！");
              },
              failure: function(response, opts) {
                var resObj = Ext.decode(response.responseText);
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
      "qzhWindowCbfJtcy button[ref=save]": {
        click: function(btn) {
          var tree = btn.up('qzhWindowCbfJtcy').down('qzhCbfJtcyGrid_Qzh');
          var curSelNode = tree.getSelectionModel().getSelection();
          if (curSelNode.length > 0) {
            var window = Ext.getCmp("qzhCbfJtcyWindowId");
            var pform = window.down("form").getForm();
            pform.findField("cbjyqzbm").setValue(curSelNode[0].raw.cbjyqzbm);
          }
          btn.up('.window').close();
        }
      }

    });
  },
  views: [
    "core.cbf_jtcy.view.QzhCbfJtcyLayout",
    "core.cbf_jtcy.view.OrgTreeQzhCbfJtcy",
    "core.cbf_jtcy.view.QzhCbfJtcyWindow",
    "core.cbf_jtcy.view.QzhWindow",
    "core.cbf_jtcy.view.QzhCbfJtcyGrid",
    "core.cbf_jtcy.view.OrgTreeQzhCbfJtcy_Qzh",
    "core.cbf_jtcy.view.QzhCbfJtcyGrid_Qzh",
    "core.cbf_jtcy.view.QzhCbfJtcyForm"
  ],
  stores: ["core.cbf_jtcy.store.CbfjtcyStore", "core.cbf_jtcy.store.OrgStore_QzhCbfJtcy", 
            "core.cbf_jtcy.store.OrgStore_QzhCbfJtcy_Qzh", "core.cbf_jtcy.store.CbjyqzdjbStore",
          "core.combobox.store.ZjlxdmbStore", "core.combobox.store.SfdmbStore"],
  models: ["core.cbf_jtcy.model.CbfjtcyModel", "core.cbf.model.CbfModel"]
});