Ext.define("core.qzh.controller.QzhGenerateController", {
//	mixins:{
//		gridUtils:"core.qzh.utils.GridUtils"
//	},
//	
  extend: "Ext.app.Controller",
  init: function() {
    var self = this;
    this.control({
      /**showbefor*/
      "qzhGenerateLayout": {
        beforeshow: function(layout, opt) {
          //alert(layout.getXType());
//          var grid = layout.down("product_grid");
//          var store = grid.getStore();
//          store.filter("id", 0);
        }
      },
      "orgTreeQzhGenerate": {
        itemclick: function(tree, record, item, index, e, eOpts) {
          //cannot find qzhGenerateWindow, so pform is undefined
          var pform = Ext.getCmp("qzhGenerateWindow");
          if (pform) { // null  undefined NaN empty string("") 0 false //if( typeof foo !== 'undefined' )
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
      "qzhGenerateGrid": {
        itemclick: function(tree, record, item, index, e, eOpts) {
        }
      },
      "qzhGenerateGrid button[ref=add]": {
        click: function(btn) {
          var lzhhtWin = Ext.create("core.qzh.view.QzhGenerateWindow");
          lzhhtWin.show();
        }
      },
      "qzhGenerateGrid button[ref=edit]": {
        click: function(btn) {
          var lzhhtTree = btn.up('qzhGenerateGrid');
          var curSelNode = lzhhtTree.getSelectionModel().getSelection();
          if (curSelNode.length > 0) {
            var lzhhtWin = Ext.create("core.qzh.view.QzhGenerateWindow");
            lzhhtWin.extraParas = {lzhht: curSelNode[0].raw, idAdd: 0, orgLevel: 3};
            lzhhtWin.show();
          }
        }
      },
      "qzhGenerateGrid button[ref=del]": {
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
      "qzhGenerateWindow button[ref=save]": {
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

                var dkgrid = Ext.getCmp("qzhGenerateGridId");
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
      }

    });
  },
  views: [
    "core.qzh.view.QzhGenerateLayout",
    "core.qzh.view.OrgTreeQzhGenerate",
    "core.qzh.view.QzhGenerateWindow",
    "core.qzh.view.QzhGenerateGrid",
    "core.qzh.view.OrgTreeQzhGenerate_Cbht",
    "core.qzh.view.QzhCbhtGrid"
  ],
  stores: ["core.qzh.store.CbjyqzdjbStore", "core.qzh.store.OrgStore", "core.qzh.store.CbhtStore_qz"],
  models: ["core.qzh.model.CbjyqzdjbModel"]
});