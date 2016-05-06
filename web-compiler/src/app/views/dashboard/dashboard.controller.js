(function() {
  'use strict';

  angular
    .module('web')
    .controller('DashboardController', DashboardController);

  /** @ngInject */
  function DashboardController(ApiService, $log) {
    var vm = this;

    vm.playerForm = {};
    vm.teamForm = {};
    vm.eventForm = {};

    vm.service = ApiService;
    vm.service.updateData();

    vm.add = function(type, item){
      switch (type){
        case 'user':
          UserService.add(item).then(function(){
            vm.list();
          });
          break;
        case 'team':
          TeamService.add(item).then(function(){
            vm.list();
          });
          break;
        case 'event':
          EventService.add(item).then(function(){
            vm.list();
          });
          break;
      }
    };

    vm.edit = function(type, item){
      switch (type){
        case 'user':
          UserService.edit(item).then(function(){
            vm.list();
          });
          break;
        case 'team':
          $log.log(item);
          TeamService.edit({team: item}).then(function(){
            vm.list();
          });
          break;
        case 'event':
          EventService.edit(item).then(function(){
            vm.list();
          });
          break;
      }
    };

    vm.find = function(type, item){
      switch (type){
        case 'user':
          UserService.search(item).then(function(response){
            vm.userslist = response.users;
          });
          break;
        case 'team':
          TeamService.search(item).then(function(response){
            vm.teamslist = response.teams;
          });
          break;
        case 'event':
          EventService.search(item).then(function(response){
            vm.eventslist = response.events;
          });
          break;
      }
    };

    vm.remove = function(type, id){
      switch (type){
        case 'user':
          UserService.remove(id);
          break;
        case 'team':
          TeamService.remove(id);
          break;
        case 'event':
          EventService.remove(id);
          break;
      }
      vm.list();
    }
  }

})();
