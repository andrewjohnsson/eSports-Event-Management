(function() {
  'use strict';

  angular
    .module('web')
    .config(routerConfig);

  /** @ngInject */
  function routerConfig($stateProvider, $urlRouterProvider) {
    $stateProvider
      .state('home', {
        url: '/',
        templateUrl: 'app/main/main.html',
        controller: 'MainController',
        controllerAs: 'main'
      })
      .state('user', {
        url: '/user',
        templateUrl: 'app/user/user.html',
        controller: 'UsersController',
        controllerAs: 'users'
      })
      .state('result', {
        url: '/result',
        templateUrl: '/result.jsp'
      })
      .state('events', {
        url: '/events',
        templateUrl: 'app/events/events.html',
        controller: 'EventController',
        controllerAs: 'events'
      })
      .state('about', {
        url: '/about',
        templateUrl: 'app/about/about.html'
      })

    $urlRouterProvider.otherwise('/');
  }

})();
