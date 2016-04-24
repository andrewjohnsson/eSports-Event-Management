(function() {
  'use strict';

  angular
    .module('web')
    .service('HttpService', HttpService);

  /** @ngInject */
  function HttpService($http) {
    /** @ngInject */
    var vm = this;

    vm.getData = function(conf){
	    $http({
          method: conf.method,
          url: conf.url,
          headers: {'Content-Type': 'application/x-www-form-urlencoded'}
        }).success(function (data) {
          return data
        });
    }
  }

})();
