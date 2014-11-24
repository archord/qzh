/*
 *  
 */
Ext.define("core.qzh.model.CbhtModel", {
  extend: "Ext.data.Model",
  fields: [
    {name: "id", type: "int", srotable: false},
    {name: "cbhtbm", type: "string", srotable: false},
    {name: "ycbhtbm", type: "string", srotable: false},
    {name: "fbfbm", type: "string", srotable: false},
    {name: "cbfbm", type: "string", srotable: false},
    {name: "cbfmc", type: "string", srotable: false},
    {name: "cbfs", type: "string", srotable: false},
    {name: "cbqxq", type: "string", srotable: false},
    {name: "cbqxz", type: "string", srotable: false},
    {name: "htzmj", type: "float", srotable: false},
    {name: "cbdkzs", type: "int", srotable: false},
    {name: "qdsj", type: "string", srotable: false},
    {name: "orgId", type: "int", srotable: false}
  ],
  proxy: {
    type: 'ajax',
    api: {
      read: '/cbht/read',
      create: '/cbht/create',
      update: '/cbht/update',
      destroy: '/cbht/destroy'
    },
    reader: {
      type: 'json'
    },
    writer: {
      type: 'json'
    }
  }
});