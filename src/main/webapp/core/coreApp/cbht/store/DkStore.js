
Ext.define("core.cbht.store.DkStore", {
  extend: 'Ext.data.Store',
  model: 'core.cbht.model.DkModel',
  pageSize:30,
  //autoSync:true,//与服务器同步
  proxy: {
    type: "ajax",
    url: "./dk/list_dk_cbht.do",
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