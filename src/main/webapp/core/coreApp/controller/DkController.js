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

        }
      },
      "dkgrid": {
        itemclick: function(tree, record, item, index, e, eOpts) {

        }
      },
      "dkgrid button[ref=add]": {
        click: function(btn) {
          var orgTree = btn.up('dkLayout').down("orgTreeDk");
          var curSelNode = orgTree.getSelectionModel().getSelection();
          if (curSelNode.length>0) {
            if (curSelNode[0].raw.orgLevel < 3) {
              Ext.MessageBox.alert("提示","必须在左侧选择村级以下区域");
            } else {
              var dkWin = Ext.create("core.view.DkForm");
              dkWin.show();
            }
          }
        }
      },
      "dkgrid button[ref=del]": {
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
      "dkwindow button[ref=save]": {
        click: function(btn) {
          var orgTree = Ext.getCmp("orgTreeDk");
          var curSelNode = orgTree.getSelectionModel().getSelection();
          if (curSelNode[0].raw) {
            if (curSelNode[0].raw.orgLevel < 3) {
              Ext.MessageBox.alert("提示","必须在左侧选择村级以下区域");
              return;
            } 
          }else{
            return;
          }
          var pform = btn.up("dkwindow").down("form").getForm();
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