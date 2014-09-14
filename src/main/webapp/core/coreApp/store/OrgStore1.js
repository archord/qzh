/*
 * ClassName 用户数据集
 */
Ext.define("core.store.OrgStore1", {
  extend: 'Ext.data.TreeStore',
  defaultRootId: "root",
  storeId: 'orgStoreId1',
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