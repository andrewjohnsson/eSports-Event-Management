(function() {
  'use strict';

  angular
    .module('web')
    .controller('DashboardController', DashboardController);

  /** @ngInject */
  function DashboardController(ApiService) {
    var vm = this;

    vm.playerForm = {};
    vm.teamForm = {};
    vm.eventForm = {};

    vm.service = ApiService;
    vm.service.updateData();

    vm.add = function(type, item){
      switch (type){
        case 'user':
          vm.service.createUser(item);
          break;
        case 'team':
          vm.service.createTeam(item);
          break;
        case 'event':
          vm.service.createEvent(item);
          break;
      }
    };

    vm.edit = function(type, item){
      switch (type){
        case 'user':
          vm.service.updateUser(item);
          break;
        case 'team':
          vm.service.updateTeam(item);
          break;
        case 'event':
          vm.service.updateEvent(item);
          break;
      }
    };

    /*vm.find = function(type, item){
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
    };*/

    vm.remove = function(type, id){
      switch (type){
        case 'user':
          vm.service.deleteUser(id);
          break;
        case 'team':
          vm.service.deleteTeam(id);
          break;
        case 'event':
          vm.service.deleteEvent(id);
          break;
      }
    }
  }

})();
