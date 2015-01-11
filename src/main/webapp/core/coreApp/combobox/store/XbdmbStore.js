/*
 *  
 */
Ext.define("core.combobox.store.XbdmbStore", {
  extend: 'Ext.data.Store',
  storeId: 'xbdmbStoreId',
  fields: ['dm', 'xb'],
  //autoSync:true,//与服务器同步
  proxy: {
    api: {
    },
    type: "ajax",
    url: "./combobox/get_all_xb.do",
    reader: {
      type: "json"
    },
    writer: {
      type: "json"
    }
  },
  autoLoad: true
});