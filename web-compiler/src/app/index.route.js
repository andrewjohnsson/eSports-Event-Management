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
      .state('cpanel', {
        url: '/cpanel',
        templateUrl: 'app/dashboard/dashboard.html',
        controller: 'DashboardController',
        controllerAs: 'ctrl'
      })
      .state('user', {
        url: '/user',
        templateUrl: 'app/user/user.html',
        controller: 'UsersController',
        controllerAs: 'users'
      })
      .state('team', {
        url: '/team',
        templateUrl: 'app/team/team.html',
        controller: 'TeamsController',
        controllerAs: 'teams'
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
