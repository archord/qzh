
Ext.define("core.view.WestView", {
  extend: 'Ext.panel.Panel',
  alias: 'widget.westview',
  collapsible: true,
  split: true,
  defaults: {
    bodyStyle: 'padding:2px'
  },
  border: 1,
  margins: '2 2 0 0',
  width: 225,
  minSize: 100,
  maxSize: 250,
  title: "功能模块导航",
  layout: 'accordion',
  layoutConfig: {
    titleCollapse: false,
    animate: true,
    activeOnTop: true
  },
  items: [{
      title: "组织机构",
      items: [{
          xtype: "treepanel",
          rootVisible: false, // 不展示根节点
          displayField: "text",
          border: 0,
          root: {
            expanded: true,
            children: [{
                text: "组织机构管理",
                id: "org-manage",
                leaf: true
              }
            ]
          }
        }]
    }, {
      title: "人员信息管理",
      items: [{
          xtype: "treepanel",
          rootVisible: false, // 不展示根节点
          displayField: "text",
          border: 0,
          root: {
            expanded: true,
            children: [
              {
                id: "fbf-manage",
                text: "发包方管理",
                leaf: true
              }, {
                id: "cbf-manage",
                text: "承包方管理",
                leaf: true
              }
            ]
          }
        }]
    }, {
      title: "地块信息管理",
      items: [{
          xtype: "treepanel",
          rootVisible: false, // 不展示根节点
          displayField: "text",
          border: 0,
          root: {
            expanded: true,
            children: [
              {
                id: "dk-manage",
                text: "地块信息管理",
                leaf: true
              },{
                id: "map-manage",
                text: "地图信息查看",
                leaf: true
              }
            ]
          }
        }]
    }, {
      title: "合同管理",
      items: [{
          xtype: "treepanel",
          rootVisible: false, // 不展示根节点
          displayField: "text",
          border: 0,
          root: {
            expanded: true,
            children: [
              {
                id: "cbht-manage",
                text: "承包合同管理",
                leaf: true
              }, {
                id: "lzhht-manage",
                text: "流转合同管理",
                leaf: true
              }
            ]
          }
        }]
    }, {
      title: "权证管理",
      items: [{
          xtype: "treepanel",
          rootVisible: false, // 不展示根节点
          displayField: "text",
          border: 0,
          root: {
            expanded: true,
            children: [
              {
                id: "qzh-generate",
                text: "权证生成",
                leaf: true
              },{
                id: "qzh-get",
                text: "权证领取",
                leaf: true
              },{
                id: "qzh-reissue",
                text: "权证补发",
                leaf: true
              },{
                id: "qzh-renew",
                text: "权证焕发",
                leaf: true
              },{
                id: "qzh-cancel",
                text: "权证注销",
                leaf: true
              }
            ]
          }
        }]
    }, {
      title: "数据统计与查询",
      items: [{
          xtype: "treepanel",
          rootVisible: false, // 不展示根节点
          displayField: "text",
          border: 0,
          root: {
            expanded: true,
            children: [
              {
                id: "org-statistic-search",
                text: "组织机构统计与查询",
                leaf: true
              }, {
                id: "people-statistic-search",
                text: "人员信息统计与查询",
                leaf: true
              }, {
                id: "dk-statistic-search",
                text: "地块信息统计与查询",
                leaf: true
              }, {
                id: "ht-statistic-search",
                text: "合同信息统计与查询",
                leaf: true
              }, {
                id: "qz-statistic-search",
                text: "权证信息统计与查询",
                leaf: true
              }
            ]
          }
        }]
    }, {
      title: "系统管理",
      items: [{
          xtype: "treepanel",
          rootVisible: false, // 不展示根节点
          displayField: "text",
          border: 0,
          root: {
            expanded: true,
            children: [
              {
                id: "system-data-manage",
                text: "系统数据维护",
                leaf: true
              }, {
                id: "system-user-manage",
                text: "系统用户管理",
                leaf: true
              }, {
                id: "data-import",
                text: "数据导入",
                leaf: true
              }
            ]
          }
        }]
    }],
  initComponent: function() {
    this.callParent(arguments);
  }
});



