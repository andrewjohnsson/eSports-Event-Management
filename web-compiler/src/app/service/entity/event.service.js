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
      },function(){
        deferred.reject();
      });
      return deferred.promise;
    };

    vm.add = function(model){
      return HttpService.getData({method: 'POST', url: 'event_create', data: angular.toJson(model)});
    };

    vm.edit = function(model){
      var deferred = $q.defer();
      HttpService.getData({method: 'POST', url: 'event_update', data: {event: model}}).then(function(response){
        deferred.resolve(response.data);
      },function(){
        deferred.reject();
      });
      return deferred.promise;
    };

    vm.remove = function(model){
      var deferred = $q.defer();
      HttpService.getData({method: 'POST', url: 'event_delete', data: {event: model}}).then(function(response){
        if (response.data.error == null){
          deferred.resolve(true);
        }else{
          deferred.resolve(false)
        }
      },function(){
        deferred.reject();
      });
      return deferred.promise;
    }
  }

})();
