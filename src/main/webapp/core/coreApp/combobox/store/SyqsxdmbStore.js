/*
 *  
 */
Ext.define("core.combobox.store.SyqsxdmbStore", {
  extend: 'Ext.data.Store',
  storeId: 'syqsxdmbStoreId',
  fields: ['dm', 'syqsx'],
  //autoSync:true,//与服务器同步
  proxy: {
    api: {
    },
    type: "ajax",
    url: "./combobox/get_all_syqsx.do",
    reader: {
      type: "json"
    },
    writer: {
      type: "json"
    }
  },
  autoLoad: true
});