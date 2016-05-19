(function() {
  'use strict';

  angular
    .module('web')
    .controller('EventDetailsController', EventDetailsController);

  /** @ngInject */
  function EventDetailsController(ApiService, $rootScope, $stateParams) {
    var vm = this;

    if (!$rootScope.apiService){
      $rootScope.apiService = ApiService;
    }

    vm.tickets = {};

    vm.service = $rootScope.apiService;
    vm.isSupervisor = vm.service.authService.isSupervisor;
    vm.isPlayer = vm.service.authService.isPlayer;
    vm.isViewer = vm.service.authService.isViewer;

    vm.service.updateEvents().then(function(){
      vm.service.eventsList.forEach(function(event){
        if (event.id == $stateParams.id){
          vm.event = event;
        }
      });

      vm.participants = vm.service.participants[$stateParams.id];

      vm.service.updateUsers().then(function() {
        vm.service.usersList.forEach(function(user){
          if(user.eventId == vm.event.id){
            vm.supervisor = user;
          }
        });
      });

      vm.service.authService.isLogged().then(function(response){
        if (response == true) {
          if (vm.event.id == vm.service.authService.getUser().eventId) {
            vm.isSupervisor = true;
          }
          vm.participants.forEach(function(team){
            if (team.id == vm.service.authService.getUser().teamId){
              vm.isPlayer = true;
              vm.team = team;
              if (vm.service.authService.tickets != null){
                vm.service.authService.tickets.forEach(function(ticket){
                  if (ticket.eventId == vm.event.id){
                    vm.isViewer = true;
                    vm.tickets.push(ticket);
                  }
                })
              }
            }
          });
        }
      })
    });
  }

})();
