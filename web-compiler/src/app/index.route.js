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
        templateUrl: 'app/views/main/main.html',
        controller: 'MainController',
        controllerAs: 'main'
      })
      .state('dashboard', {
        url: '/dashboard',
        templateUrl: 'app/views/dashboard/dashboard.html',
        controller: 'DashboardController',
        controllerAs: 'ctrl',
        resolve: {isLogged: function(AuthService, $location){AuthService.isLogged().then(function(data){if (data != true){$location.url('/404')}},function(){$location.url('/404')})}}
      })
      .state('user', {
        abstract: true,
        url: '/user',
        templateUrl: 'app/views/user/user.html',
        controller: 'UserController',
        controllerAs: 'ctrl'
      })
      .state('user.list', {
        url: '/list',
        templateUrl: 'app/views/user/list/list.html'
      })
      .state('user.details', {
        url: '/:id',
        templateUrl: 'app/views/user/details/details.html',
        controller: 'UserDetailsController',
        controllerAs: 'uDetCtrl'
      })
      .state('team', {
        abstract: true,
        url: '/team',
        templateUrl: 'app/views/team/team.html',
        controller: 'TeamController',
        controllerAs: 'ctrl'
      })
      .state('team.list', {
        url: '/list',
        templateUrl: 'app/views/team/list/list.html'
      })
      .state('team.details', {
        url: '/:id',
        templateUrl: 'app/views/team/details/details.html',
        controller: 'TeamDetailsController',
        controllerAs: 'tDetCtrl'
      })
      .state('event', {
        abstract: true,
        url: '/event',
        templateUrl: 'app/views/event/event.html',
        controller: 'EventController',
        controllerAs: 'ctrl'
      })
      .state('event.list', {
        url: '/list',
        templateUrl: 'app/views/event/list/list.html'
      })
      .state('event.details', {
        url: '/:id',
        templateUrl: 'app/views/event/details/details.html',
        controller: 'EventDetailsController',
        controllerAs: 'eDetCtrl'
      })
      .state('about', {
        url: '/about',
        templateUrl: 'app/views/about/about.html'
      })
      .state('404', {
        url: '/404',
        templateUrl: 'app/views/404/main.html'
      });

    $urlRouterProvider.otherwise('/');
  }

})();
