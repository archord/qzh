
Ext.define("core.cbf.store.CbfStore",{
 	extend:'Ext.data.Store',
	model:'core.cbf.model.CbfModel',
	pageSize:30,
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