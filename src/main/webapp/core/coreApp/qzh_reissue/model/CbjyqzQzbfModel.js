/*
 *  
 */
Ext.define("core.qzh_reissue.model.CbjyqzQzbfModel", {
  extend: "Ext.data.Model",
  fields: [
    {name: "id", type: "int", srotable: false},
    {name: "cbjyqzbm", type: "string", srotable: false},
    {name: "qzbfyy", type: "string", srotable: false},
    {name: "bfrq", type: "string", srotable: false},
    {name: "qzbflqrq", type: "string", srotable: false},
    {name: "qzbflqrxm", type: "string", srotable: false},
    {name: "bflqrzjlx", type: "string", srotable: false},
    {name: "bflqrzjhm", type: "string", srotable: false}
  ],
  proxy: {
    type: 'ajax',
    api: {
      read: '/cbjyqzQzbf/read',
      create: '/cbjyqzQzbf/create',
      update: '/cbjyqzQzbf/update',
      destroy: '/cbjyqzQzbf/destroy'
    },
    reader: {
      type: 'json'
    },
    writer: {
      type: 'json'
    }
  }
});