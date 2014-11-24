Ext.define("core.cbf.controller.CbfController", {
//	mixins:{
//		gridUtils:"core.cbf.utils.GridUtils"
//	},
//	
  extend: "Ext.app.Controller",
  init: function() {
    var self = this;
    this.control({
      /**showbefor*/
      "cbfLayout": {
        beforeshow: function(layout, opt) {
          //alert(layout.getXType());
//          var grid = layout.down("product_grid");
//          var store = grid.getStore();
//          store.filter("id", 0);
        }
      },
      "orgTreeCbf": {
        itemclick: function(tree, record, item, index, e, eOpts) {
          //节点点击事件
          var pform = tree.up("cbfLayout").down("cbfform").getForm();
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
      "cbfgrid": {
        itemclick: function(tree, record, item, index, e, eOpts) {

          var pform = tree.up("cbfLayout").down("cbfform").getForm();
          pform.findField("isAdd").setValue("0");

          if (record.raw) {
            pform.findField("orgName").setValue(" ");
            pform.findField("orgId").setValue(record.raw.orgId);
            pform.findField("orgLevel").setValue(record.raw.orgLevel);

            pform.findField("id").setValue(record.raw.id);
            pform.findField("cbfbm").setValue(record.raw.cbfbm);
            pform.findField("cbflx").setValue(record.raw.cbflx);
            pform.findField("cbfmc").setValue(record.raw.cbfmc);
            pform.findField("cbfzjlx").setValue(record.raw.cbfzjlx);
            pform.findField("cbfzjhm").setValue(record.raw.cbfzjhm);
            pform.findField("cbfdz").setValue(record.raw.cbfdz);
            pform.findField("yzbm").setValue(record.raw.yzbm);
            pform.findField("lxdh").setValue(record.raw.lxdh);
            pform.findField("cbfcysl").setValue(record.raw.cbfcysl);
            pform.findField("cbfdcrq").setValue(record.raw.cbfdcrq);
            pform.findField("cbfdcy").setValue(record.raw.cbfdcy);
            pform.findField("cbfdcjs").setValue(record.raw.cbfdcjs);
            pform.findField("gsjs").setValue(record.raw.gsjs);
            pform.findField("gsjsr").setValue(record.raw.gsjsr);
            pform.findField("gsshrq").setValue(record.raw.gsshrq);
            pform.findField("gsshr").setValue(record.raw.gsshr);
          }
        }
      },
      "cbfform button[ref=save]": {
        click: function(btn) {
          var pform = btn.up("cbfform").getForm();
          var orgTree = btn.up('cbfLayout').down("orgTreeCbf");
          var curSelNode = orgTree.getSelectionModel().getSelection();
//          alert(curSelNode[0].raw.orgId);
          if (pform.findField("isAdd").getValue() === '1') {
            if (pform.findField("orgId").getValue() === "" || pform.findField("orgLevel").getValue() < 3) {
              pform.findField("orgName").setValue("必须在左侧选择村级以下区域");
              alert("必须在左侧选择村级以下区域!");
              return;
            }
          }
          if (pform.isValid()) {
            pform.submit({
              url: "./cbf/add_cbf.do",
              success: function(form, action) {
                Ext.MessageBox.confirm("提示", "保存成功！是否继续？", function(btn) {
                  if (btn === "yes") {
                    pform.reset();
                  }
                });

                var store = btn.up("cbfLayout").down("cbfgrid").getStore();
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
    "core.cbf.view.CbfLayout",
    "core.cbf.view.OrgTreeCbf",
    "core.cbf.view.CbfForm",
    "core.cbf.view.CbfGrid"
  ],
  stores: ["core.cbf.store.CbfStore", "core.cbf.store.OrgStore"],
  models: ["core.cbf.model.CbfModel"]
});