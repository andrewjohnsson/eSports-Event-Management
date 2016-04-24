(function() {
  'use strict';

  angular
    .module('web')
    .service('TeamService', TeamService);

  /** @ngInject */
  function TeamService(HttpService, ParserService) {
    /** @ngInject */

    var vm = this;
    vm.teams = [];
    vm.participations = [];

    vm.getTeams = function(){
	    if (vm.teams != undefined){
		    return vm.teams;
	    }
    }

    vm.setTeams = function(teams){
	    vm.teams = teams
    }

    vm.getParticipations = function(participations){
	    if (vm.participations != undefined){
		    return vm.participations;
	    }
    }

    vm.setParticipations = function(participations){
	    vm.participations = participations
    }

    vm.get = function(){
	    var data = HttpService.getData({method: 'GET', url: 'list_teams'})
	    if (data != undefined){
		    vm.setTeams(data.teams)
			  vm.setParticipations(data.participations)
	    }else{
		    alert('Problems with getting the list of teams')
	    }
    }

    vm.get()

    vm.add = function(team){
        vm.params = ParserService.parseParams($.param(team), 'team')
        return HttpService.getData({method: 'POST', url: 'create_team?'+ vm.params})
    }

    vm.search = function(team){
      vm.params = ParserService.parseParams($.param(team), 'team')
      return HttpService.getData({method: 'POST', url: 'read_team?' + vm.params}).users
    }

    vm.remove = function(type, id){
      HttpService.getData({method: 'POST', url: 'delete_team?id='+id})
      return true
    }

  }

})();
