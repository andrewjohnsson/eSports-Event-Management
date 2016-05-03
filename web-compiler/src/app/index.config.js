(function() {
  'use strict';

  angular
    .module('web')
    .config(config);

  /** @ngInject */
  function config($logProvider, blockUIConfig) {
    // Enable log
    $logProvider.debugEnabled(true);
    blockUIConfig.autoBlock = false;
  }

})();
