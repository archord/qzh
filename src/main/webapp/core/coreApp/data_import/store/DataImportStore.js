
Ext.define("core.data_import.store.DataImportStore",{
 	extend:'Ext.data.Store',
	model:'core.data_import.model.DataImportModel',
	pageSize:10,
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