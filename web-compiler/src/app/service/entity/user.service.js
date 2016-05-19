(function() {
  'use strict';

  angular
    .module('web')
    .service('UserService', UserService);

  /** @ngInject */
  function UserService(HttpService, $q, blockUI) {
    /** @ngInject */
    var vm = this;

    var usersBlock = blockUI.instances.get('usersBlock');

    vm.get = function(){
      var deferred = $q.defer();
      usersBlock.start("Loading Users...");
      HttpService.getData({method: 'GET', url: 'user_list'}).then(function(response){
        if (response.data != null){
          usersBlock.stop();
          deferred.resolve(response.data)
        }else{
          deferred.resolve(null)
        }
      }, function(){
        usersBlock.stop('Failed To Load Users');
        deferred.reject();
      });
      return deferred.promise;
    };

    vm.add = function(user){
      return HttpService.getData({method: 'POST', url: 'user_create', data: angular.toJson(user)})
    };

    vm.edit = function(user){
      var deferred = $q.defer();
      HttpService.getData({method: 'POST', url: 'user_update', data: {user: user}}).then(function(response) {
        deferred.resolve(response.data);
      });
      return deferred.promise;
    };

    vm.remove = function(user){
      var deferred = $q.defer();
      HttpService.getData({method: 'POST', url: 'user_delete', data: {user: user}}).then(function(){
        deferred.resolve(true)
      },function(){
        deferred.reject(false)
      });
      return deferred.promise;
    };

  }

})();
