/*
 *  
 */
Ext.define("core.combobox.store.TdlylxStore", {
  extend: 'Ext.data.Store',
  storeId: 'tdlylxStoreId',
  fields: ['lbbm', 'lbmc'],
  //autoSync:true,//与服务器同步
  proxy: {
    api: {
    },
    type: "ajax",
    url: "./combobox/get_all_tdlylx.do",
    reader: {
      type: "json"
    },
    writer: {
      type: "json"
    }
  },
  autoLoad: true
});