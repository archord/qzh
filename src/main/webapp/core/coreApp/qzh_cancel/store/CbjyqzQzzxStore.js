
Ext.define("core.qzh_cancel.store.CbjyqzQzzxStore", {
  extend: 'Ext.data.Store',
  model: 'core.qzh_cancel.model.CbjyqzQzzxModel',
  pageSize: 10,
  //autoSync:true,//与服务器同步
  proxy: {
    type: "ajax",
    url: "./cbjyqzQzzx/listall_cbjyqzQzzx.do",
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