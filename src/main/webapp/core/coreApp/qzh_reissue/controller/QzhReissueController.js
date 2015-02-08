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
          //cannot find qzhReissueWindow, so pform is undefined
          var pform = Ext.getCmp("qzhReissueWindowId");
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
          var lzhhtTree = btn.up('qzhReissueGrid');
          var curSelNode = lzhhtTree.getSelectionModel().getSelection();
          if (curSelNode.length > 0) {
            var lzhhtWin = Ext.create("core.qzh_reissue.view.QzhReissueWindow");
            lzhhtWin.extraParas = {obj: curSelNode[0].raw, idAdd: 0, orgLevel: 3};
            lzhhtWin.show();
          }
        }
      },
      "qzhReissueGrid button[ref=del]": {
        click: function(btn) {
          Ext.MessageBox.confirm("注意", "是否删除该记录？", function(btn) {
            if (btn === "yes") {
              console.log("delete record");
            } else {
              console.log("not delete record");
            }
          });
        }
      },
      "qzhReissueWindow button[ref=save]": {
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

                var dkgrid = Ext.getCmp("qzhReissueGridId");
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
      "qzhWindowReissue button[ref=save]": {
        click: function(btn) {
          var tree = btn.up('qzhWindowReissue').down('qzhReissueGrid_Qzh');
          var curSelNode = tree.getSelectionModel().getSelection();
          if (curSelNode.length > 0) {
            var window = Ext.getCmp("qzhReissueWindowId");
            var pform = window.down("form").getForm();
            pform.findField("cbjyqzbm").setValue(curSelNode[0].raw.cbjyqzbm);
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
    "core.qzh_reissue.view.QzhWindow_Reissue",
    "core.qzh_reissue.view.QzhReissueGrid",
    "core.qzh_reissue.view.OrgTreeQzhReissue_Qzh",
    "core.qzh_reissue.view.QzhReissueGrid_Qzh",
    "core.qzh_reissue.view.QzhReissueForm"
  ],
  stores: ["core.qzh_reissue.store.CbjyqzStore", "core.qzh_reissue.store.OrgStore_QzhReissue", 
            "core.qzh_reissue.store.OrgStore_QzhReissue_Qzh", "core.qzh_reissue.store.CbjyqzdjbStore",
          "core.combobox.store.ZjlxdmbStore", "core.combobox.store.SfdmbStore"],
  models: ["core.qzh_reissue.model.CbjyqzModel", "core.qzh.model.CbjyqzdjbModel"]
});