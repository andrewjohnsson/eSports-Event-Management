(function() {
  'use strict';

  angular
    .module('web')
    .service('EventService', EventService);

  /** @ngInject */
  function EventService(HttpService, $q, $log, blockUI) {
    /** @ngInject */

    var vm = this;

    var eventsBlock = blockUI.instances.get('eventsBlock');

    vm.get = function(){
      var deferred = $q.defer();
      eventsBlock.start("Loading Events...");
      HttpService.getData({method: 'GET', url: 'list_event'}).then(function(response){
        eventsBlock.stop();
        deferred.resolve(response.data)
      });
      return deferred.promise;
    };

    vm.add = function(event){
      return HttpService.getData({method: 'POST', url: 'create_event', data: angular.toJson(event)});
    };

    vm.search = function(event){
      var deferred = $q.defer();
      HttpService.getData({method: 'POST', url: 'find_event', data: angular.toJson(event)}).then(
        function(response){
          deferred.resolve(response.data);
        },
        function(){
          deferred.reject(null);
        }
      );
      return deferred.promise;
    };

    vm.remove = function(type, id){
      HttpService.getData({method: 'POST', url: 'delete_event?id='+id});
      return true
    }
  }

})();
