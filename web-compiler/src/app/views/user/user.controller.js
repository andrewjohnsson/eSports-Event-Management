(function() {
  'use strict';

  angular
    .module('web')
    .controller('UserController', UserController);

  /** @ngInject */
  function UserController($rootScope) {
    var vm = this;

    vm.title = 'Explore Players';
    vm.subtitle = 'And Get Info About Them';

    vm.service = $rootScope.apiService;
    vm.service.updateUsers();
  }

})();
