(function() {
  'use strict';

  angular
    .module('web')
    .controller('UserController', UserController);

  /** @ngInject */
  function UserController(UserService, $log) {
    var vm = this;

    vm.title = 'Explore Players';
    vm.subtitle = 'And Get Info About Them';

    UserService.get().then(function(response){
      vm.userslist = response.users;
    });
  }

})();
