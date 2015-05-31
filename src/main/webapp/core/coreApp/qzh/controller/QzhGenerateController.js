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
          var grid = Ext.getCmp("qzhGenerateGridId");
          var store = grid.getStore();
          if (record.raw) {
            store.load({params: {orgId: record.raw.orgId}});
          }
        }
      },
      "orgTreeQzhGenerate_Cbht": {
        itemclick: function(tree, record, item, index, e, eOpts) {
          var grid = Ext.getCmp("qzhCbhtGridId");
          var store = grid.getStore();
          if (record.raw) {
            store.load({params: {orgId: record.raw.orgId}});
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
      "qzhGenerateGrid button[ref=download]": {
        click: function(btn) {
//          var mainView = btn.up("mainviewlayout").down("centerview");
//          var maptab = btn.up("mainviewlayout").down("mapLayout");
//          var orgTreeMap = btn.up("mainviewlayout").down("mapLayout").down("orgTreeMap");
//          orgTreeMap.setActive
//          console.log(orgTreeMap.items[0]);
//          mainView.setActiveTab(maptab);
//          
//          var cbrxmid = Ext.getCmp("cbrxmid");
//          console.log(cbrxmid);
//          cbrxmid.setValue('阿里木·赛买提'); //curSelNode[0].raw.cbjyqzbm
//          var searchdkbutton = Ext.getCmp("searchdkbutton");
//          var downqzbutton = Ext.getCmp("downqzbutton");
//          
//          
//          return;
          var cbhtTree = btn.up('qzhGenerateGrid');
          var curSelNode = cbhtTree.getSelectionModel().getSelection();
          if (curSelNode.length > 0) {//curSelNode[0].raw
            var qzbm = curSelNode[0].raw.cbjyqzbm;
            window.open("download/qz.do?qzbm="+qzbm, "_blank");
          } else {
            Ext.MessageBox.alert("提示", "请在下方选择权证！");
          }
        }
      },
      "qzhGenerateGrid button[ref=edit]": {
        click: function(btn) {
          var lzhhtTree = btn.up('qzhGenerateGrid');
          var curSelNode = lzhhtTree.getSelectionModel().getSelection();
          if (curSelNode.length > 0) {
            var lzhhtWin = Ext.create("core.qzh.view.QzhGenerateWindow");
            lzhhtWin.extraParas = {obj: curSelNode[0].raw, idAdd: 0, orgLevel: 3};
            lzhhtWin.show();
          }
        }
      },
      "qzhGenerateGrid button[ref=del]": {
        click: function(btn) {
          var cbhtTree = btn.up('qzhGenerateGrid');
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
                  url: "./cbjyqzdjb/remove_cbjyqzdjb.do",
                  params: {
                    ids: ids
                  }, // 根据id删除
                  method: "POST",
                  timeout: 4000,
                  success: function(response, opts) {
                    var resObj = Ext.decode(response.responseText);
                    if (resObj.success) {
                      var store = Ext.getCmp("qzhGenerateGridId").getStore();
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
            Ext.MessageBox.alert("提示", "请在下方选择权证！");
          }
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