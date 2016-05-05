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
        abstract: true,
        url: '/team',
        templateUrl: 'app/team/team.html',
        controller: 'TeamController',
        controllerAs: 'ctrl'
      })
      .state('team.list', {
        url: '/list',
        templateUrl: 'app/team/list/list.html'
      })
      .state('team.details', {
        url: '/:id',
        templateUrl: 'app/team/details/details.html',
        controller: function($scope, $stateParams){
          $scope.team = $scope.teamsList[$stateParams.id];
        }
      })
      .state('event', {
        abstract: true,
        url: '/event',
        templateUrl: 'app/event/event.html',
        controller: 'EventController',
        controllerAs: 'ctrl'
      })
      .state('event.list', {
        url: '/list',
        templateUrl: 'app/event/list/list.html'
      })
      .state('event.details', {
        url: '/:id',
        templateUrl: 'app/event/details/details.html',
        controller: function($scope, $stateParams){
          $scope.event = $scope.eventsList[$stateParams.id];
        }
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
