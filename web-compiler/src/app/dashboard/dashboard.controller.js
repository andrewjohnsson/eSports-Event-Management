(function() {
  'use strict';

  angular
    .module('web')
    .controller('DashboardController', DashboardController);

  /** @ngInject */
  function DashboardController(UserService, TeamService, EventService) {
    var vm = this;

    vm.list = function() {
      UserService.get().then(function(response){
        vm.userslist = response.users;
      });

      TeamService.get().then(function(response){
        vm.teamsList = response.teams;
        vm.participations = response.participations;
      });

      EventService.get().then(function(response){
        vm.eventsList = response.events;
        vm.participants = response.participants;
      });

    };

    vm.list();

    vm.addUser = function(item){
      UserService.add(item).then(function(){
        vm.list();
      });
    };

    vm.findUser = function(item){
      UserService.search(item).then(function(response){
        vm.userslist = response.users;
      });
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
