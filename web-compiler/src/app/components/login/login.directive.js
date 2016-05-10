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
      controllerAs: 'ctrl',
      bindToController: true
    };

    /** @ngInject */
    function LoginController($rootScope) {
      var vm = this;

      vm.loginFields = [
        {
          key: 'user.email',
          type: 'input',
          templateOptions: {
            type: 'email',
            placeholder: 'Email address'
          }
        },
        {
          key: 'user.password',
          type: 'input',
          templateOptions: {
            type: 'password',
            placeholder: 'Password'
          }
        }
      ];

      vm.service = $rootScope.apiService;

      vm.service.authService.isLogged().then(function(){
        vm.isLogged = vm.service.authService.logged;
      }, function(){
        vm.isLogged = vm.service.authService.logged;
      });

      vm.login = function(){
        vm.service.authService.login(vm.loginModel).then(function(data){
          if (data != true){
            alert('Wrong Credentials');
          }else{
            vm.isLogged = vm.service.authService.logged;
          }
        }, function(){
          alert('Login Failed')
        });
      };
    }

    return directive;
  }

})();
