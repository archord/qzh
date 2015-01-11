/*
 *  
 */
Ext.define("core.combobox.store.CbflxdmbStore", {
  extend: 'Ext.data.Store',
  storeId: 'cbflxdmbStoreId',
  fields: ['dm', 'cbflx'],
  //autoSync:true,//与服务器同步
  proxy: {
    api: {
    },
    type: "ajax",
    url: "./combobox/get_all_cbflx.do",
    reader: {
      type: "json"
    },
    writer: {
      type: "json"
    }
  },
  autoLoad: true
});