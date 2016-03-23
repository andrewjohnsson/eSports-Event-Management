(function() {
  'use strict';

  angular
    .module('web')
    .directive('registerDirective', register);

  /** @ngInject */
  function register() {
    var directive = {
      restrict: 'E',
      templateUrl: 'app/components/registration/registration.html',
      controller: RegisterController,
      controllerAs: 'register',
      bindToController: true
    };

    /** @ngInject */
    function RegisterController() {
      var vm = this;

      vm.submit = function(){
        alert('Clicked on register');
      }
    }

    return directive;
  }

})();
