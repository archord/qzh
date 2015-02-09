Ext.define("core.fbf.controller.FbfController", {
//	mixins:{
//		gridUtils:"core.utils.GridUtils"
//	},
//	
  extend: "Ext.app.Controller",
  init: function() {
    var self = this;
    this.control({
      /**showbefor*/
      "fbfLayout": {
        beforeshow: function(layout, opt) {
          //alert(layout.getXType());
//          var grid = layout.down("product_grid");
//          var store = grid.getStore();
//          store.filter("id", 0);
        }
      },
      "orgTreeFbf": {
        itemclick: function(tree, record, item, index, e, eOpts) {
          //节点点击事件
          var pform = tree.up("fbfLayout").down("fbfForm").getForm();
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
      "fbfgrid button[ref=add]": {
        click: function(btn) {
          var win = Ext.create("core.fbf.view.FbfWindow");
          win.show();
        }
      },
      "fbfgrid button[ref=edit]": {
        click: function(btn) {
          var cbhtTree = btn.up('fbfgrid');
          var curSelNode = cbhtTree.getSelectionModel().getSelection();
          if (curSelNode.length > 0) {
            var cbhtWin = Ext.create("core.fbf.view.FbfWindow");
            cbhtWin.extraParas = {obj: curSelNode[0].raw, idAdd: 0, orgLevel: 3};
            cbhtWin.show();
          } else {
            Ext.MessageBox.alert("提示", "请在下方选择发包方！");
          }
        }
      },
      "fbfgrid button[ref=del]": {
        click: function(btn) {
          var cbhtTree = btn.up('fbfgrid');
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
                  url: "./fbf/remove_fbf.do",
                  params: {
                    ids: ids
                  }, // 根据id删除
                  method: "POST",
                  timeout: 4000,
                  success: function(response, opts) {
                    var resObj = Ext.decode(response.responseText);
                    if (resObj.success) {
                      var store = Ext.getCmp("fbfgridId").getStore();
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
      "fbfgrid": {
        itemclick: function(tree, record, item, index, e, eOpts) {
        }
      },
      "fbfForm button[ref=save]": {
        click: function(btn) {
          var pform = btn.up("fbfForm").getForm();
          var orgTree = btn.up('fbfLayout').down("orgTreeFbf");
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
              url: "./fbf/add_fbf.do",
              success: function(form, action) {
                Ext.MessageBox.confirm("提示", "保存成功！是否继续？", function(btn) {
                  if (btn === "yes") {
                    pform.reset();
                  }
                });

                var store = btn.up("fbfLayout").down("fbfGrid").getStore();
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
    "core.fbf.view.FbfLayout",
    "core.fbf.view.OrgTreeFbf",
    "core.fbf.view.FbfForm",
    "core.fbf.view.FbfGrid",
    "core.fbf.view.FbfWindow"
  ],
  stores: ["core.fbf.store.FbfStore", "core.fbf.store.OrgStore","core.combobox.store.ZjlxdmbStore"],
  models: ["core.fbf.model.FbfModel"]
});