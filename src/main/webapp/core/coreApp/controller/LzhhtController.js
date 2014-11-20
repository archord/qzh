Ext.define("core.controller.LzhhtController", {
//	mixins:{
//		gridUtils:"core.utils.GridUtils"
//	},
//	
  extend: "Ext.app.Controller",
  init: function() {
    var self = this;
    this.control({
      /**showbefor*/
      "lzhhtLayout": {
        beforeshow: function(layout, opt) {
          //alert(layout.getXType());
//          var grid = layout.down("product_grid");
//          var store = grid.getStore();
//          store.filter("id", 0);
        }
      },
      "orgTreeLzhht": {
        itemclick: function(tree, record, item, index, e, eOpts) {
          //cannot find lzhhtwindow, so pform is undefined
          var pform = Ext.getCmp("lzhhtwindow");
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
      "lzhhtGrid": {
        itemclick: function(tree, record, item, index, e, eOpts) {
        }
      },
      "lzhhtGrid button[ref=add]": {
        click: function(btn) {
          var orgTree = btn.up('lzhhtLayout').down("orgTreeLzhht");
          var curSelNode = orgTree.getSelectionModel().getSelection();
          if (curSelNode.length > 0) {
            if (curSelNode[0].raw.orgLevel < 3) {
              Ext.MessageBox.alert("提示", "必须在左侧选择村级以下区域");
            } else {
              var lzhhtWin = Ext.create("core.view.LzhhtWindow");
              lzhhtWin.myExtraParams = {orgId: curSelNode[0].raw.orgId}; // Add additional stuff
//              lzhhtWin.on('show', function(win) {
//                console.log('orgId=' + win.myExtraParams.orgId);
//              });
              lzhhtWin.show();
            }
          } else {
            Ext.MessageBox.alert("提示", "必须在左侧选择村级以下区域");
          }
        }
      },
      "lzhhtGrid button[ref=edit]": {
        click: function(btn) {
          var lzhhtTree = btn.up('lzhhtGrid');
          var curSelNode = lzhhtTree.getSelectionModel().getSelection();
          if (curSelNode.length > 0) {
            var lzhhtWin = Ext.create("core.view.LzhhtWindow");
            lzhhtWin.extraParas = {lzhht: curSelNode[0].raw, idAdd: 0, orgLevel: 3};
            lzhhtWin.show();
          }
        }
      },
      "lzhhtGrid button[ref=del]": {
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
      "lzhhtwindow button[ref=save]": {
        click: function(btn) {
          var orgTree = Ext.getCmp("orgTreeLzhht");
          var pform = btn.up("lzhhtwindow").down("form").getForm();
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
              url: "./lzht/add_lzht.do",
              success: function(form, action) {
                var lzhhtGrid = Ext.getCmp("lzhhtgrid");
                var store = lzhhtGrid.getStore();
                store.load();
                Ext.MessageBox.alert("提示", "保存成功！");
                btn.up("lzhhtwindow").close();
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
    "core.view.LzhhtLayout",
    "core.view.OrgTreeLzhht",
    "core.view.LzhhtWindow",
    "core.view.LzhhtGrid"
  ],
  stores: ["core.store.LzhhtStore", "core.store.OrgStore"],
  models: ["core.model.LzhhtModel"]
});