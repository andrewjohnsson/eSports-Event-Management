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
          title: 'Players',
          link: 'user'
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
