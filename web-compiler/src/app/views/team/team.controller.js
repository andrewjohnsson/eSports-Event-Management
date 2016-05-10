(function() {
  'use strict';

  angular
    .module('web')
    .controller('TeamController', TeamController);

  /** @ngInject */
  function TeamController($rootScope, ApiService) {
    var vm = this;

    vm.title = 'Explore Teams';
    vm.subtitle = 'and get schedules';

    if (!$rootScope.apiService){
      $rootScope.apiService = ApiService;
    }

    vm.service = $rootScope.apiService;
    vm.service.updateTeams();
  }

})();
