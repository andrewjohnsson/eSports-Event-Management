(function() {
  'use strict';

  angular
    .module('web')
    .controller('ModalDeleteController', ModalDeleteController);

  /** @ngInject */
  function ModalDeleteController($scope, $uibModal, ApiService) {
    var vm = this;

    vm.uibModal = $uibModal;

    $scope.cancel = function () {
      $uibModalInstance.dismiss('cancel');
    };

    $scope.accept = function(){
      $uibModalInstance.dismiss('accept');
    };

    vm.open = function(user){
      var user = user;
      vm.uibModal = $uibModal.open({
        animation: true,
        templateUrl: 'app/components/modal/delete/delete.html',
        controller: 'ModalController',
        size: 'sm',
        windowClass: 'aligned-modal'
      });

      vm.uibModal.result.then(function(){},function(result){
        if(result == 'accept'){
          ApiService.deleteUser(user);
        }
      })
    }
  }

})();
