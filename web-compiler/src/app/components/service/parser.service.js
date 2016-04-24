(function() {
  'use strict';

  angular
    .module('web')
    .service('ParserService', ParserService);

  /** @ngInject */
  function ParserService() {
    /** @ngInject */
    
    var vm = this;
    
    vm.paramArr = [];
    vm.params;

    vm.parseParams = function(array, type){
      vm.params = '';
      vm.paramArr = array.toString().split('&');
      vm.paramArr.forEach(function(parameter){
        vm.params = vm.params + type + '.' + parameter + '&';
      });
      return vm.params = vm.params.substring(0, vm.params.length -1);
    }
  }

})();