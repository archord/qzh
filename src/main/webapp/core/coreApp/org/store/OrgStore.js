
Ext.define("core.org.store.OrgStore",{
 	extend:'Ext.data.Store',
	model:'core.org.model.OrgModel',
	pageSize:30,
	//autoSync:true,//与服务器同步
	proxy:{
		type:"ajax",
		url:"./org/listall_org.do",
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