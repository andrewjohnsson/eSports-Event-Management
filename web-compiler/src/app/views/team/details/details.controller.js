(function() {
  'use strict';

  angular
    .module('web')
    .controller('TeamDetailsController', TeamDetailsController);

  /** @ngInject */
  function TeamDetailsController(AuthService, ApiService, $stateParams) {
    var vm = this;

    vm.apiService = ApiService;
    vm.isManager = false;
    vm.isPlayer = false;
    vm.players = [];

    vm.apiService.updateTeams().then(function(){
      vm.apiService.teamsList.forEach(function(team){
        if (team.id == $stateParams.id){
          vm.team = team;
        }
      });
      vm.participations = vm.apiService.participations[$stateParams.id];

      vm.apiService.updateUsers().then(function() {
        vm.apiService.usersList.forEach(function(user){
          if(vm.team.userId == user.id){
            vm.manager = user;
          }
          if(user.teamId == vm.team.id){
            vm.players.push(user)
          }
        });
      });

      AuthService.isLogged().then(function(response){
        if (response == true) {
          if (vm.team.userId == AuthService.getUser().id) {
            vm.isManager = true;
          }
          if (vm.team.id == AuthService.getUser().teamId){
            vm.isPlayer = true;
          }
        }
      })
    });
  }

})();
