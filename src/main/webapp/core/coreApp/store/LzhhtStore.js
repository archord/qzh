
Ext.define("core.store.LzhhtStore", {
  extend: 'Ext.data.Store',
  model: 'core.model.LzhhtModel',
  pageSize: 10,
  //autoSync:true,//与服务器同步
  proxy: {
    type: "ajax",
    url: "./lzht/list_lzht.do",
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