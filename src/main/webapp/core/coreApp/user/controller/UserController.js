Ext.define("core.user.controller.UserController", {
//	mixins:{
//		gridUtils:"core.utils.GridUtils"
//	},
//	
  extend: "Ext.app.Controller",
  init: function() {
    var self = this;
    this.control({
      /**showbefor*/
      "userLayout": {
        beforeshow: function(layout, opt) {
          //alert(layout.getXType());
//          var grid = layout.down("product_grid");
//          var store = grid.getStore();
//          store.filter("id", 0);
        }
      },
      "orgTreeUser": {
        itemclick: function(tree, record, item, index, e, eOpts) {
          //节点点击事件
          var pform = tree.up("userLayout").down("userForm").getForm();
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
      },
      "usergrid button[ref=add]": {
        click: function(btn) {
          var win = Ext.create("core.user.view.UserWindow");
          win.show();
        }
      },
      "usergrid button[ref=edit]": {
        click: function(btn) {
          var cbhtTree = btn.up('usergrid');
          var curSelNode = cbhtTree.getSelectionModel().getSelection();
          if (curSelNode.length > 0) {
            var cbhtWin = Ext.create("core.user.view.UserWindow");
            cbhtWin.extraParas = {obj: curSelNode[0].raw, idAdd: 0, orgLevel: 3};
            cbhtWin.show();
          } else {
            Ext.MessageBox.alert("提示", "请在下方选择发包方！");
          }
        }
      },
      "usergrid button[ref=del]": {
        click: function(btn) {
          var cbhtTree = btn.up('usergrid');
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
                  url: "./user/remove_user.do",
                  params: {
                    ids: ids
                  }, // 根据id删除
                  method: "POST",
                  timeout: 4000,
                  success: function(response, opts) {
                    var resObj = Ext.decode(response.responseText);
                    if (resObj.success) {
                      var store = Ext.getCmp("usergridId").getStore();
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
      "usergrid": {
        itemclick: function(tree, record, item, index, e, eOpts) {
        }
      },
      "userForm button[ref=save]": {
        click: function(btn) {
          var pform = btn.up("userForm").getForm();
          var orgTree = btn.up('userLayout').down("orgTreeUser");
          var curSelNode = orgTree.getSelectionModel().getSelection();
//          alert(curSelNode[0].raw.orgId);
          if (pform.findField("isAdd").getValue() === '1') {
            if (pform.findField("orgId").getValue() === "" || pform.findField("orgLevel").getValue() < 3) {
              pform.findField("orgName").setValue("必须在左侧选择村级以下区域");
              Ext.MessageBox.alert("提示","必须在左侧选择村级以下区域!");
              return;
            }
          }
          if (pform.isValid()) {
            pform.submit({
              url: "./user/add_user.do",
              success: function(form, action) {
                Ext.MessageBox.confirm("提示", "保存成功！是否继续？", function(btn) {
                  if (btn === "yes") {
                    pform.reset();
                  }
                });

                var store = btn.up("userLayout").down("userGrid").getStore();
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
      }

    });
  },
  views: [
    "core.user.view.UserLayout",
    "core.user.view.OrgTreeUser",
    "core.user.view.UserForm",
    "core.user.view.UserGrid",
    "core.user.view.UserWindow"
  ],
  stores: ["core.user.store.UserStore", "core.user.store.OrgStore"],
  models: ["core.user.model.UserModel"]
});