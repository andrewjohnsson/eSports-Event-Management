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
    function NavbarController() {
      var vm = this;

      vm.brand = {
        title: 'LabSPP',
        link: '/'
      };

      vm.items = [
        {
          title: 'Users',
          link: 'user'
        },
        {
          title: 'Teams',
          link: 'team'
        },
        {
          title: 'Events',
          link: 'event'
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
