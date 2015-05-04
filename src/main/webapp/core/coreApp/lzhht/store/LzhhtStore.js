
Ext.define("core.lzhht.store.LzhhtStore", {
  extend: 'Ext.data.Store',
  model: 'core.lzhht.model.LzhhtModel',
  pageSize:30,
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