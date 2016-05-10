(function() {
  'use strict';

  angular
    .module('web')
    .directive('loggedDirective', logged);

  /** @ngInject */
  function logged() {
    var directive = {
      restrict: 'E',
      templateUrl: 'app/components/logged/logged.html',
      controller: LoggedInController,
      controllerAs: 'ctrl',
      bindToController: true
    };

    /** @ngInject */
    function LoggedInController($rootScope, $location) {
      var vm = this;

      vm.service = $rootScope.apiService;

      vm.logout = function(){
        vm.service.authService.logout().then(function(response){
            if (response){
              $location.url('/')
            }
          },
          function(){
            alert('Unable to logout!')
          });
      }

    }

    return directive;
  }

})();
