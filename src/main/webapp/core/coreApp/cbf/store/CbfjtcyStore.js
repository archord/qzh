
Ext.define("core.cbf.store.CbfjtcyStore", {
  extend: 'Ext.data.Store',
  model: 'core.cbf.model.CbfjtcyModel',
  pageSize: 10,
  //autoSync:true,//与服务器同步
  proxy: {
    type: "ajax",
    url: "./cbfjtcy/listall_cbfjtcy.do",
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