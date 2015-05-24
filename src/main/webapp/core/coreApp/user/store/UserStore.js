
Ext.define("core.user.store.UserStore",{
 	extend:'Ext.data.Store',
	model:'core.user.model.UserModel',
	pageSize:30,
	//autoSync:true,//与服务器同步
	proxy:{
		type:"ajax",
		url:"./user/listall_user.do",
		reader:{
			type:"json",
			root:"rows",
			totalProperty :'totalCount'		
		},
		writer:{
			type:"json"
		}
	},
	autoLoad:true	
 });