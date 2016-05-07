(function() {
  'use strict';

  angular
    .module('web')
    .controller('DashboardController', DashboardController);

  /** @ngInject */
  function DashboardController(ApiService) {
    var vm = this;

    vm.playerForm = {};
    vm.teamForm = {};
    vm.eventForm = {};

    vm.service = ApiService;
    vm.service.updateData();

    vm.userFields = [
      {
        key: 'user.email',
        type: 'input',
        templateOptions: {
          type: 'email'
        }
      },
      {
        key: 'user.password',
        type: 'input',
        templateOptions: {
          type: 'password'
        }
      },
      {
        key: 'user.name',
        type: 'input',
        templateOptions: {
          type: 'text'
        }
      },
      {
        key: 'user.age',
        type: 'input',
        templateOptions: {
          type: 'number'
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
          type: 'text'
        }
      }
    ];

    vm.eventFields = [
      {
        key: 'event.name',
        type: 'input',
        templateOptions: {
          type: 'text'
        }
      },
      {
        key: 'event.date',
        type: 'input',
        templateOptions: {
          type: 'date'
        }
      }
    ];
  }

})();
