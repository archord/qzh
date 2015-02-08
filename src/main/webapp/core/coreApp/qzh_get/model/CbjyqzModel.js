/*
 *  
 */
Ext.define("core.qzh_get.model.CbjyqzModel", {
  extend: "Ext.data.Model",
  fields: [
    {name: "id", type: "int", srotable: false},
    {name: "cbjyqzbm", type: "string", srotable: false},
    {name: "fzjg", type: "string", srotable: false},
    {name: "fzrq", type: "string", srotable: false},
    {name: "qzsfly", type: "string", srotable: false},
    {name: "qzlqrq", type: "string", srotable: false},
    {name: "qzlqrxm", type: "string", srotable: false},
    {name: "qzlqrzjlx", type: "string", srotable: false},
    {name: "qzlqrzjhm", type: "string", srotable: false}
  ],
  proxy: {
    type: 'ajax',
    api: {
      read: '/cbjyqzdjb/read',
      create: '/cbjyqzdjb/create',
      update: '/cbjyqzdjb/update',
      destroy: '/cbjyqzdjb/destroy'
    },
    reader: {
      type: 'json'
    },
    writer: {
      type: 'json'
    }
  }
});