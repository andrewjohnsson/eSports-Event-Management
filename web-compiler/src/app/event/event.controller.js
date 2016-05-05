(function() {
  'use strict';

  angular
    .module('web')
    .controller('EventController', EventController);

  /** @ngInject */
  function EventController(EventService, $scope) {
    var vm = this;

    vm.title = 'Explore Events';
    vm.subtitle = 'and buy tickets';

    EventService.get().then(function(response){
      $scope.eventsList = response.events;
      $scope.participants = response.participants;
    });
  }

})();
