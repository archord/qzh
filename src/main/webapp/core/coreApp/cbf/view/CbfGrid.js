/**
 *  
 * */
Ext.define("core.cbf.view.CbfGrid",{
	extend:"Ext.grid.Panel",
	alias:"widget.cbfgrid",
	store:"core.cbf.store.CbfStore",
	border:0,
	selModel:{
		selType:"checkboxmodel"
	},
	multiSelect:true,
	frame:true,
	tbar:[
		{xtype:'button',text:'添加',ref:'add',iconCls:'table_add'},'|',
		{xtype:'button',text:'删除',ref:'del',iconCls:'table_remove'},
		"->",
		'按名称查询:',
		{
			xtype: 'triggerfield', 
			triggerCls: Ext.baseCSSPrefix + 'form-search-trigger',
			listeners:{
            	"change":function(_this,_new,_old,_opt){ 
            		var _store = _this.ownerCt.ownerCt.getStore();
            		_store.clearFilter(false);
            		_store.filter("name",_new);
                }
            },
            onTriggerClick: function() {
            	var _store = this.ownerCt.ownerCt.getStore();
            	_store.clearFilter(false);
            	_store.filter("name",this.getValue());
		    }
		},
		'按编号查询:',
		{
			xtype: 'triggerfield', 
			triggerCls: Ext.baseCSSPrefix + 'form-search-trigger',
			listeners:{
            	"change":function(_this,_new,_old,_opt){ 
            		var _store = _this.ownerCt.ownerCt.getStore();
            		_store.clearFilter(false);
            		_store.filter("id",_new);
                }
            },
            onTriggerClick: function() {
            	var _store = this.ownerCt.ownerCt.getStore();
            	_store.clearFilter(false);
            	_store.filter("id",this.getValue());
		    }
		}
	],
	bbar:{
		xtype:'pagingtoolbar',
		store:'core.cbf.store.CbfStore',
		dock:'bottom',
		displayInfo:true
	},
	enableKeyNav:true,  //可以使用键盘控制上下
	columnLines:true, //展示竖线
	columns:[
		{xtype: 'rownumberer'},
		{text:"承包方编码",dataIndex:"cbfbm",width:100,field:{
			xtype:"textfield"
		}},
		{text:"承包方类型",dataIndex:"cbflx",width:100,field:{
			xtype:"textfield"
		}},
		{text:"承包方名称",dataIndex:"cbfmc",width:100,field:{
			xtype:"textfield"
		}},
		{text:"承包方地址",dataIndex:"cbfdz",width:100,field:{
			xtype:"textfield"
		}},
		{text:"联系电话",dataIndex:"lxdh",width:100,field:{
			xtype:"datefield",
			width:150
		}},
		{text:"承包方调查员",dataIndex:"cbfdcy",width:70,field:{
			xtype:"textfield"
		}},
		{text:"公示记事人",dataIndex:"gsjsr",width:70,field:{
			xtype:"textfield"
		}},
		{text:"公示审核人",dataIndex:"gsshr",width:70,field:{
			xtype:"textfield"
		}},
		{text:"公示审核日期",dataIndex:"gsshrq",width:70,field:{
			xtype:"textfield"
		}}
	],
	initComponent:function(){
		this.callParent(arguments);
	}
});