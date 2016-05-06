(function() {
  'use strict';

  angular
    .module('web')
    .controller('TeamDetailsController', TeamDetailsController);

  /** @ngInject */
  function TeamDetailsController(TeamService, $stateParams) {
    var vm = this;

    TeamService.get().then(function(response){
      vm.team = response.teams[$stateParams.id];
      vm.participations = response.participations;
    });
  }

})();
