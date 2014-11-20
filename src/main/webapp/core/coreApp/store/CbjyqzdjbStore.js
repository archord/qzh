
Ext.define("core.store.CbjyqzdjbStore", {
  extend: 'Ext.data.Store',
  model: 'core.model.CbjyqzdjbModel',
  pageSize: 10,
  //autoSync:true,//与服务器同步
  proxy: {
    type: "ajax",
    url: "./cbjyqzdjb/listall_cbjyqzdjb.do",
    reader: {
      type: "json",
      root: "rows",
      totalProperty: 'totalCount'
    },
    writer: {
      type: "json"
    }
  },
  autoLoad: true
});