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
        controllerAs: 'ctrl',
        resolve: {isLogged: function(AuthService, $location){AuthService.isLogged().then(function(data){if (data != true){$location.url('/404')}},function(){$location.url('/404')})}}
      })
      .state('user', {
        url: '/user',
        templateUrl: 'app/user/user.html',
        controller: 'UserController',
        controllerAs: 'ctrl'
      })
      .state('team', {
        url: '/team',
        templateUrl: 'app/team/team.html',
        controller: 'TeamController',
        controllerAs: 'ctrl'
      })
      .state('event', {
        url: '/event',
        templateUrl: 'app/event/event.html',
        controller: 'EventController',
        controllerAs: 'ctrl'
      })
      .state('about', {
        url: '/about',
        templateUrl: 'app/about/about.html'
      })
      .state('404', {
        url: '/404',
        templateUrl: 'app/404/main.html'
      });

    $urlRouterProvider.otherwise('/');
  }

})();
