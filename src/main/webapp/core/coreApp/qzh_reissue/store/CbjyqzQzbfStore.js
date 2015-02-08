
Ext.define("core.qzh_reissue.store.CbjyqzQzbfStore", {
  extend: 'Ext.data.Store',
  model: 'core.qzh_reissue.model.CbjyqzQzbfModel',
  pageSize: 10,
  //autoSync:true,//与服务器同步
  proxy: {
    type: "ajax",
    url: "./cbjyqzQzbf/listall_cbjyqzQzbf.do",
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