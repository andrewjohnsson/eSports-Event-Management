(function() {
  'use strict';

  angular
    .module('web')
    .controller('EventController', EventController);

  /** @ngInject */
  function EventController($rootScope) {
    var vm = this;

    vm.title = 'Explore Events';
    vm.subtitle = 'and buy tickets';

    vm.service = $rootScope.apiService;
    vm.service.updateEvents();
  }

})();
