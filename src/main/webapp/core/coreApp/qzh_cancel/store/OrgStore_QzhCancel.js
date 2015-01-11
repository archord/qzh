/*
 *  
 */
Ext.define("core.qzh_cancel.store.OrgStore_QzhCancel", {
  extend: 'Ext.data.TreeStore',
  defaultRootId: "root",
  //autoSync:true,//与服务器同步
  proxy: {
    api: {
    },
    type: "ajax",
    url: "./organization/list_org_tree.do",
    reader: {
      type: "json"
    },
    writer: {
      type: "json"
    }
  },
  autoLoad: true
});