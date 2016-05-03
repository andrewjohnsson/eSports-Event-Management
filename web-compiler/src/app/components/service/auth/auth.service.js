(function() {
  'use strict';

  angular
    .module('web')
    .service('AuthService', AuthService);

  /** @ngInject */
  function AuthService(HttpService, blockUI, $q) {
    /** @ngInject */
    var vm = this;

    var currentUser = {};

    vm.permissions = [
      { nodeIndex: 0, description: 'Watch Games'},
      { nodeIndex: 1, description: 'Play Games'},
      { nodeIndex: 2, description: 'Manage Teams'},
      { nodeIndex: 3, description: 'Manage Events'}
    ];

    vm.getPermissions = function(){
      return vm.permissions;
    };

    vm.addPermission = function(description){
      vm.permissions.push({nodeIndex: Object.keys(vm.permissions).length+1, description: description})
    };

    vm.rmPermission = function(id){
      if (vm.permissions[id] != undefined){
        vm.permissions.splice(id, 1);
      }
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
      var entity = user;
      loginForm.start("Logging in...");
      HttpService.getData({method: 'POST', url: 'login', data: user}).then(function(response){
        if (response.data.user != undefined){
          vm.setUser(response.data.user);
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
      HttpService.getData({method: 'GET', url: 'check'}).then(function(response){
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
      HttpService.getData({method: 'POST', url: 'logout'}).then(function(response){
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
      HttpService.getData({method: 'POST', url: 'register', data: angular.toJson(model)}).then(function(response){
        deferred.resolve(response);
      },function(){
        deferred.reject();
      });
      return deferred.promise;
    }
  }

})();
