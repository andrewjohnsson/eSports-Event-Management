(function() {
  'use strict';

  angular
    .module('web')
    .service('HttpService', HttpService);

  /** @ngInject */
  function HttpService($http, $q) {
    /** @ngInject */
    var vm = this;

    vm.getData = function(conf){
      var deferred = $q.defer();
      $http({
        method: conf.method,
        url: conf.url,
        data: conf.data,
        dataType: 'json',
        headers: {'Content-Type': 'application/json; charset=utf-8'}
      }).then(function (response) {
        deferred.resolve(response)
      }, function(){
        deferred.reject(undefined)
      });
      return deferred.promise;
    }
  }

})();
