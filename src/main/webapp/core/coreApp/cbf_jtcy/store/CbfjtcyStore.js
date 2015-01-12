
Ext.define("core.cbf_jtcy.store.CbfjtcyStore", {
  extend: 'Ext.data.Store',
  model: 'core.cbf_jtcy.model.CbfjtcyModel',
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