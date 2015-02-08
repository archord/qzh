Ext.define("core.data_import.controller.DataImportController", {
  extend: "Ext.app.Controller",
  init: function() {
    var self = this;
    this.control({
      /**showbefor*/
      "fbfLayout": {
        beforeshow: function(layout, opt) {
        }
      }

    });
  },
  views: [
    "core.data_import.view.DataImportLayout",
    "core.data_import.view.DataImportForm"
  ],
  stores: ["core.data_import.store.DataImportStore"],
  models: ["core.data_import.model.DataImportModel"]
});