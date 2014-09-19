Ext.define("core.controller.DkController", {
//	mixins:{
//		gridUtils:"core.utils.GridUtils"
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
          //节点点击事件
          var pform = tree.up("dkLayout").down("dkform").getForm();
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
      "dkgrid": {
        itemclick: function(tree, record, item, index, e, eOpts) {

          var pform = tree.up("dkLayout").down("dkform").getForm();
          pform.findField("isAdd").setValue("0");

          if (record.raw) {
            pform.findField("orgName").setValue(" ");
            pform.findField("orgId").setValue(record.raw.orgId);
            pform.findField("orgLevel").setValue(record.raw.orgLevel);

            pform.findField("id").setValue(record.raw.id);
            pform.findField("dkbm").setValue(record.raw.dkbm);
            pform.findField("dklx").setValue(record.raw.dklx);
            pform.findField("dkmc").setValue(record.raw.dkmc);
            pform.findField("dkzjlx").setValue(record.raw.dkzjlx);
            pform.findField("dkzjhm").setValue(record.raw.dkzjhm);
            pform.findField("dkdz").setValue(record.raw.dkdz);
            pform.findField("yzbm").setValue(record.raw.yzbm);
            pform.findField("lxdh").setValue(record.raw.lxdh);
            pform.findField("dkcysl").setValue(record.raw.dkcysl);
            pform.findField("dkdcrq").setValue(record.raw.dkdcrq);
            pform.findField("dkdcy").setValue(record.raw.dkdcy);
            pform.findField("dkdcjs").setValue(record.raw.dkdcjs);
            pform.findField("gsjs").setValue(record.raw.gsjs);
            pform.findField("gsjsr").setValue(record.raw.gsjsr);
            pform.findField("gsshrq").setValue(record.raw.gsshrq);
            pform.findField("gsshr").setValue(record.raw.gsshr);
          }
        }
      },
      "dkform button[ref=save]": {
        click: function(btn) {
          var pform = btn.up("dkform").getForm();
          var orgTree = btn.up('dkLayout').down("orgTreeDk");
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
              url: "./dk/add_dk.do",
              success: function(form, action) {
                Ext.MessageBox.confirm("提示", "保存成功！是否继续？", function(btn) {
                  if (btn === "yes") {
                    pform.reset();
                  }
                });

                var store = btn.up("dkLayout").down("dkgrid").getStore();
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
    "core.view.DkLayout",
    "core.view.OrgTreeDk",
    "core.view.DkForm",
    "core.view.DkGrid"
  ],
  stores: ["core.store.DkStore", "core.store.OrgStore"],
  models: ["core.model.DkModel"]
});