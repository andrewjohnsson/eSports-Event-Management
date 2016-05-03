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
    function LoginController(AuthService, $log, $location) {
      var vm = this;

      vm.loginFields = [
        {
          key: 'user.email',
          type: 'input',
          templateOptions: {
            type: 'email',
            label: 'Email address',
            placeholder: 'Email address'
          }
        },
        {
          key: 'user.password',
          type: 'input',
          templateOptions: {
            type: 'password',
            label: 'Password',
            placeholder: 'Password'
          }
        }
      ];

      vm.originalFields = angular.copy(vm.loginFields);

      vm.isLogged = false;

      AuthService.isLogged().then(function(data){
        if (data == true){
          vm.isLogged = true;
          vm.currentUser = AuthService.getUser();
          $log.log(vm.currentUser);
        }
      });

      vm.login = function(){
        AuthService.login(vm.loginModel).then(function(data){
          if (data == true){
            vm.isLogged = true;
            vm.currentUser = AuthService.getUser();
            vm.loginFields = angular.copy(vm.originalFields);
          }else{
            alert('Wrong Credentials');
          }
        }, function(){
          $log.error('Login Failed For User ' + vm.loginModel.user.email)
        });
      };

      vm.logout = function(){
        AuthService.isLogged().then(function(){
          AuthService.logout().then(function(){
            vm.isLogged = false;
            $location.url('/');
          });
        });
      }
    }

    return directive;
  }

})();
