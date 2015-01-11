/*
 *  
 */
Ext.define("core.combobox.store.DldjdmbStore", {
  extend: 'Ext.data.Store',
  storeId: 'dldjdmbStoreId',
  fields: ['dm', 'dldj'],
  //autoSync:true,//与服务器同步
  proxy: {
    api: {
    },
    type: "ajax",
    url: "./combobox/get_all_dldj.do",
    reader: {
      type: "json"
    },
    writer: {
      type: "json"
    }
  },
  autoLoad: true
});