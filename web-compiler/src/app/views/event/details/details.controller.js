(function() {
  'use strict';

  angular
    .module('web')
    .controller('EventDetailsController', EventDetailsController);

  /** @ngInject */
  function EventDetailsController(EventService, $stateParams) {
    var vm = this;

    EventService.get().then(function(response){
      vm.event = response.events[$stateParams.id];
      vm.participants = response.participants;
    });
  }

})();
