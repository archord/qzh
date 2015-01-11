
Ext.define("core.cbf_jtcy.store.CbjyqzStore", {
  extend: 'Ext.data.Store',
  model: 'core.cbf_jtcy.model.CbjyqzModel',
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