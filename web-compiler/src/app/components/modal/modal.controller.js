(function() {
  'use strict';

  angular
    .module('web')
    .controller('ModalController', ModalController);

  /** @ngInject */
  function ModalController($uibModalInstance) {
    var vm = this;
    vm.cancel = function() {
      $uibModalInstance.dismiss('cancel');
    };
    vm.accept = function(){
      $uibModalInstance.dismiss('accept');
    }
  }

})();
