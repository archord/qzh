
Ext.define("core.qzh_reissue.store.CbjyqzStore", {
  extend: 'Ext.data.Store',
  model: 'core.qzh_reissue.model.CbjyqzModel',
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