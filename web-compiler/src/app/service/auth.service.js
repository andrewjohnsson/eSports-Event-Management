(function() {
  'use strict';

  angular
    .module('web')
    .service('AuthService', AuthService);

  /** @ngInject */
  function AuthService(HttpService, $q) {
    /** @ngInject */
    var vm = this;
    vm.currentUser = null;
    vm.logged = false;

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

    vm.tickets = {};
    vm.isSupervisor = false;
    vm.isManager = false;
    vm.isPlayer = false;
    vm.isViewer = false;

    vm.getPermissions = function(){
      return vm.permissions;
    };

    vm.getUser = function(){
      return vm.currentUser;
    };

    vm.setUser = function(user){
      vm.currentUser = user;
    };

    vm.login = function(user){
      var deferred = $q.defer();
      HttpService.getData({method: 'POST', url: 'auth_login', data: user}).then(function(response){
        if (response.data.user != undefined){
          vm.setUser(response.data.user);
          vm.logged = true;
          vm.tickets = response.data.user.tickets;
          deferred.resolve(true);
        }else{
          vm.setUser(null);
          vm.logged = false;
          vm.tickets = null;
          deferred.resolve(false);
        }
      }, function(){
        deferred.reject();
      });
      return deferred.promise;
    };

    vm.isLogged = function(){
      var deferred = $q.defer();
      HttpService.getData({method: 'GET', url: 'auth_isLogged'}).then(function(response){
        if (response.data.user != null && response.data.error == null){
          vm.setUser(response.data.user);
          vm.logged = true;
          vm.tickets = response.data.user.tickets;
          deferred.resolve(true);
        }else{
          vm.setUser(null);
          vm.logged = false;
          vm.tickets = null;
          deferred.resolve(false);
        }
      },function(){
        deferred.reject();
      });
      return deferred.promise;
    };

    vm.logout = function(){
      var deferred = $q.defer();
      HttpService.getData({method: 'POST', url: 'auth_logout'}).then(function(){
        vm.logged = false;
        deferred.resolve(true)
      },function(){
        deferred.resolve(false);
      },function(){
        deferred.reject();
      });
      return deferred.promise;
    };

    vm.register = function(model){
      var deferred = $q.defer();
      HttpService.getData({method: 'POST', url: 'auth_register', data: angular.toJson(model)}).then(function(response){
        deferred.resolve(response);
      },function(){
        deferred.reject();
      });
      return deferred.promise;
    }
  }

})();
