(function() {
  'use strict';

  angular
    .module('web')
    .service('AuthService', AuthService);

  /** @ngInject */
  function AuthService(HttpService, blockUI, $q, $log) {
    /** @ngInject */
    var vm = this;

    vm.getPermissions = function(){
      return vm.permissions;
    };

    var loginForm = blockUI.instances.get('loginFormBlock');
    var userInfoBlock = blockUI.instances.get('userInfoBlock');

    vm.getUser = function(){
      return vm.currentUser;
    };

    vm.setUser = function(user){
      vm.currentUser = user;
    };

    vm.login = function(user){
      var deferred = $q.defer();
      loginForm.start("Logging in...");
      HttpService.getData({method: 'POST', url: 'auth_login', data: user}).then(function(response){
        if (response.data.user != undefined){
          vm.currentUser = response.data.user;
          $log.log(vm.currentUser);
          deferred.resolve(true);
          loginForm.stop();
        }else{
          deferred.resolve(false);
          loginForm.stop();
        }
      }, function(){
        loginForm.stop();
        deferred.reject();
      });
      return deferred.promise;
    };

    vm.isLogged = function(){
      var deferred = $q.defer();
      HttpService.getData({method: 'GET', url: 'auth_isLogged'}).then(function(response){
        if (response.data.user != null && response.data.error == null){
          vm.setUser(response.data.user);
          deferred.resolve(true);
        }else{
          deferred.resolve(false);
        }
      },function(){
        deferred.reject();
      });
      return deferred.promise;
    };

    vm.logout = function(){
      var deferred = $q.defer();
      userInfoBlock.start('Wait please');
      HttpService.getData({method: 'POST', url: 'auth_logout'}).then(function(response){
        userInfoBlock.stop();
        deferred.resolve(response.data)
      },function(){
        userInfoBlock.stop();
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
