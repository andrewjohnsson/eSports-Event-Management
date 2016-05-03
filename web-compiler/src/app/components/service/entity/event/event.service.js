(function() {
  'use strict';

  angular
    .module('web')
    .service('EventService', EventService);

  /** @ngInject */
  function EventService(HttpService, ParserService, $q) {
    /** @ngInject */

    var vm = this;

    vm.get = function(){
      var deferred = $q.defer();
      HttpService.getData({method: 'GET', url: 'list_events'}).then(function(response){
        deferred.resolve(response.data)
      });
      return deferred.promise;
    };

    vm.add = function(team){
        vm.params = ParserService.parseParams($.param(team), 'event')
        return HttpService.getData({method: 'POST', url: 'create_event?'+ vm.params})
    };

    vm.search = function(team){
      vm.params = ParserService.parseParams($.param(team), 'event')
      return HttpService.getData({method: 'POST', url: 'read_event?' + vm.params}).users
    };

    vm.remove = function(type, id){
      HttpService.getData({method: 'POST', url: 'delete_event?id='+id})
      return true
    }
  }

})();
