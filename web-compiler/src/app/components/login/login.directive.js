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
    function LoginController($http, md5) {
      var vm = this;

      vm.paramArr = [];
      vm.params;

      vm.parseParams = function(type){
        vm.params = '';
        vm.paramArr = vm.paramArr.toString().split('&');
        vm.paramArr.forEach(function(parameter){
          if (parameter.split('=')[0] == 'password'){
            parameter = parameter.split('=')[0]+'='+md5.createHash(parameter.split('=')[1] || '');
          }
          vm.params += type + '.' + parameter + '&';
        });
        return vm.params = vm.params.substring(0, vm.params.length -1);
      }

      vm.login = function(user){
        vm.paramArr = $.param(user);
        vm.parseParams('user');
        $http({
          method: 'POST',
          url: 'login?'+ vm.params,
          headers: {'Content-Type': 'application/x-www-form-urlencoded'}
        }).success(function(data){
          alert(data);
        });
      }
    }

    return directive;
  }

})();
