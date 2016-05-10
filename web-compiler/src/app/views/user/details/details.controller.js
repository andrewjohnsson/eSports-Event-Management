(function() {
  'use strict';

  angular
    .module('web')
    .controller('UserDetailsController', UserDetailsController);

  /** @ngInject */
  function UserDetailsController($rootScope, ApiService, $stateParams) {
    var vm = this;

    if (!$rootScope.apiService){
      $rootScope.apiService = ApiService;
    }

    vm.service = $rootScope.apiService;

    vm.isSupervisor = vm.service.authService.isSupervisor;
    vm.isManager = vm.service.authService.isManager;
    vm.isPlayer = vm.service.authService.isPlayer;
    vm.isAdmin = vm.service.authService.isAdmin;

    vm.currentUser = vm.service.authService.currentUser;

    vm.service.updateUsers().then(function(){
      vm.service.usersList.forEach(function(user){
        if (user.id == $stateParams.id){
          vm.user = user;
        }
      });

      vm.service.updateTeams().then(function(){
        vm.service.teamsList.forEach(function(team){
          if (team.id == vm.user.teamId){
            vm.team = team;
            vm.isPlayer = true;
          }
          if (team.userId == vm.user.id){
            vm.managedTeam = team;
            vm.isManager = true;
          }
        });
      });

      vm.service.updateEvents().then(function(){
        vm.service.eventsList.forEach(function(event){
          if (event.id == vm.user.eventId){
            vm.event = event;
            vm.isSupervisor = true;
          }
        })
      });
    });
  }

})();
