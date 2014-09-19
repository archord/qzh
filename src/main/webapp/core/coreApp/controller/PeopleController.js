Ext.define("core.controller.PeopleController", {
//	mixins:{
//		gridUtils:"core.utils.GridUtils"
//	},
//	
  extend: "Ext.app.Controller",
  init: function() {
    var self = this;
    this.control({
      /**showbefor*/
      "peopleLayout": {
        beforeshow: function(layout, opt) {
          //alert(layout.getXType());
//          var grid = layout.down("product_grid");
//          var store = grid.getStore();
//          store.filter("id", 0);
        }
      },
      "orgTreePeo": {
        itemclick: function(tree, record, item, index, e, eOpts) {
          //节点点击事件
          var pform = tree.up("peopleLayout").down("peopleform").getForm();
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
      "people_grid": {
        itemclick: function(tree, record, item, index, e, eOpts) {

          var pform = tree.up("peopleLayout").down("peopleform").getForm();
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
      "peopleform button[ref=save]": {
        click: function(btn) {
          var pform = btn.up("peopleform").getForm();
          var orgTree = btn.up('peopleLayout').down("orgTreePeo");
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
              url: "./people/add_people.do",
              success: function(form, action) {
                Ext.MessageBox.confirm("提示", "保存成功！是否继续？", function(btn) {
                  if (btn === "yes") {
                    pform.reset();
                  }
                });

                var store = btn.up("peopleLayout").down("people_grid").getStore();
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
    "core.view.PeopleLayout",
    "core.view.OrgTreePeo",
    "core.view.PeopleForm",
    "core.view.PeopleGrid"
  ],
  stores: ["core.store.PeopleStore", "core.store.OrgStore"],
  models: ["core.model.PeopleModel"]
});