(function() {
  'use strict';

  angular
    .module('web')
    .service('HttpService', HttpService);

  /** @ngInject */
  function HttpService($http, $q, $log, FileSaver, Blob) {
    /** @ngInject */
    var vm = this;

    vm.getData = function(conf){
      var deferred = $q.defer();
      $http({
        method: conf.method,
        url: conf.url,
        data: conf.data,
        headers: {'Content-Type': 'application/json; charset=utf-8'}
      }).then(function (response) {
        deferred.resolve(response)
      }, function(){
        deferred.reject(undefined)
      });
      return deferred.promise;
    };

    vm.getDocument = function(conf){
      switch (conf.data.docType){
        case 'EXCEL':
          vm.header = 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet';
          vm.extension = '.xls';
          break;
        case 'CSV':
          vm.header = 'application/csv';
          vm.extension = '.csv';
          break;
        case 'PDF':
          vm.header = 'application/pdf';
          vm.extension = '.pdf';
          break;
      }
      $http({
        url: 'generate_' + conf.type,
        method: 'POST',
        responseType: 'arraybuffer',
        data: conf.data,
        headers: {
          'Content-type': 'application/json',
          'Accept': vm.header
        }
      }).then(function(response){
          $log.log(response);
          var blob = new Blob([response.data], {type: vm.header});
          FileSaver.saveAs(blob, conf.type + vm.extension);
        },function(){
          var blob = new Blob([response.data], {type: vm.header});
          FileSaver.saveAs(blob, conf.type + vm.extension);
          alert('Error occured while getting your document!')
        });
    }
  }

})();
