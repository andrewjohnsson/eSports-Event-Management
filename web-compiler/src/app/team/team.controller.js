(function() {
  'use strict';

  angular
    .module('web')
    .controller('TeamController', TeamController);

  /** @ngInject */
  function TeamController(TeamService, $scope) {
    var vm = this;

    vm.title = 'Explore Teams';
    vm.subtitle = 'and get schedules';

    TeamService.get().then(function(response){
      $scope.teamsList = response.teams;
      $scope.participations = response.participations;
    });
  }

})();
