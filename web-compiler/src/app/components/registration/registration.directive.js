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
      controllerAs: 'regCtrl',
      bindToController: true
    };

    /** @ngInject */
    function RegisterController(AuthService, $log) {
      var vm = this;

      vm.registerFields = [
        {
          key: 'email',
          type: 'input',
          templateOptions: {
            type: 'email',
            label: 'Email address',
            placeholder: 'Email address'
          }
        },
        {
          key: 'password',
          type: 'input',
          templateOptions: {
            type: 'password',
            label: 'Password',
            placeholder: 'Password'
          }
        },
        {
          key: 'name',
          type: 'input',
          templateOptions: {
            type: 'text',
            label: 'Your name',
            placeholder: 'Name'
          }
        },
        {
          key: 'age',
          type: 'input',
          templateOptions: {
            type: 'number',
            label: 'Your Age',
            placeholder: 'Age'
          }
        },
        {
          key: 'permissions[0]',
          type: 'checkbox',
          templateOptions: {
            type: 'checkbox',
            label: 'Watch Games'
          }
        },
        {
          key: 'permissions[1]',
          type: 'checkbox',
          templateOptions: {
            type: 'checkbox',
            label: 'Play Games'
          }
        },
        {
          key: 'permissions[2]',
          type: 'checkbox',
          templateOptions: {
            type: 'checkbox',
            label: 'Manage Teams'
          }
        },
        {
          key: 'permissions[3]',
          type: 'checkbox',
          templateOptions: {
            type: 'checkbox',
            label: 'Manage Events'
          }
        }
      ];

      vm.service = AuthService;
      vm.isRegistered = false;
      vm.permissions = vm.service.getPermissions();

      vm.register = function(){
        AuthService.isLogged().then(function(data){
          if (data != true){
            var permissions = [0, 0, 0, 0];
            var selectedPermissions = Object.keys(vm.user.permissions);
            selectedPermissions.forEach(function(elem){
              permissions[elem] = 1;
            });
            vm.user.permissions = permissions.toString();
            vm.service.register(vm.user).then(function(response){
              if (response.error == null){
                vm.isRegistered = true;
              }
            });
          }else{
            alert('You should log out to register new user!');
          }
        });
      }
    }

    return directive;
  }

})();
