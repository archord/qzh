/*
 *  
 */
Ext.define("core.combobox.store.SfdmbStore", {
  extend: 'Ext.data.Store',
  storeId: 'sfdmbStoreId',
  fields: ['dm', 'sf'],
  //autoSync:true,//与服务器同步
  proxy: {
    api: {
    },
    type: "ajax",
    url: "./combobox/get_all_sfdmb.do",
    reader: {
      type: "json"
    },
    writer: {
      type: "json"
    }
  },
  autoLoad: true
});