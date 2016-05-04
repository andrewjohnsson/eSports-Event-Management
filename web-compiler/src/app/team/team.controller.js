(function() {
  'use strict';

  angular
    .module('web')
    .controller('TeamController', TeamController);

  /** @ngInject */
  function TeamController(TeamService) {
    var vm = this;

    vm.title = 'Explore Teams';
    vm.subtitle = 'and get schedules';

    TeamService.get().then(function(response){
      vm.teamsList = response.teams;
      vm.participations = response.participations;
    });
  }

})();
