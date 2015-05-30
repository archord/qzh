Ext.define("core.org.controller.OrgController", {
//	mixins:{
//		gridUtils:"core.org.utils.GridUtils"
//	},
//	
  extend: "Ext.app.Controller",
  init: function() {
    var self = this;
    this.control({
      /**showbefor*/
      "orgLayout": {
        beforeshow: function(layout, opt) {
//          alert(layout.getXType());
//          var grid = layout.down("product_grid");
//          var store = grid.getStore();
//          store.filter("id", 0);
//          var addOrgForm = layout.down("addOrganization").getForm();
//          alert(addOrgForm.findField('cell_phone'));
        }
      },
      "orggrid button[ref=add]": {
        click: function(btn) {
          var win = Ext.create("core.org.view.OrgWindow");
          win.show();
        }
      },
      "orggrid button[ref=edit]": {
        click: function(btn) {
          var cbhtTree = btn.up('orggrid');
          var curSelNode = cbhtTree.getSelectionModel().getSelection();
          if (curSelNode.length > 0) {
            var cbhtWin = Ext.create("core.org.view.OrgWindow");
            cbhtWin.extraParas = {obj: curSelNode[0].raw, idAdd: 0};
            cbhtWin.show();
          } else {
            Ext.MessageBox.alert("提示", "请在下方选择发包方！");
          }
        }
      },
      "orggrid button[ref=del]": {
        click: function(btn) {
          var cbhtTree = btn.up('orggrid');
          var curSelNode = cbhtTree.getSelectionModel().getSelection();
          if (curSelNode.length > 0) {//curSelNode[0].raw
            Ext.MessageBox.confirm("注意", "是否删除该记录？", function(btn) {
              if (btn === "yes") {
                var ids = "";
                for (var i = 0; i < curSelNode.length; i++) {
                  ids += curSelNode[i].raw.orgId + ",";
                }
                ids = ids.substring(0, ids.length - 1);
                Ext.Ajax.request({
                  waitMsg: '正在进行处理,请稍后...',
                  url: "./org/remove_org.do",
                  params: {
                    ids: ids
                  }, // 根据id删除
                  method: "POST",
                  timeout: 4000,
                  success: function(response, opts) {
                    var resObj = Ext.decode(response.responseText);
                    if (resObj.success) {
                      var grid = Ext.getCmp("orggridId");
                      var store = grid.getStore();
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
      "orggrid": {
        itemclick: function(tree, record, item, index, e, eOpts) {
        }
      },
      "orgform button[ref=save]": {
        click: function(btn) {
          var pform = btn.up("orgform").getForm();
          if (pform.isValid()) {
            pform.submit({
              url: "./org/save_org.do",
              success: function(form, action) {
                Ext.MessageBox.confirm("提示", "保存成功！是否继续？", function(btn) {
                  if (btn === "yes") {
                    pform.reset();
                  } else {
                    var win = Ext.getCmp("orgWindowId");
                    win.close();
                  }
                });

                var grid = Ext.getCmp("orggridId");
                var store = grid.getStore();
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
      "orgTreeAll button[ref=save]": {
        click: function(btn) {
          var tree = btn.up('orgWindowAll').down('orgTreeAll');
          var curSelNode = tree.getSelectionModel().getSelection();
          if (curSelNode.length > 0 && curSelNode[0].raw) {
//            if (curSelNode[0].raw.orgLevel < 3) {
//              Ext.MessageBox.alert("提示", "必须在左侧选择村级以下区域!");
//              return;
//            }
            var window = Ext.getCmp("orgWindowId");
            var pform = window.down("form").getForm();
            pform.findField("parentName").setValue(curSelNode[0].raw.orgName);
            pform.findField("parentId").setValue(curSelNode[0].raw.orgId);
          }
//          btn.up('.window').close();
          btn.up('.window').hide();
        }
      }

    });
  },
  views: [
    "core.org.view.OrgLayout",
    "core.org.view.OrgTree",
    "core.org.view.OrgForm",
    "core.org.view.OrganizationAdd",
    "core.org.view.OrgGrid",
    "core.org.view.OrgWindow",
    "core.main.view.OrgTree",
    "core.main.view.OrgWindow"
  ],
  stores: ["core.org.store.OrgStore", "core.org.store.OrgStoreTree", "core.main.store.OrgStore"],
  models: ["core.org.model.OrgModel"]
});