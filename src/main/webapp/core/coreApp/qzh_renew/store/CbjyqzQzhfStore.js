
Ext.define("core.qzh_renew.store.CbjyqzQzhfStore", {
  extend: 'Ext.data.Store',
  model: 'core.qzh_renew.model.CbjyqzQzhfModel',
  pageSize:30,
  //autoSync:true,//与服务器同步
  proxy: {
    type: "ajax",
    url: "./cbjyqzQzhf/listall_cbjyqzQzhf.do",
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