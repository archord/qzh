
Ext.define("core.fbf.store.FbfStore",{
 	extend:'Ext.data.Store',
	model:'core.fbf.model.FbfModel',
	pageSize:30,
	//autoSync:true,//与服务器同步
	proxy:{
		type:"ajax",
		url:"./fbf/listall_fbf.do",
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