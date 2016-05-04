(function() {
  'use strict';

  angular
    .module('web')
    .controller('EventController', EventController);

  /** @ngInject */
  function EventController(EventService) {
    var vm = this;

    vm.title = 'Explore Events';
    vm.subtitle = 'and buy tickets';

    EventService.get().then(function(response){
      vm.eventsList = response.events;
      vm.participants = response.participants;
    });
  }

})();
