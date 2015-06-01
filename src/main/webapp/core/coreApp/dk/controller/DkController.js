Ext.define("core.dk.controller.DkController", {
//	mixins:{
//		gridUtils:"core.dk.utils.GridUtils"
//	},
//	
  extend: "Ext.app.Controller",
  init: function() {
    var self = this;
    this.control({
      /**showbefor*/
      "dkLayout": {
        beforeshow: function(layout, opt) {
          //alert(layout.getXType());
//          var grid = layout.down("product_grid");
//          var store = grid.getStore();
//          store.filter("id", 0);
        }
      },
      "orgTreeDk": {
        itemclick: function(tree, record, item, index, e, eOpts) {
          var dkgrid = Ext.getCmp("dkgrid");
          var store = dkgrid.getStore();
          if (record.raw) {
            store.load({params: {orgId: record.raw.orgId}});
          }
        }
      },
      "dkgrid": {
        itemclick: function(tree, record, item, index, e, eOpts) {
        }
      },
      "dkgrid button[ref=add]": {
        click: function(btn) {
          var win = Ext.create("core.dk.view.DkWindow");
          win.show();
        }
      },
      "dkgrid button[ref=edit]": {
        click: function(btn) {
          var dkTree = btn.up('dkgrid');
          var curSelNode = dkTree.getSelectionModel().getSelection();
          if (curSelNode.length > 0) {
            var dkWin = Ext.create("core.dk.view.DkWindow");
            dkWin.extraParas = {dk: curSelNode[0].raw, idAdd: 0, orgLevel: 3};
            dkWin.show();
          }
        }
      },
      "dkgrid button[ref=del]": {
        click: function(btn) {
          var cbhtTree = btn.up('dkgrid');
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
                  url: "./dk/remove_dk.do",
                  params: {
                    ids: ids
                  }, // 根据id删除
                  method: "POST",
                  timeout: 4000,
                  success: function(response, opts) {
                    var resObj = Ext.decode(response.responseText);
                    if (resObj.success) {
                      var store = Ext.getCmp("dkgrid").getStore();
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
      "dkwindow button[ref=save]": {
        click: function(btn) {
          var orgTree = Ext.getCmp("orgTreeDk");
          var pform = btn.up("dkwindow").down("form").getForm();
          if (pform.findField("isAdd").getValue() === '1') {
            var curSelNode = orgTree.getSelectionModel().getSelection();
            if (curSelNode[0].raw) {
              if (curSelNode[0].raw.orgLevel < 3) {
                pform.findField("orgName").setValue("必须在左侧选择村级以下区域");
                Ext.MessageBox.alert("提示", "必须在左侧选择村级以下区域");
                return;
              }
            } else {
              return;
            }
            if (pform.findField("orgId").getValue() === "" || pform.findField("orgLevel").getValue() < 3) {
              pform.findField("orgName").setValue("必须在左侧选择村级以下区域");
              Ext.MessageBox.alert("提示", "必须在左侧选择村级以下区域!");
              return;
            }
          }
          if (pform.isValid()) {
            pform.submit({
              url: "./dk/add_dk.do",
              success: function(form, action) {
                var dkgrid = Ext.getCmp("dkgrid");
                var store = dkgrid.getStore();
                store.load();
                Ext.MessageBox.alert("提示", "保存成功！");
                btn.up("dkwindow").close();
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
      "OrgWindowAll_dk button[ref=save]": {
        click: function(btn) {
          var tree = btn.up('OrgWindowAll_dk').down('treepanel');
          var curSelNode = tree.getSelectionModel().getSelection();
          if (curSelNode.length > 0 && curSelNode[0].raw) {
//            if (curSelNode[0].raw.orgLevel < 3) {
//              Ext.MessageBox.alert("提示", "必须在左侧选择村级以下区域!");
//              return;
//            }
            var window = Ext.getCmp("dkWindowId");
            var pform = window.down("form").getForm();
            pform.findField("orgName").setValue(curSelNode[0].raw.orgName);
            pform.findField("orgId").setValue(curSelNode[0].raw.orgId);
          }
          btn.up('.window').close();
//          btn.up('.window').hide();
        }
      }

    });
  },
  views: [
    "core.dk.view.DkLayout",
    "core.dk.view.OrgTreeDk",
    "core.dk.view.DkWindow",
    "core.dk.view.DkGrid",
    "core.dk.view.OrgWindowAll_dk"
  ],
  stores: ["core.dk.store.DkStore",
    "core.dk.store.OrgStore",
    "core.combobox.store.SyqsxdmbStore",
    "core.combobox.store.DklbdmbStore",
    "core.combobox.store.DldjdmbStore",
    "core.combobox.store.TdytdmbStore",
    "core.combobox.store.SfdmbStore",
    "core.combobox.store.TdlylxStore", "core.main.store.OrgStore"],
  models: ["core.dk.model.DkModel"]
});