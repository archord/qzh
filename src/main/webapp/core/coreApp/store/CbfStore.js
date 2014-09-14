
Ext.define("core.store.CbfStore",{
 	extend:'Ext.data.Store',
	model:'core.model.CbfModel',
	pageSize:10,
	//autoSync:true,//与服务器同步
	proxy:{
		type:"ajax",
		url:"./cbf/listall_cbf.do",
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