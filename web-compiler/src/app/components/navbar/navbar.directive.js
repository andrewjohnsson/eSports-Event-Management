(function() {
  'use strict';

  angular
    .module('web')
    .directive('navbarDirective', navbar);

  /** @ngInject */
  function navbar() {
    var directive = {
      restrict: 'E',
      templateUrl: 'app/components/navbar/navbar.html',
      controller: NavbarController,
      controllerAs: 'menu',
      bindToController: true
    };

    /** @ngInject */
    function NavbarController($rootScope, ApiService) {
      var vm = this;

      if (!$rootScope.apiService){
        $rootScope.apiService = ApiService;
      }

      vm.service = $rootScope.apiService;
      vm.currentUser = vm.service.authService.isLogged();

      vm.brand = {
        title: 'LabSPP',
        link: '/'
      };

      vm.items = [
        {
          title: 'Players',
          link: 'user.list'
        },
        {
          title: 'Teams',
          link: 'team.list'
        },
        {
          title: 'Events',
          link: 'event.list'
        },
        {
          title: 'About Us',
          link: 'about'
        }
      ];
    }

    return directive;
  }

})();
