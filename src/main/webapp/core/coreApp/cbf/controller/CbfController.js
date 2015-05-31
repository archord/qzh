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
          var grid = Ext.getCmp("cbfgridId");
          var store = grid.getStore();
          if (record.raw) {
            store.load({params: {orgId: record.raw.orgId}});
          }
        }
      },
      "cbfgrid": {
        itemclick: function(tree, record, item, index, e, eOpts) {
          return;
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
      "cbfgrid button[ref=add]": {
        click: function(btn) {
          var cbhtWin = Ext.create("core.cbf.view.CbfWindow");
          cbhtWin.show();
        }
      },
      "cbfgrid button[ref=edit]": {
        click: function(btn) {
          var cbhtTree = btn.up('cbfgrid');
          var curSelNode = cbhtTree.getSelectionModel().getSelection();
          if (curSelNode.length > 0) {
            var cbhtWin = Ext.create("core.cbf.view.CbfWindow");
            cbhtWin.extraParas = {obj: curSelNode[0].raw, idAdd: 0, orgLevel: 3};
            cbhtWin.show();
          } else {
            Ext.MessageBox.alert("提示", "请在下方选择承包合同！");
          }
        }
      },
      "cbfgrid button[ref=del]": {
        click: function(btn) {
          var cbhtTree = btn.up('cbfgrid');
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
                  url: "./cbf/remove_cbf.do",
                  params: {
                    ids: ids
                  }, // 根据id删除
                  method: "POST",
                  timeout: 4000,
                  success: function(response, opts) {
                    var resObj = Ext.decode(response.responseText);
                    if (resObj.success) {
                      var store = Ext.getCmp("cbfgridId").getStore();
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
      "cbfform button[ref=save]": {
        click: function(btn) {
          var orgTree = Ext.getCmp("orgTreeCbfId");
          var pform = btn.up("cbfWindow").down("form").getForm();
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
              url: "./cbf/add_cbf.do",
              success: function(form, action) {
                Ext.MessageBox.confirm("提示", "保存成功！是否继续？", function(btn) {
                  if (btn === "yes") {
                    pform.reset();
                  }
                });

                var store = Ext.getCmp("cbfgridId").getStore();
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
      },
      "cbfJtcyForm button[ref=save]": {
        click: function(btn) {
          var pform = btn.up("cbfJtcyWindow").down("form").getForm();
          var url = "";
          if (parseInt(pform.findField("cbfbm").getValue()) > 0) {
            url = "./cbfjtcy/add_cbfJtcy.do";
          } else {
            url = "./cbfjtcy/update_cbfJtcy.do";
          }
          if (pform.isValid()) {
            pform.submit({
              url: url,
              success: function(form, action) {
                Ext.MessageBox.confirm("提示", "保存成功！是否继续？", function(btn) {
                  if (btn === "yes") {
                    pform.reset();
                  } else {
                    Ext.getCmp("cbfJtcyWindowId").close();
                  }
                });

                var cbfForm = Ext.getCmp("cbfformId");
                var cbfJtcyGrid = Ext.getCmp("cbfJtcyGridId");
                var store = cbfJtcyGrid.getStore();
                store.load({params: {cbfbm: pform.findField("cbfbm").getValue()}});
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
      "cbfJtcyGrid button[ref=add]": {
        click: function(btn) {
          var pform = btn.up('cbfWindow').down("form").getForm();
          if (parseInt(pform.findField("id").getValue()) > 0) {
            var cbhtWin = Ext.create("core.cbf.view.CbfJtcyWindow");
            cbhtWin.extraParas = {isAdd: 1, cbfbm: pform.findField("cbfbm").getValue()};
            cbhtWin.show();
          } else {
            Ext.MessageBox.alert("提示", "请保存承包方信息后，再添加家庭成员信息！<br/>或在修改窗口添加家庭成员信息！");
          }
        }
      },
      "cbfJtcyGrid button[ref=edit]": {
        click: function(btn) {
          var cbhtTree = btn.up('cbfJtcyGrid');
          var curSelNode = cbhtTree.getSelectionModel().getSelection();
          if (curSelNode.length > 0) {
            var cbhtWin = Ext.create("core.cbf.view.CbfJtcyWindow");
            cbhtWin.extraParas = {obj: curSelNode[0].raw, isAdd: 0, orgLevel: 3};
            cbhtWin.show();
          } else {
            Ext.MessageBox.alert("提示", "请在下方选择承包方家庭成员！");
          }
        }
      },
      "cbfJtcyGrid button[ref=del]": {
        click: function(btn) {
          var cbhtTree = btn.up('cbfJtcyGrid');
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
                  url: "./cbfjtcy/remove_cbfjtcy.do",
                  params: {
                    ids: ids
                  }, // 根据id删除
                  method: "POST",
                  timeout: 4000,
                  success: function(response, opts) {
                    var resObj = Ext.decode(response.responseText);
                    if (resObj.success) {
                      var store = Ext.getCmp("cbfJtcyGridId").getStore();
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
      }

    });
  },
  views: [
    "core.cbf.view.CbfLayout",
    "core.cbf.view.OrgTreeCbf",
    "core.cbf.view.CbfForm",
    "core.cbf.view.CbfGrid",
    "core.cbf.view.CbfJtcyGrid",
    "core.cbf.view.CbfJtcyWindow",
    "core.cbf.view.CbfJtcyForm",
    "core.cbf.view.CbfWindow"
  ],
  stores: ["core.cbf.store.CbfStore",
    "core.cbf.store.OrgStore",
    "core.combobox.store.ZjlxdmbStore",
    "core.combobox.store.CbflxdmbStore",
    "core.combobox.store.XbdmbStore",
    "core.combobox.store.SfdmbStore",
    "core.cbf.store.CbfjtcyStore",
    "core.combobox.store.CybzdmbStore",
    "core.combobox.store.XbdmbStore"],
  models: ["core.cbf.model.CbfModel", "core.cbf.model.CbfjtcyModel"]
});