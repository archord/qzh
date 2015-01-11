/*
 *  
 */
Ext.define("core.combobox.store.TdytdmbStore", {
  extend: 'Ext.data.Store',
  storeId: 'tdytdmbStoreId',
  fields: ['dm', 'tdytd'],
  //autoSync:true,//与服务器同步
  proxy: {
    api: {
    },
    type: "ajax",
    url: "./combobox/get_all_tdyt.do",
    reader: {
      type: "json"
    },
    writer: {
      type: "json"
    }
  },
  autoLoad: true
});