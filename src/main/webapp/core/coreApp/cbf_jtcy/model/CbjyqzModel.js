/*
 *  
 */
Ext.define("core.cbf_jtcy.model.CbjyqzModel", {
  extend: "Ext.data.Model",
  fields: [
    {name: "id", type: "int", srotable: false},
    {name: "cbfbm", type: "string", srotable: false},
    {name: "cyxm", type: "string", srotable: false},
    {name: "cbfbm", type: "string", srotable: false},
    {name: "cbfs", type: "string", srotable: false},
    {name: "cbqx", type: "string", srotable: false},
    {name: "cbqxq", type: "string", srotable: false},
    {name: "cbqxz", type: "string", srotable: false}
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