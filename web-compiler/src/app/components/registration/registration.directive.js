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

      vm.service = AuthService;
      vm.isRegistered = false;
      vm.permissions = vm.service.getPermissions();

      vm.registerFields = [
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
        },
        {
          key: 'user.name',
          type: 'input',
          templateOptions: {
            type: 'text',
            label: 'Your name',
            placeholder: 'Name'
          }
        },
        {
          key: 'user.age',
          type: 'input',
          templateOptions: {
            type: 'number',
            label: 'Your Age',
            placeholder: 'Age'
          }
        },
        {
          key: 'user.isViewer',
          type: 'checkbox',
          templateOptions: {
            type: 'checkbox',
            label: 'Watch Games'
          }
        },
        {
          key: 'user.isPlayer',
          type: 'checkbox',
          templateOptions: {
            type: 'checkbox',
            label: 'Play Games'
          }
        },
        {
          key: 'user.isManager',
          type: 'checkbox',
          templateOptions: {
            type: 'checkbox',
            label: 'Manage Teams'
          }
        },
        {
          key: 'user.isSupervisor',
          type: 'checkbox',
          templateOptions: {
            type: 'checkbox',
            label: 'Manage Events'
          }
        }
      ];

      vm.register = function(){
        AuthService.isLogged().then(function(data){
          if (data != true){
            $log.log(vm.regModel);
            vm.service.register(vm.regModel).then(function(response){
              if (response.data.error == null){
                $log.log(response.data);
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
