(function() {
  'use strict';

  angular
    .module('web')
    .service('UserService', UserService);

  /** @ngInject */
  function UserService(HttpService, ParserService, $q) {
    /** @ngInject */
    var vm = this;

    vm.get = function(){
      var deferred = $q.defer();
      HttpService.getData({method: 'GET', url: 'list_users'}).then(function(response){
        deferred.resolve(response.data.users)
      });
      return deferred.promise;
    };

    vm.add = function(user){
      vm.params = ParserService.parseParams($.param(user), 'user');
      return HttpService.getData({method: 'POST', url: 'create_user?'+ vm.params})
    }

    vm.search = function(user){
      vm.params = ParserService.parseParams($.param(user), 'user');
      return HttpService.getData({method: 'POST', url: 'read_user?' + vm.params}).users
    }

    vm.remove = function(type, id){
      HttpService.getData({method: 'POST', url: 'delete_user?id='+id})
      return true
    }
  }

})();
