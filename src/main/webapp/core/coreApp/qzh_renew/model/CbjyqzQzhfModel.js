/*
 *  
 */
Ext.define("core.qzh_renew.model.CbjyqzQzhfModel", {
  extend: "Ext.data.Model",
  fields: [
    {name: "id", type: "int", srotable: false},
    {name: "cbjyqzbm", type: "string", srotable: false},
    {name: "qzhfyy", type: "string", srotable: false},
    {name: "hfrq", type: "string", srotable: false},
    {name: "qzhflqrq", type: "string", srotable: false},
    {name: "qzhflqrxm", type: "string", srotable: false},
    {name: "hflqrzjlx", type: "string", srotable: false},
    {name: "hflqrzjhm", type: "string", srotable: false},
    {name: "orgId", type: "int", srotable: false},
 		{name:"orgName",type:"string",srotable:false}
  ],
  proxy: {
    type: 'ajax',
    api: {
      read: '/cbjyqzQzhf/read',
      create: '/cbjyqzQzhf/create',
      update: '/cbjyqzQzhf/update',
      destroy: '/cbjyqzQzhf/destroy'
    },
    reader: {
      type: 'json'
    },
    writer: {
      type: 'json'
    }
  }
});