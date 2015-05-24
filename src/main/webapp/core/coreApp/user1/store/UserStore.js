/*
 *  
 */
Ext.define("core.user.store.UserStore", {
  extend: 'Ext.data.Store',
  model: 'core.user.model.UserModel',
  pageSize:30, //每页显示5条记录
  //autoSync:true,//与服务器同步
  proxy: {
    type: "ajax",
    url: "user/list_user.do",
    reader: {
      type: "json",
      root: "rows",
      totalProperty: 'totalCount'
    },
    writer: {
      type: "json"
    }
  },
  autoLoad: true
});