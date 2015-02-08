Ext.define("core.cbht.controller.CbhtController", {
//	mixins:{
//		gridUtils:"core.cbht.utils.GridUtils"
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
//            console.log(pform);
//            console.log(pform.findField("orgName").getValue());
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
          var cbhtWin = Ext.create("core.cbht.view.CbhtWindow");
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
            var cbhtWin = Ext.create("core.cbht.view.CbhtWindow");
            cbhtWin.extraParas = {cbht: curSelNode[0].raw, idAdd: 0, orgLevel: 3};
            cbhtWin.show();
          } else {
            Ext.MessageBox.alert("提示", "请在下方选择承包合同！");
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
      "fbfwindow_cbht button[ref=save]": {
        click: function(btn) {
          var tree = btn.up('fbfwindow_cbht').down('fbfGrid_cbht');
          var curSelNode = tree.getSelectionModel().getSelection();
          if (curSelNode.length > 0) {
            var window = Ext.getCmp("cbhtwindowId");
            var pform = window.down("form").getForm();
            pform.findField("fbfbm").setValue(curSelNode[0].raw.fbfbm);
          }
          btn.up('.window').close();
        }
      },
      "cbfwindow_cbht button[ref=save]": {
        click: function(btn) {
          var tree = btn.up('cbfwindow_cbht').down('cbfgrid_cbht');
          var curSelNode = tree.getSelectionModel().getSelection();
          if (curSelNode.length > 0) {
            var window = Ext.getCmp("cbhtwindowId");
            var pform = window.down("form").getForm();
            pform.findField("cbfbm").setValue(curSelNode[0].raw.cbfbm);
          }
          btn.up('.window').close();
        }
      },
      "cbhtform button[ref=save]": {
        click: function(btn) {
          var orgTree = Ext.getCmp("orgTreeCbht");
          var pform = btn.up("cbhtwindow").down("form").getForm();
          if (pform.findField("isAdd").getValue() === '1') {
            var curSelNode = orgTree.getSelectionModel().getSelection();
            if (curSelNode.length > 0 && curSelNode[0].raw) {
              if (curSelNode[0].raw.orgLevel < 3) {
                pform.findField("orgName").setValue("必须在左侧选择村级以下区域");
                Ext.MessageBox.alert("提示", "必须在左侧选择村级以下区域");
                return;
              } else {
                pform.findField("orgName").setValue(curSelNode[0].raw.orgName);
                pform.findField("orgLevel").setValue(curSelNode[0].raw.orgLevel);
                pform.findField("orgId").setValue(curSelNode[0].raw.orgId);
              }
            } else {
              pform.findField("orgName").setValue("必须在左侧选择村级以下区域");
              Ext.MessageBox.alert("提示", "必须在左侧选择村级以下区域");
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
                var resObj = Ext.decode(action.response.responseText);
                pform.findField("id").setValue(resObj.data.id);
                var cbhtgrid = Ext.getCmp("cbhtgrid");
                var store = cbhtgrid.getStore();
                store.load();
                Ext.MessageBox.alert("提示", "保存成功！");
//                btn.up("cbhtwindow").close();
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
      },
      "orgTreeCbhtDkSearch": {
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
      "dksearchwindow button[ref=save]": {
        click: function(btn) {
          var orgTree = Ext.getCmp("dkgridId_cbht");
          var curSelNode = orgTree.getSelectionModel().getSelection();
          if (curSelNode.length > 0) {
            var dkIds = "";
            var cbhtId = "";
            var cbhtform = Ext.getCmp("cbhtformid").getForm();
            if (cbhtform) {
              cbhtId = cbhtform.findField("id").getValue();
            }
            for (var a in curSelNode) {
              dkIds += curSelNode[a].raw.id + ",";
            }
            dkIds = dkIds.substring(0, dkIds.length - 1);
//            console.log(dkIds);
//            console.log(cbhtId);
            Ext.Ajax.request({
              url: "./dk/update_dk.do?cbhtId=" + cbhtId + "&dkIds=" + dkIds,
              success: function(response, opts) {

                var dkgrid_cbht = Ext.getCmp("dkgridId_cbht");
                var store = dkgrid_cbht.getStore();
                store.reload();

                var dkgrid = Ext.getCmp("cbhtdkgridid");
                var store = dkgrid.getStore();
                store.load({params: {cbhtId: cbhtId}});
                Ext.MessageBox.alert("提示", "保存成功！");
              },
              failure: function(response, opts) {
                var resObj = Ext.decode(response.responseText);
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
      },
      "cbhtdkgrid button[ref=add]": {
        click: function(btn) {
          var pform = btn.up('cbhtwindow').down("form").getForm();
          if (parseInt(pform.findField("id").getValue()) > 0) {
            var win = Ext.create("core.cbht.view.DkSearchWindow");
            win.show();
          } else {
            Ext.MessageBox.alert("提示", "请保存合同信息后，再添加地块信息！<br/>或在修改窗口添加地块信息！");
          }
        }
      },
      "cbhtdkgrid button[ref=del]": {
        click: function(btn) {
//          var orgTree = btn.up('cbhtLayout').down("orgTreeCbht");
//          var curSelNode = orgTree.getSelectionModel().getSelection();
//          if (curSelNode.length > 0) {
//            if (curSelNode[0].raw.orgLevel < 3) {
//              Ext.MessageBox.alert("提示", "必须在左侧选择村级以下区域");
//            } else {
          var cbhtWin = Ext.create("core.cbht.view.CbhtWindow");
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
      }

    });
  },
  views: [
    "core.cbht.view.CbhtLayout",
    "core.cbht.view.OrgTreeCbht",
    "core.cbht.view.CbhtGrid",
    "core.cbht.view.CbhtWindow",
    "core.cbht.view.CbhtForm",
    "core.cbht.view.CbhtDkGrid",
    "core.cbht.view.FbfWindow_cbht",
    "core.cbht.view.CbfWindow_cbht",
    "core.cbht.view.FbfGrid_cbht",
    "core.cbht.view.CbfGrid_cbht",
    "core.cbht.view.DkGrid_cbht",
    "core.cbht.view.OrgTreeCbhtDkSearch"
  ],
  stores: ["core.cbht.store.CbhtStore", "core.cbht.store.CbhtDkStore",
    "core.cbht.store.OrgStore", "core.cbht.store.FbfStore",
    "core.cbht.store.CbfStore", "core.cbht.store.DkStore",
    "core.combobox.store.CbjyqqdfsdmbStore"],
  models: ["core.cbht.model.CbhtModel", "core.cbht.model.DkModel"]
});