/*
 *  
 */
Ext.define("core.combobox.store.CbjyqqdfsdmbStore", {
  extend: 'Ext.data.Store',
  storeId: 'cbjyqqdfsdmbStoreId',
  fields: ['dm', 'qdfs'],
  //autoSync:true,//与服务器同步
  proxy: {
    api: {
    },
    type: "ajax",
    url: "./combobox/get_all_cbjyqqdfs.do",
    reader: {
      type: "json"
    },
    writer: {
      type: "json"
    }
  },
  autoLoad: true
});