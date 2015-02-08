/*
 *  
 */
Ext.define("core.qzh_cancel.model.CbjyqzQzzxModel", {
  extend: "Ext.data.Model",
  fields: [
    {name: "id", type: "int", srotable: false},
    {name: "cbjyqzbm", type: "string", srotable: false},
    {name: "zxyy", type: "string", srotable: false},
    {name: "zxrq", type: "string", srotable: false}
  ],
  proxy: {
    type: 'ajax',
    api: {
      read: '/cbjyqzQzzxdjb/read',
      create: '/cbjyqzQzzxdjb/create',
      update: '/cbjyqzQzzxdjb/update',
      destroy: '/cbjyqzQzzxdjb/destroy'
    },
    reader: {
      type: 'json'
    },
    writer: {
      type: 'json'
    }
  }
});