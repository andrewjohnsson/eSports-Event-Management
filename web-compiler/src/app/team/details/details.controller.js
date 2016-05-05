(function() {
  'use strict';

  angular
    .module('web')
    .controller('TeamDetailsController', TeamDetailsController);

  /** @ngInject */
  function TeamDetailsController($stateParams, $scope) {
    var vm = this;

    vm.title = vm.event.name;
    vm.subtitle = vm.event.date;

    $scope.team = $scope.teamsList[$stateParams.id];
  }

})();
