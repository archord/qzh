/*
 *  
 */
Ext.define("core.cbf_jtcy.model.CbfjtcyModel", {
  extend: "Ext.data.Model",
  fields: [
    {name: "id", type: "int", srotable: false},
    {name: "cbfbm", type: "string", srotable: false},
    {name: "cyxm", type: "string", srotable: false},
    {name: "cyxb", type: "string", srotable: false},
    {name: "cymz", type: "string", srotable: false},
    {name: "cyzjlx", type: "string", srotable: false},
    {name: "cyzjhm", type: "string", srotable: false},
    {name: "yhzgx", type: "string", srotable: false},
    {name: "cybz", type: "string", srotable: false},
    {name: "sfgyr", type: "string", srotable: false}
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