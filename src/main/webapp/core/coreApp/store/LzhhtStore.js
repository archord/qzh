
Ext.define("core.store.DkStore", {
  extend: 'Ext.data.Store',
  model: 'core.model.DkModel',
  pageSize: 10,
  //autoSync:true,//与服务器同步
  proxy: {
    type: "ajax",
    url: "./dk/listall_dk.do",
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