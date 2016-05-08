(function() {
  'use strict';

  angular
    .module('web')
    .controller('EventDetailsController', EventDetailsController);

  /** @ngInject */
  function EventDetailsController(ApiService, AuthService, $stateParams) {
    var vm = this;
    vm.apiService = ApiService;
    vm.isSupervisor = false;

    vm.apiService.updateEvents().then(function(){
      vm.apiService.eventsList.forEach(function(event){
        if (event.id == $stateParams.id){
          vm.event = event;

        }
      });

      vm.participants = vm.apiService.participants[$stateParams.id];
      vm.apiService.updateUsers().then(function() {
        vm.apiService.usersList.forEach(function(user){
          if(user.eventId == vm.event.id){
            vm.supervisor = user;
          }
        });
      });
      AuthService.isLogged().then(function(response){
        if (response == true) {
          if (vm.event.id == AuthService.getUser().eventId) {
            vm.isSupervisor = true;
          }
        }
      })
    });
  }

})();
