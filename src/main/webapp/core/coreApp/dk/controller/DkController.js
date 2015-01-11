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
              if (record.raw.orgLevel >= 3) {
                store.load({params: {orgId: record.raw.orgId}});
              }
          }
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
          if (curSelNode.length > 0) {
            if (curSelNode[0].raw.orgLevel < 3) {
              Ext.MessageBox.alert("提示", "必须在左侧选择村级以下区域");
            } else {
              var dkWin = Ext.create("core.dk.view.DkWindow");
              dkWin.myExtraParams = {orgId: curSelNode[0].raw.orgId}; // Add additional stuff
//              dkWin.on('show', function(win) {
//                console.log('orgId=' + win.myExtraParams.orgId);
//              });
              dkWin.show();
            }
          } else {
            Ext.MessageBox.alert("提示", "必须在左侧选择村级以下区域");
          }
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
      }

    });
  },
  views: [
    "core.dk.view.DkLayout",
    "core.dk.view.OrgTreeDk",
    "core.dk.view.DkWindow",
    "core.dk.view.DkGrid"
  ],
  stores: ["core.dk.store.DkStore", 
    "core.dk.store.OrgStore", 
    "core.combobox.store.SyqsxdmbStore", 
    "core.combobox.store.DklbdmbStore", 
    "core.combobox.store.DldjdmbStore", 
    "core.combobox.store.TdytdmbStore", 
    "core.combobox.store.SfdmbStore"],
  models: ["core.dk.model.DkModel"]
});