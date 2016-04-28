(function() {
  'use strict';

  angular
    .module('web')
    .service('ParserService', ParserService);

  /** @ngInject */
  function ParserService($q) {
    /** @ngInject */

    var vm = this;

    vm.parseParams = function(array, type){
      var params = '';
      array.toString().split('&').forEach(function(parameter){
        params = params + type + '.' + parameter + '&';
      });
      return params = params.substring(0, params.length -1);
    }
  }

})();
