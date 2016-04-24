(function() {
  'use strict';

  angular
    .module('web')
    .controller('EventController', EventController);

  /** @ngInject */
  function EventController() {
    var vm = this;
    vm.title = 'Explore Events';
    vm.subtitle = 'and buy tickets';

    vm.items = [
      {
        id: 0,
        isSoldOut: false,
        isExpired: true,
        price: 5.99,
        name: 'Starladder XIV Finals',
        description: 'Lan Finals at the heart of Belarus.',
        images: {
          full: '',
          thumb: ''
        }
      },
      {
        id: 1,
        isSoldOut: false,
        isExpired: false,
        price: 19.95,
        name: 'ESL ESEA Pro League',
        description: 'Best gamers from the world.',
        images: {
          full: '',
          thumb: ''
        }
      }
    ];
  }

})();
