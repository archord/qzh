Ext.define("core.qzh_get.controller.QzhGetController", {
//	mixins:{
//		gridUtils:"core.qzh_get.utils.GridUtils"
//	},
//	
  extend: "Ext.app.Controller",
  init: function() {
    var self = this;
    this.control({
      /**showbefor*/
      "qzhGetLayout": {
        beforeshow: function(layout, opt) {
          //alert(layout.getXType());
//          var grid = layout.down("product_grid");
//          var store = grid.getStore();
//          store.filter("id", 0);
        }
      },
      "orgTreeQzhGet": {
        itemclick: function(tree, record, item, index, e, eOpts) {
          //cannot find qzhGetWindow, so pform is undefined
          var pform = Ext.getCmp("qzhGetWindowId");
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
      "qzhGetGrid": {
        itemclick: function(tree, record, item, index, e, eOpts) {
        }
      },
      "qzhGetGrid button[ref=add]": {
        click: function(btn) {
          var lzhhtWin = Ext.create("core.qzh_get.view.QzhGetWindow");
          lzhhtWin.show();
        }
      },
      "qzhGetGrid button[ref=edit]": {
        click: function(btn) {
          var lzhhtTree = btn.up('qzhGetGrid');
          var curSelNode = lzhhtTree.getSelectionModel().getSelection();
          if (curSelNode.length > 0) {
            var lzhhtWin = Ext.create("core.qzh_get.view.QzhGetWindow");
            lzhhtWin.extraParas = {lzhht: curSelNode[0].raw, idAdd: 0, orgLevel: 3};
            lzhhtWin.show();
          }
        }
      },
      "qzhGetGrid button[ref=del]": {
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
      "qzhGetWindow button[ref=save]": {
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

                var dkgrid = Ext.getCmp("qzhGetGridId");
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
      "qzhWindow button[ref=save]": {
        click: function(btn) {
          var tree = btn.up('qzhWindow').down('qzhGetGrid_Qzh');
          var curSelNode = tree.getSelectionModel().getSelection();
          if (curSelNode.length > 0) {
            var window = Ext.getCmp("qzhGetWindowId");
            var pform = window.down("form").getForm();
            pform.findField("cbjyqzbm").setValue(curSelNode[0].raw.cbjyqzbm);
          }
          btn.up('.window').close();
        }
      }

    });
  },
  views: [
    "core.qzh_get.view.QzhGetLayout",
    "core.qzh_get.view.OrgTreeQzhGet",
    "core.qzh_get.view.QzhGetWindow",
    "core.qzh_get.view.QzhWindow",
    "core.qzh_get.view.QzhGetGrid",
    "core.qzh_get.view.OrgTreeQzhGet_Qzh",
    "core.qzh_get.view.QzhGetGrid_Qzh",
    "core.qzh_get.view.QzhGetForm"
  ],
  stores: ["core.qzh_get.store.CbjyqzStore", "core.qzh_get.store.OrgStore_QzhGet", "core.qzh_get.store.OrgStore_QzhGet_Qzh", "core.qzh_get.store.CbjyqzdjbStore"],
  models: ["core.qzh_get.model.CbjyqzModel", "core.qzh.model.CbjyqzdjbModel"]
});