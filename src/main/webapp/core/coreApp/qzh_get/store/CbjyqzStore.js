
Ext.define("core.qzh_get.store.CbjyqzStore", {
  extend: 'Ext.data.Store',
  model: 'core.qzh_get.model.CbjyqzModel',
  pageSize:30,
  //autoSync:true,//与服务器同步
  proxy: {
    type: "ajax",
    url: "./cbjyqz/listall_cbjyqz.do",
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