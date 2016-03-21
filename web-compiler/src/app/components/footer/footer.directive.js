(function() {
  'use strict';

  angular
    .module('web')
    .directive('footerDirective', footer);

  /** @ngInject */
  function footer() {
    var directive = {
      restrict: 'E',
      templateUrl: 'app/components/footer/footer.html',
      scope: {
          creationDate: '='
      },
      controller: FooterController,
      controllerAs: 'footer',
      bindToController: true
    };

    return directive;

    /** @ngInject */
    function FooterController() {
      var vm = this;
      vm.link = "https://github.com/andrewjohnsson/eSports-Event-Management";
    }
  }

})();
