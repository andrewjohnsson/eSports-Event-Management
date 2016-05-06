(function() {
  'use strict';

  angular
    .module('web')
    .service('UserService', UserService);

  /** @ngInject */
  function UserService(HttpService, ParserService, $q, blockUI) {
    /** @ngInject */
    var vm = this;

    var usersBlock = blockUI.instances.get('usersBlock');

    vm.get = function(){
      var deferred = $q.defer();
      usersBlock.start("Loading Users...");
      HttpService.getData({method: 'GET', url: 'list_users'}).then(function(response){
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
      return HttpService.getData({method: 'POST', url: 'create_user?', data: angular.toJson(user)})
    };

    vm.edit = function(user){
      var deferred = $q.defer();
      vm.params = ParserService.parseParams($.param(user), 'user');
      HttpService.getData({method: 'POST', url: 'update_user?', data: angular.toJson(user)}).then(function(response) {
        deferred.resolve(response.data);
        usersBlock.stop();
      });
      return deferred.promise;
    };

    vm.search = function(user){
      var deferred = $q.defer();
      usersBlock.start("Searching Users...");
      HttpService.getData({method: 'POST', url: 'read_user?', data: angular.toJson(user)}).then(function(response){
        deferred.resolve(response.data);
        usersBlock.stop();
      }, function(){
        deferred.reject(null);
        usersBlock.stop();
      });
      return deferred.promise;
    };

    vm.remove = function(type, id){
      var deferred = $q.defer();
      HttpService.getData({method: 'POST', url: 'delete_user?id='+id}).then(function(){
        deferred.resolve(true)
      },function(){
        deferred.reject(false)
      });
      return deferred.promise;
    };

  }

})();