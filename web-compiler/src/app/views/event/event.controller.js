(function() {
  'use strict';

  angular
    .module('web')
    .controller('EventController', EventController);

  /** @ngInject */
  function EventController(ApiService) {
    var vm = this;

    vm.title = 'Explore Events';
    vm.subtitle = 'and buy tickets';

    vm.service = ApiService;
    vm.service.updateEvents();
  }

})();
