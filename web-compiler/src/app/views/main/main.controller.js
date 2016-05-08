(function() {
  'use strict';

  angular
    .module('web')
    .controller('MainController', MainController);

  /** @ngInject */
  function MainController() {
    var vm = this;
    vm.title = 'This is SPP Lab';
    vm.subtitle = 'Made by Andrey Ivanov, Andrey Litvinchuk and Alexey Shaveiko';
  }

})();
