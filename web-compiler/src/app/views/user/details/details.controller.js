(function() {
  'use strict';

  angular
    .module('web')
    .controller('UserDetailsController', UserDetailsController);

  /** @ngInject */
  function UserDetailsController(UserService, $scope, $stateParams) {
    var vm = this;

    UserService.get().then(function(response){
      vm.user = response.users[$stateParams.id];
      vm.team = $scope.teamsList[vm.user.teamId];
    });
  }

})();
