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
      var deferred = $q.defer()
      $http({
        method: conf.method,
        url: conf.url,
        headers: {'Content-Type': 'application/x-www-form-urlencoded'}
      }).then(function (response) {
        deferred.resolve(response)
      }, function(err){
        deferred.reject(undefined)
        alert('Failed to load ' + conf.url + '!')
      });
      return deferred.promise;
    }
  }

})();
