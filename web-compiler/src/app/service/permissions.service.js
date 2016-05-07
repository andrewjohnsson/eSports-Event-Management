(function() {
  'use strict';

  angular
    .module('web')
    .service('PermissionService', PermissionService);

  /** @ngInject */
  function PermissionService(HttpService, AuthService, $q) {
    /** @ngInject */
    var vm = this;
    vm.authService = AuthService;
    vm.httpService = HttpService;

    vm.isAdmin = function(){
      var deferred = $q.defer();
      HttpService.getData({method: 'POST', url: 'isAdmin'}).then(function(response){
        deferred.resolve(response.data);
      }, function(){
        deferred.reject();
      });
      return deferred.promise;
    }
  }

})();
