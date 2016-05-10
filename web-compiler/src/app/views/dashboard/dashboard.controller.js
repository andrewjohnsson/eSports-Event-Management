(function() {
  'use strict';

  angular
    .module('web')
    .controller('DashboardController', DashboardController);

  /** @ngInject */
  function DashboardController(ApiService, $rootScope) {
    var vm = this;

    vm.playerForm = {};
    vm.teamForm = {};
    vm.eventForm = {};

    if (!$rootScope.apiService){
      $rootScope.apiService = ApiService;
    }

    vm.service = $rootScope.apiService;
    vm.service.updateData();

    vm.userFields = [
      {
        key: 'user.email',
        type: 'input',
        templateOptions: {
          type: 'email',
          placeholder: 'Email'
        }
      },
      {
        key: 'user.password',
        type: 'input',
        templateOptions: {
          type: 'password',
          placeholder: 'Password'
        }
      },
      {
        key: 'user.name',
        type: 'input',
        templateOptions: {
          type: 'text',
          placeholder: 'Name'
        }
      },
      {
        key: 'user.age',
        type: 'input',
        templateOptions: {
          type: 'number',
          placeholder: 'Age'
        }
      },
      {
        key: 'user.isViewer',
        type: 'checkbox',
        templateOptions: {
          type: 'checkbox',
          label: 'Viewer'
        }
      },
      {
        key: 'user.isPlayer',
        type: 'checkbox',
        templateOptions: {
          type: 'checkbox',
          label: 'Player'
        }
      },
      {
        key: 'user.isManager',
        type: 'checkbox',
        templateOptions: {
          type: 'checkbox',
          label: 'Supervisor'
        }
      },
      {
        key: 'user.isSupervisor',
        type: 'checkbox',
        templateOptions: {
          type: 'checkbox',
          label: 'Manager'
        }
      }
    ];

    vm.teamFields = [
      {
        key: 'team.name',
        type: 'input',
        templateOptions: {
          type: 'text',
          placeholder: 'Name'
        }
      }
    ];

    vm.eventFields = [
      {
        key: 'event.name',
        type: 'input',
        templateOptions: {
          type: 'text',
          placeholder: 'Name'
        }
      },
      {
        key: 'event.date',
        type: 'input',
        templateOptions: {
          type: 'text',
          placeholder: 'Date'
        }
      },
      {
        key: 'tickets',
        type: 'input',
        templateOptions: {
          type: 'number',
          placeholder: 'Tickets Amount'
        }
      }
    ];
  }

})();
