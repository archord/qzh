
Ext.define("core.qzh.store.CbhtStore_qz", {
  extend: 'Ext.data.Store',
  model: 'core.qzh.model.CbhtModel',
  pageSize: 10,
  //autoSync:true,//与服务器同步
  proxy: {
    type: "ajax",
    url: "./cbht/listall_cbht2.do",
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