Ext.define("core.controller.CbhtController", {
//	mixins:{
//		gridUtils:"core.utils.GridUtils"
//	},
//	
  extend: "Ext.app.Controller",
  init: function() {
    var self = this;
    this.control({
      /**showbefor*/
      "cbhtLayout": {
        beforeshow: function(layout, opt) {
          //alert(layout.getXType());
//          var grid = layout.down("product_grid");
//          var store = grid.getStore();
//          store.filter("id", 0);
        }
      },
      "orgTreeCbht": {
        itemclick: function(tree, record, item, index, e, eOpts) {
          //cannot find cbhtwindow, so pform is undefined
          var pform = Ext.getCmp("cbhtwindow");
          if (pform) { // null  undefined NaN empty string("") 0 false //if( typeof foo !== 'undefined' )
            console.log(pform);
            console.log(pform.findField("orgName").getValue());
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
        }
      },
      "cbhtgrid": {
        itemclick: function(tree, record, item, index, e, eOpts) {
        }
      },
      "cbhtgrid button[ref=add]": {
        click: function(btn) {
//          var orgTree = btn.up('cbhtLayout').down("orgTreeCbht");
//          var curSelNode = orgTree.getSelectionModel().getSelection();
//          if (curSelNode.length > 0) {
//            if (curSelNode[0].raw.orgLevel < 3) {
//              Ext.MessageBox.alert("提示", "必须在左侧选择村级以下区域");
//            } else {
              var cbhtWin = Ext.create("core.view.CbhtWindow");
//              cbhtWin.myExtraParams = {orgId: curSelNode[0].raw.orgId}; 
//              cbhtWin.on('show', function(win) {
//                console.log('orgId=' + win.myExtraParams.orgId);
//              });
              cbhtWin.show();
//            }
//          } else {
//            Ext.MessageBox.alert("提示", "必须在左侧选择村级以下区域");
//          }
        }
      },
      "cbhtgrid button[ref=edit]": {
        click: function(btn) {
          var cbhtTree = btn.up('cbhtgrid');
          var curSelNode = cbhtTree.getSelectionModel().getSelection();
          if (curSelNode.length > 0) {
            var cbhtWin = Ext.create("core.view.CbhtWindow");
            cbhtWin.extraParas = {cbht: curSelNode[0].raw, idAdd: 0, orgLevel: 3};
            cbhtWin.show();
          }
        }
      },
      "cbhtgrid button[ref=del]": {
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
      "fbfwindow button[ref=save]": {
        click: function(btn) {
          alert(1);
        }
      },
      "cbhtwindow button[ref=save]": {
        click: function(btn) {
          console.log("cbhtwindow button[ref=save]");
          var orgTree = Ext.getCmp("orgTreeCbht");
          var pform = btn.up("cbhtwindow").down("form").getForm();
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
              url: "./cbht/add_cbht.do",
              success: function(form, action) {
                var cbhtgrid = Ext.getCmp("cbhtgrid");
                var store = cbhtgrid.getStore();
                store.load();
                Ext.MessageBox.alert("提示", "保存成功！");
                btn.up("cbhtwindow").close();
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
    "core.view.CbhtLayout",
    "core.view.OrgTreeCbht",
    "core.view.CbhtGrid",
    "core.view.CbhtWindow",
    "core.view.CbhtForm",
    "core.view.CbhtDkGrid",
    "core.view.PeopleGrid",
    "core.view.FbfWindow"
  ],
  stores: ["core.store.CbhtStore", "core.store.CbhtDkStore", "core.store.OrgStore", "core.store.PeopleStore"],
  models: ["core.model.CbhtModel", "core.model.DkModel"]
});