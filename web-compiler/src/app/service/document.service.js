(function() {
  'use strict';

  angular
    .module('web')
    .service('DocumentService', DocumentService);

  /** @ngInject */
  function DocumentService(HttpService) {
    /** @ngInject */
    var vm = this;

    vm.docFormat = 'PDF';

    vm.docFormats = [
      {
        id: 'PDF',
        value: 'PDF'
      },
      {
        id: 'CSV',
        value: 'CSV'
      },
      {
        id: 'EXCEL',
        value: 'Excel File (XLS)'
      }
    ];

    vm.generatePass = function(user, event, doctype){
      HttpService.getDocument({type: 'pass', data: {user: user, event: event, docType: doctype}});
    };

    vm.generateReport = function(type, doctype){
      HttpService.getDocument({type: type+'_report', data: {docType: doctype}});
    };

    vm.generateTicket = function(event, team, user, ticket, doctype){
      HttpService.getDocument({type: 'ticket', data: {user: user, event: event, team: team, ticket: ticket, docType: doctype}});
    };
  }

})();
