(function() {
  'use strict';

  angular
    .module('web')
    .service('TeamService', TeamService);

  /** @ngInject */
  function TeamService(HttpService, ParserService, $q) {
    /** @ngInject */

    var vm = this;

    vm.get = function(){
      var deferred = $q.defer();
      HttpService.getData({method: 'GET', url: 'list_teams'}).then(function(response){
        deferred.resolve(response.data)
      });
      return deferred.promise;
    };

    vm.add = function(team){
        vm.params = ParserService.parseParams($.param(team), 'team')
        return HttpService.getData({method: 'POST', url: 'create_team?'+ vm.params})
    };

    vm.search = function(team){
      vm.params = ParserService.parseParams($.param(team), 'team')
      return HttpService.getData({method: 'POST', url: 'read_team?' + vm.params}).users
    };

    vm.remove = function(type, id){
      HttpService.getData({method: 'POST', url: 'delete_team?id='+id})
      return true
    }

  }

})();
