(function() {
  'use strict';

  angular
    .module('web')
    .directive('loginDirective', login);

  /** @ngInject */
  function login() {
    var directive = {
      restrict: 'E',
      templateUrl: 'app/components/login/login.html',
      controller: LoginController,
      controllerAs: 'login',
      bindToController: true
    };

    /** @ngInject */
    function LoginController() {
      var vm = this;

      vm.submit = function(){
        alert('Clicked on login');
      }
    }

    return directive;
  }

})();
