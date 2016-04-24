(function() {
  'use strict';

  angular
    .module('web')
    .service('UserService', UserService);

  /** @ngInject */
  function UserService(HttpService, ParserService) {
    /** @ngInject */
    var vm = this;

    vm.get = function(){
	    var data = HttpService.getData({method: 'GET', url: 'list_users'})
	    if (data != undefined){
		    return data
	    }else{
		    alert('Problems with getting the list of users')
	    }
    }

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
