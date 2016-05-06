(function() {
  'use strict';

  angular
    .module('web')
    .service('EventService', EventService);

  /** @ngInject */
  function EventService(HttpService, $q, blockUI) {
    /** @ngInject */

    var vm = this;

    var eventsBlock = blockUI.instances.get('eventsBlock');

    vm.get = function(){
      var deferred = $q.defer();
      eventsBlock.start("Loading Events...");
      HttpService.getData({method: 'GET', url: 'event_list'}).then(function(response){
        eventsBlock.stop();
        deferred.resolve(response.data)
      });
      return deferred.promise;
    };

    vm.add = function(event){
      return HttpService.getData({method: 'POST', url: 'event_create', data: angular.toJson(event)});
    };

    vm.search = function(event){
      var deferred = $q.defer();
      HttpService.getData({method: 'POST', url: 'event_read', data: angular.toJson(event)}).then(
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
      HttpService.getData({method: 'POST', url: 'event_delete?id='+id});
      return true
    }
  }

})();
