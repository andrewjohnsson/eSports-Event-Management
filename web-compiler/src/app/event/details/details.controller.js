(function() {
  'use strict';

  angular
    .module('web')
    .controller('EventDetailsController', EventDetailsController);

  /** @ngInject */
  function EventDetailsController($scope, $stateParams) {
    var vm = this;

    vm.title = vm.event.name;
    vm.subtitle = vm.event.date;

    vm.event = $scope.eventsList[$stateParams.id];
  }

})();
