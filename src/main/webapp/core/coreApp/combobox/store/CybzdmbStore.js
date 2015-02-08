/*
 *  
 */
Ext.define("core.combobox.store.CybzdmbStore", {
  extend: 'Ext.data.Store',
  storeId: 'cybzdmbStoreId',
  fields: ['dm', 'cybz'],
  //autoSync:true,//与服务器同步
  proxy: {
    api: {
    },
    type: "ajax",
    url: "./combobox/get_all_cybz.do",
    reader: {
      type: "json"
    },
    writer: {
      type: "json"
    }
  },
  autoLoad: true
});