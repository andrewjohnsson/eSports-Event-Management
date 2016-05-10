(function() {
  'use strict';

  angular
    .module('web')
    .service('TeamService', TeamService);

  /** @ngInject */
  function TeamService(HttpService, $q, blockUI) {
    /** @ngInject */

    var vm = this;

    var teamsBlock = blockUI.instances.get('teamsBlock');

    vm.get = function(){
      var deferred = $q.defer();
      teamsBlock.start("Loading Teams...");
      HttpService.getData({method: 'GET', url: 'team_list'}).then(function(response){
        teamsBlock.stop();
        deferred.resolve(response.data);
      });
      return deferred.promise;
    };

    vm.add = function(model){
      return HttpService.getData({method: 'POST', url: 'team_create', data: angular.toJson(model)})
    };

    vm.edit = function(model){
      var deferred = $q.defer();
      teamsBlock.start("Editing Team...");
      HttpService.getData({method: 'POST', url: 'team_update', data: {team: model}}).then(function(response){
        teamsBlock.stop();
        deferred.resolve(response.data);
      });
      return deferred.promise;
    };

    vm.search = function(model){
      return HttpService.getData({method: 'POST', url: 'team_read', data: {team: model}}).users
    };

    vm.remove = function(model){
      var deferred = $q.defer();
      HttpService.getData({method: 'POST', url: 'team_delete', data: {team: model}}).then(function(response){
        deferred.resolve(response.data)
      }, function(){
        deferred.reject();
      });
      return deferred.promise;
    }

  }

})();
