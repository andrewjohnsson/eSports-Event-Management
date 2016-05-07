(function() {
  'use strict';

  angular
    .module('web')
    .controller('ModalController', ModalController);

  /** @ngInject */
  function ModalController($scope, $uibModalInstance) {
    $scope.cancel = function() {
      $uibModalInstance.dismiss('cancel');
    };
    $scope.accept = function(){
      $uibModalInstance.dismiss('accept');
    }
  }

})();
