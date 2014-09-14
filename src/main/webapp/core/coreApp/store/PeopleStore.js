/*
 * 商品数据集类
 */
 Ext.define("core.store.PeopleStore",{
 	extend:'Ext.data.Store',
	model:'core.model.PeopleModel',
	pageSize:10,
	//autoSync:true,//与服务器同步
	proxy:{
		type:"ajax",
		url:"./people/listall_people.do",
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