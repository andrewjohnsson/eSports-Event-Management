(function() {
  'use strict';

  angular
    .module('web')
    .controller('TeamDetailsController', TeamDetailsController);

  /** @ngInject */
  function TeamDetailsController(ApiService, $rootScope, $stateParams) {
    var vm = this;

    if (!$rootScope.apiService){
      $rootScope.apiService = ApiService;
    }

    vm.service = $rootScope.apiService;

    vm.isManager = vm.service.authService.isManager;
    vm.players = [];

    vm.service.updateTeams().then(function(){
      vm.service.teamsList.forEach(function(team){
        if (team.id == $stateParams.id){
          vm.team = team;
        }
      });

      vm.participations = vm.service.participations[$stateParams.id];

      vm.service.updateUsers().then(function() {
        vm.service.usersList.forEach(function(user){
          if(vm.team.userId == user.id){
            vm.manager = user;
          }
          if(user.teamId == vm.team.id){
            vm.players.push(user)
          }
        });
      });

      vm.service.authService.isLogged().then(function(response){
        if (response == true) {
          if (vm.team.userId == vm.service.authService.getUser().id) {
            vm.isManager = true;
          }
        }
      })
    });
  }

})();
