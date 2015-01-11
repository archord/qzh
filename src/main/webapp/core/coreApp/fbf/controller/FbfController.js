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
      "fbfGrid": {
        itemclick: function(tree, record, item, index, e, eOpts) {

          var pform = tree.up("fbfLayout").down("fbfForm").getForm();
          pform.findField("isAdd").setValue("0");

          if (record.raw) {
            pform.findField("orgName").setValue(" ");
            pform.findField("orgId").setValue(record.raw.orgId);
            pform.findField("orgLevel").setValue(record.raw.orgLevel);

            pform.findField("id").setValue(record.raw.id);
            pform.findField("fbfbm").setValue(record.raw.fbfbm);
            pform.findField("fbfmc").setValue(record.raw.fbfmc);
            pform.findField("fbffzrxm").setValue(record.raw.fbffzrxm);
            pform.findField("fzrzjlx").setValue(record.raw.fzrzjlx);
            pform.findField("fzrzjhm").setValue(record.raw.fzrzjhm);
            pform.findField("lxdh").setValue(record.raw.lxdh);
            pform.findField("fbfdz").setValue(record.raw.fbfdz);
            pform.findField("yzbm").setValue(record.raw.yzbm);
            pform.findField("fbfdcy").setValue(record.raw.fbfdcy);
            pform.findField("fbfdcrq").setValue(record.raw.fbfdcrq);
            pform.findField("fbfdcjs").setValue(record.raw.fbfdcjs);
          }
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
    "core.fbf.view.FbfGrid"
  ],
  stores: ["core.fbf.store.FbfStore", "core.fbf.store.OrgStore","core.combobox.store.ZjlxdmbStore"],
  models: ["core.fbf.model.FbfModel"]
});