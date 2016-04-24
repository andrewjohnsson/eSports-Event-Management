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
    function LoginController(HttpService, ParserService, md5) {
      var vm = this;

      vm.login = function(user){
        user.password = md5.createHash(md5.createHash(user.password)+'somesalt');
        vm.params = ParserService.parseParams($.param(user), 'user');
        HttpService.getData({method: 'POST', url: 'login?'+ vm.params}).then(function(data){
          alert(data);
        });
      }
    }

    return directive;
  }

})();
