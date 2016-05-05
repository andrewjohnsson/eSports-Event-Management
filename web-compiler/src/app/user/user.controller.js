(function() {
  'use strict';

  angular
    .module('web')
    .controller('UserController', UserController);

  /** @ngInject */
  function UserController(UserService, $scope) {
    var vm = this;

    vm.title = 'Explore Players';
    vm.subtitle = 'And Get Info About Them';

    UserService.get().then(function(response){
      $scope.userslist = response.users;
    });
  }

})();
