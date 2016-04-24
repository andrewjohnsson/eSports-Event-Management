(function() {
  'use strict';

  angular
    .module('web')
    .directive('dashboard', dashboard);

  /** @ngInject */
  function dashboard($http) {
    var directive = {
      restrict: 'E',
      templateUrl: 'app/components/dashboard/dashboard.html',
      controller: DashboardController,
      controllerAs: 'ctrl',
      bindToController: true
    };

    /** @ngInject */
    function DashboardController(UserService, TeamService, EventService) {
      var vm = this;

      vm.userslist = [];
      vm.teamsList = [];
      vm.eventsList = [];
      vm.participations = [];
      vm.participants = [];

      vm.list = function() {
        vm.userslist = UserService.get()
		vm.teamsList = TeamService.getTeams()
		vm.participations = TeamService.getParticipations()
        vm.eventsList = EventService.getEvents()
        vm.participants = EventService.getParticipants()
      }

      vm.list();

      vm.add = function(type, item){
	  	switch (type){
	  		case 'user':
	  			UserService.add(type, item)
	  			break;
	  		case 'team':
	  			TeamService.add(type, item)
	  			break;
	  		case 'event':
	  			EventService.add(type, item)
	  			break;
	  	}
	  	vm.list()
      }

      vm.search = function(type, item){
	  	switch (type){
	  		case 'user':
	  			vm.userslist = UserService.search(type, item)
	  			break;
	  		case 'team':
	  			vm.teamsList = TeamService.search(type, item)
	  			break;
	  		case 'event':
	  			vm.eventsList = EventService.search(type, item)
	  			break;
	  	}
      }

      vm.remove = function(type, id){
	  	switch (type){
	  		case 'user':
	  			UserService.remove(id)
	  			break;
	  		case 'team':
	  			TeamService.remove(id)
	  			break;
	  		case 'event':
	  			EventService.remove(id)
	  			break;
	  	}
	  	vm.list();
      }
    }
    return directive;
  }

})();
