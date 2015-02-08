Ext.define("core.org.controller.OrgController", {
//	mixins:{
//		gridUtils:"core.org.utils.GridUtils"
//	},
//	
  extend: "Ext.app.Controller",
  init: function() {
    var self = this;
    this.control({
      /**showbefor*/
      "orgLayout": {
        beforeshow: function(layout, opt) {
//          alert(layout.getXType());
//          var grid = layout.down("product_grid");
//          var store = grid.getStore();
//          store.filter("id", 0);
//          var addOrgForm = layout.down("addOrganization").getForm();
//          alert(addOrgForm.findField('cell_phone'));
        }
      },
      "addOrganization button[ref=save]": {
        click: function(btn) {

          var addOrgForm = btn.up("addOrganization").down("form").getForm();
          var orgTree = btn.up('orgLayout').down("orgTree");
          var curSelNode = orgTree.getSelectionModel().getSelection();
          if (addOrgForm.isValid()) {
            addOrgForm.submit({
              url: "./organization/addOrg.do",
              success: function(form, action) {
                Ext.MessageBox.confirm("提示", "保存成功！是否继续添加？", function(btn) {
                  if (btn === "yes") {
                    addOrgForm.reset();
                  }
                });

                var store = btn.up("orgLayout").down("orgTree").getStore();
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
          return;
//          alert('save');
//          btn.up("addOrganization").close();
          var addOrgForm = btn.up("addOrganization").down("form").getForm();
          var orgId = addOrgForm.findField("orgId").getValue();
          var orgLevel = addOrgForm.findField("orgLevel").getValue();
          var orgCoding = addOrgForm.findField("orgCoding").getValue();
          var orgName = addOrgForm.findField("orgName").getValue();
          var orgMember = addOrgForm.findField("orgMember").getValue();
          var fbfName = addOrgForm.findField("fbfName").getValue();
          var fbfLegalPerson = addOrgForm.findField("fbfLegalPerson").getValue();
          var fbfPhone = addOrgForm.findField("fbfPhone").getValue();
          var fbfAddress = addOrgForm.findField("fbfAddress").getValue();
          var authOrgName = addOrgForm.findField("authOrgName").getValue();
          var authPeople = addOrgForm.findField("authPeople").getValue();
          var authPhone = addOrgForm.findField("authPhone").getValue();

          //form.submit()
          if (!addOrgForm.isValid()) {
            return false;
          }

          Ext.Ajax.request({
            waitMsg: '正在登陆,请稍后...',
            url: "./organization/addOrg.do",
            params: {
              orgId: orgId,
              orgLevel: orgLevel,
              orgCoding: orgCoding,
              orgName: orgName,
              orgMember: orgMember,
              fbfName: fbfName,
              fbfLegalPerson: fbfLegalPerson,
              fbfPhone: fbfPhone,
              fbfAddress: fbfAddress,
              authOrgName: authOrgName,
              authPeople: authPeople,
              authPhone: authPhone
            },
            method: "POST",
            timeout: 4000,
            success: function(response, opts) {
              var resObj = Ext.decode(response.responseText);
              if (resObj.success) {
                Ext.MessageBox.confirm("提示", "添加成功！是否继续添加？", function(btn) {
                  if (btn === "yes") {
                    addOrgForm.reset();
                  }
                });
              } else {
                Ext.MessageBox.show({
                  title: '错误',
                  buttons: Ext.MessageBox.OK,
                  icon: Ext.MessageBox.ERROR,
                  msg: resObj.msg
                });
              }
            }
          });

        }
      },
      /**
       *删除
       */
      "orgTree button[ref=delOrg]": {
        click: function(btn) {
          var orgTree = btn.up('orgLayout').down("orgTree");
          var store = orgTree.getStore();
          var records = orgTree.getSelectionModel().getSelection();
          if (!records || records.length <= 0) {
            Ext.Msg.alert("提示", "请选择需要删除的组织!");
            return;
          }
          // 根据id删除多条记录
          var id = records[0].raw.orgId;
          Ext.Ajax.request({
            waitMsg: '正在进行处理,请稍后...',
            url: "./organization/remove_org.do",
            params: {
              id: id
            }, // 根据id删除
            method: "POST",
            timeout: 4000,
            success: function(response, opts) {
              var resObj = Ext.decode(response.responseText);
              if (resObj.success) {
                // 不用查询，从grid中去掉对应的记录就OK了
                store.load();
                Ext.Msg.alert("提示", resObj.msg);
              } else {
                Ext.Msg.alert("提示", resObj.msg);
              }
              var addOrgForm = btn.up('orgLayout').down("addOrganization").down("form").getForm();
              addOrgForm.reset();

              var orgTree = btn.up("orgLayout").down("orgTree");
              var addXian = orgTree.down("button[ref=addXian]");
              var addXiang = orgTree.down("button[ref=addXiang]");
              var addCun = orgTree.down("button[ref=addCun]");
              var addZun = orgTree.down("button[ref=addZun]");
              var delOrg = orgTree.down("button[ref=delOrg]");
              addXian.setDisabled(false);
              addXiang.setDisabled(true);
              addCun.setDisabled(true);
              addZun.setDisabled(true);
            }
          });
        }
      },
      /**
       * 类别树形节点点击
       */
      "orgTree": {
        itemclick: function(tree, record, item, index, e, eOpts) {
          //节点点击事件
          var orgForm = tree.up("orgLayout").down("addOrganization").down("form").getForm();

          if (record.raw) {
            orgForm.findField("orgId").setValue(record.raw.orgId);
            orgForm.findField("orgLevel").setValue(record.raw.orgLevel);
            orgForm.findField("orgCoding").setValue(record.raw.orgCoding);
            orgForm.findField("orgName").setValue(record.raw.orgName);
            orgForm.findField("orgMember").setValue(record.raw.orgMember);
            orgForm.findField("fbfName").setValue(record.raw.fbfName);
            orgForm.findField("fbfLegalPerson").setValue(record.raw.fbfLegalPerson);
            orgForm.findField("fbfPhone").setValue(record.raw.fbfPhone);
            orgForm.findField("fbfAddress").setValue(record.raw.fbfAddress);
            orgForm.findField("authOrgName").setValue(record.raw.authOrgName);
            orgForm.findField("authPeople").setValue(record.raw.authPeople);
            orgForm.findField("authPhone").setValue(record.raw.authPhone);

            var orgTree = tree.up("orgLayout").down("orgTree");
            var addXian = orgTree.down("button[ref=addXian]");
            var addXiang = orgTree.down("button[ref=addXiang]");
            var addCun = orgTree.down("button[ref=addCun]");
            var addZun = orgTree.down("button[ref=addZun]");
            var delOrg = orgTree.down("button[ref=delOrg]");
            if (record.raw.orgLevel === 1) {
              addXian.setDisabled(true);
              addXiang.setDisabled(false);
              addCun.setDisabled(true);
              addZun.setDisabled(true);
            } else if (record.raw.orgLevel === 2) {
              addXian.setDisabled(true);
              addXiang.setDisabled(true);
              addCun.setDisabled(false);
              addZun.setDisabled(true);
            } else if (record.raw.orgLevel === 3) {
              addXian.setDisabled(true);
              addXiang.setDisabled(true);
              addCun.setDisabled(true);
              addZun.setDisabled(false);
            } else {
              addXian.setDisabled(true);
              addXiang.setDisabled(true);
              addCun.setDisabled(true);
              addZun.setDisabled(true);
            }
            delOrg.setDisabled(false);
          }

          return;
          var store = tree.up("orgLayout").down("product_grid").getStore();
          if (record.raw) {
            treeForm.findField("id").setValue(record.raw.id);
            treeForm.findField("text").setValue(record.raw.text);
            treeForm.findField("description").setValue(record.raw.description);
            treeForm.findField("parent").setValue(record.raw.parent);
            treeForm.findField("leaf").setValue(record.raw.leaf);
            var proxy = store.getProxy();
            whereSql = " and deptCode='" + record.raw.code + "'";
            proxy.extraParams = {
              whereSql: whereSql
            };
            store.load();
          } else {
            treeForm.findField("id").setValue(record.data.id);
            treeForm.findField("text").setValue(record.data.text);
            treeForm.findField("description").setValue("");
            treeForm.findField("parent").setValue(record.data.parentId);
            treeForm.findField("leaf").setValue(record.data.leaf);
          }
          /**加载类别对应的到grid中*/
          store.clearFilter(false);
          if (record.raw) {
            store.filter("category", record.raw.text);
          }
        }
      },
      /**
       * 为根节点添加类别
       */
      "orgTree button[ref=addXian]": {
        click: function(btn) {

          var orgForm = btn.up("orgLayout").down("addOrganization").down("form").getForm();
          orgForm.reset();
          orgForm.findField("orgId").setValue("");
          orgForm.findField("orgLevel").setValue(1);
          orgForm.findField("parentId").setValue(0);
          //添加根类别
//          var tree = btn.up("orgTree");
//          var rootNode = tree.getStore().getRootNode(); // 得到根节点
//          rootNode.appendChild({
//            text: "",
//            parent: "root",
//            leaf: true
//          });

//          var loginWin = Ext.create("core.org.view.OrganizationAdd");
//          loginWin.show();
        }
      },
      "orgTree button[ref=addXiang]": {
        click: function(btn) {
          var curSelNode = btn.up('orgLayout').down("orgTree").getSelectionModel().getSelection();
          var orgForm = btn.up("orgLayout").down("addOrganization").down("form").getForm();
          orgForm.reset();
          orgForm.findField("orgId").setValue("");
          orgForm.findField("orgLevel").setValue(2);
          orgForm.findField("parentId").setValue(curSelNode[0].raw.orgId);

        }
      },
      "orgTree button[ref=addCun]": {
        click: function(btn) {
          var curSelNode = btn.up('orgLayout').down("orgTree").getSelectionModel().getSelection();
          var orgForm = btn.up("orgLayout").down("addOrganization").down("form").getForm();
          orgForm.reset();
          orgForm.findField("orgId").setValue("");
          orgForm.findField("orgLevel").setValue(3);
          orgForm.findField("parentId").setValue(curSelNode[0].raw.orgId);

        }
      },
      "orgTree button[ref=addZun]": {
        click: function(btn) {
          var curSelNode = btn.up('orgLayout').down("orgTree").getSelectionModel().getSelection();
          var orgForm = btn.up("orgLayout").down("addOrganization").down("form").getForm();
          orgForm.reset();
          orgForm.findField("orgId").setValue("");
          orgForm.findField("orgLevel").setValue(4);
          orgForm.findField("parentId").setValue(curSelNode[0].raw.orgId);

        }
      },
      /**
       * 为指定类别添加子类别
       */
      "orgTree button[ref=treechildIns]": {
        //添加子类别
        click: function(btn) {
          var tree = btn.up("orgTree");
          var records = tree.getSelectionModel().getSelection();
          if (records.length < 1) {
            Ext.Msg.alert("提示", "选择父类别");
            return;
          }
          var parentId = records[0].data.id;
          var parentNode = tree.getStore()
                  .getNodeById(parentId);
          if (!parentNode) {
            Ext.Msg.alert("提示", "不能为未存在的类别添加")
            return;
          }
          // 将leaf属性改变
          parentNode.data["leaf"] = false;
          parentNode.updateInfo();
          // 给它加一个孩子节点
          parentNode.appendChild({
            parent: parentId,
            leaf: true
          });
          parentNode.expand(); // 打开父节点
        }
      },
      /**
       * 删除类别
       */
      "orgTree button[ref=treeDel]": {
        //删除类别
        click: function(btn) {
          var tree = btn.up("orgTree");
          var records = tree.getSelectionModel().getSelection();
          var id = records[0].data.id;
          if (records.length < 1) {
            Ext.Msg.alert("提示", "请选择类别");
            return;
          }
          if (!records[0].raw) {
            Ext.Msg.alert("提示", "不能删除未存在类别");
            return;
          }
          var pid = records[0].data.parentId;
          Ext.MessageBox.confirm("重要提示",
                  "如果删除些类别，同时也会删除些类别对应的所有信息，确定要删除吗？",
                  function(e) {
                    if (e == 'yes') {
                      Ext.Ajax.request({
                        waitMsg: '正在进行处理,请稍后...',
                        url: "./category/delete_do",
                        params: {id: id, pid: pid},
                        method: "POST",
                        timeout: 4000,
                        success: function(response, opts) {
                          var resObj = Ext.decode(response.responseText);
                          if (resObj.success) {
                            tree.getStore().load();
                            var OrgForm = tree.up("orgLayout").down("OrgForm").getForm();
                            OrgForm.reset();
                            btn.setDisabled(true);
                            Ext.Msg.alert("提示", resObj.msg);
                          } else {
                            Ext.Msg.alert("提示", resObj.msg);
                          }
                        }
                      });
                    }
                  }
          );

        }
      }
      /**
       * 保存类别信息
       */
      , "OrgForm button[ref=save]": {
        //类信息保存
        click: function(btn) {
          var category = btn.up("OrgForm");
          var OrgForm = getForm();
          var orgTree = up("orgLayout").down("orgTree");
          //var grid=up("orgLayout").down("productgrid");
          //var store = grid.getStore();
          //根据id来判断是保存还是更新，如果为空不就进行操作
          var treeId = OrgForm.findField("id").getValue();
          /*首先声明保存操作*/
          var actionName = "";
          var params = {};
          if (treeId && treeId != "") {
            //修改								
            actionName = "./category/update_do";
            params.id = OrgForm.findField("id").getValue();
          } else {
            //新增加
            actionName = "./category/add_do";
          }
          var text = OrgForm.findField("text").getValue();
          var description = OrgForm.findField("description").getValue();
          var parent = OrgForm.findField("parent").getValue();
          var leaf = OrgForm.findField("leaf").getValue();
          params.text = text;
          params.description = description;
          params.parent = parent;
          params.leaf = leaf;
          Ext.Ajax.request({
            waitMsg: '正在进行处理,请稍后...',
            url: actionName,
            params: params,
            method: "POST",
            timeout: 4000,
            success: function(response, opts) {
              var resObj = Ext.decode(response.responseText);
              if (resObj.success) {
                OrgForm.reset();
                orgTree.getStore().load();
                Ext.Msg.alert("提示", resObj.msg);
              } else {
                Ext.Msg.alert("提示", resObj.msg);
              }
            }
          });
        }
      }

    });
  },
  views: [
    "core.org.view.OrgLayout",
    "core.org.view.OrgTree",
    "core.org.view.OrgForm",
    "core.org.view.OrganizationAdd"
  ],
  stores: ["core.org.store.OrgStore"],
  models: []
});