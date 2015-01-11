/*
 *  
 */
Ext.define("core.combobox.store.ZjlxdmbStore", {
  extend: 'Ext.data.Store',
  storeId: 'zjlxdmbStoreId',
  fields: ['dm', 'zjlx'],
  //autoSync:true,//与服务器同步
  proxy: {
    api: {
    },
    type: "ajax",
    url: "./combobox/get_all_zjlx.do",
    reader: {
      type: "json"
    },
    writer: {
      type: "json"
    }
  },
  autoLoad: true
});