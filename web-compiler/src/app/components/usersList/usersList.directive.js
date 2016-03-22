(function() {
  'use strict';

  angular
    .module('web')
    .directive('usersList', usersList);

  /** @ngInject */
  function usersList($http) {
    var directive = {
      restrict: 'E',
      templateUrl: 'app/components/usersList/usersList.html',
      controller: UsersListController,
      controllerAs: 'usersList',
      bindToController: true
    };

    /** @ngInject */
    function UsersListController() {
      var vm = this;

      vm.list = [];
      vm.paramArr = [];
      vm.params;

      vm.parseParams = function(type){
        vm.params = '';
        vm.paramArr = vm.paramArr.toString().split('&');
        vm.paramArr.forEach(function(parameter){
          vm.params = vm.params + type + '.' + parameter + '&';
        });
        return vm.params = vm.params.substring(0, vm.params.length -1);
      }

      vm.listUsers = function () {
        $http({
          method: 'GET',
          url: 'list',
          headers: {'Content-Type': 'application/x-www-form-urlencoded'}
        }).success(function (data) {
          vm.list = data.users;
        });
      }

      vm.listUsers();

      vm.add = function(user){
        vm.paramArr = $.param(user);
        vm.parseParams('user');
        $http({
          method: 'POST',
          url: 'create?'+ vm.params,
          headers: {'Content-Type': 'application/x-www-form-urlencoded'}
        }).success(function(){
          vm.listUsers();
        });
      }

      vm.search = function(user){
        vm.paramArr = $.param(user);
        vm.parseParams('user');
        $http({
          method: 'POST',
          url: 'read?' + vm.params,
          headers: {'Content-Type': 'application/x-www-form-urlencoded'}
        }).success(function(data){
          vm.list = data.users;
        });
      }

      vm.remove = function(id){
        $http({
          method: 'POST',
          url: 'delete?id='+id,
          headers: {'Content-Type': 'application/x-www-form-urlencoded'}
        }).success(function(){
          vm.listUsers();
        });
      }
    }
    return directive;
  }

})();
