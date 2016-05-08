(function() {
  'use strict';

  angular
    .module('web')
    .controller('UserDetailsController', UserDetailsController);

  /** @ngInject */
  function UserDetailsController(ApiService, $stateParams) {
    var vm = this;

    vm.apiService = ApiService;

    vm.apiService.updateUsers().then(function(){
      vm.apiService.usersList.forEach(function(user){
        if (user.id == $stateParams.id){
          vm.user = user;
        }
      });

      vm.apiService.updateTeams().then(function(){
        vm.apiService.teamsList.forEach(function(team){
          if (team.id == vm.user.teamId){
            vm.team = team;
          }
          if (team.userId == vm.user.id){
            vm.managedTeam = team;
          }
        });
      });

      vm.apiService.updateEvents().then(function(){
        vm.apiService.eventsList.forEach(function(event){
          if (event.id == vm.user.eventId){
            vm.event = event;
          }
        })
      });
    });
  }

})();
