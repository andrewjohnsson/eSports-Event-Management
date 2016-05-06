(function() {
  'use strict';

  angular
    .module('web')
    .controller('ModalDeleteController', ModalDeleteController);

  /** @ngInject */
  function ModalDeleteController($scope, $uibModal, UserService) {
    var vm = this;

    vm.uibModal = $uibModal;

    $scope.cancel = function () {
      $uibModalInstance.dismiss('cancel');
    };

    $scope.accept = function(){
      UserService.remove(vm.id);
      $uibModalInstance.dismiss('accept');
    };

    vm.open = function(id){
      vm.id = id;
      vm.uibModal.open({
        animation: true,
        templateUrl: 'app/components/modal/delete/delete.html',
        controller: 'ModalController',
        size: 'sm',
        windowClass: 'aligned-modal'
      })
    }
  }

})();
