/*
 *  
 */
Ext.define("core.combobox.store.DklbdmbStore", {
  extend: 'Ext.data.Store',
  storeId: 'dklbdmbStoreId',
  fields: ['dm', 'dkxz'],
  //autoSync:true,//与服务器同步
  proxy: {
    api: {
    },
    type: "ajax",
    url: "./combobox/get_all_dklb.do",
    reader: {
      type: "json"
    },
    writer: {
      type: "json"
    }
  },
  autoLoad: true
});