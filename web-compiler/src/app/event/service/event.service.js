(function() {
  'use strict';

  angular
    .module('web')
    .service('EventService', EventService);

  /** @ngInject */
  function EventService(HttpService, ParserService) {
    /** @ngInject */

    var vm = this;

    vm.events = [];
    vm.participants = [];

    vm.getEvents = function(){
	    if (vm.events != undefined){
			return vm.events;
	    }
    }

    vm.setEvents = function(events){
	    vm.events = events
    }

    vm.getParticipants = function(participants){
	    if (vm.participants != undefined){
			return vm.participants;
	    }
    }

    vm.setParticipants = function(participants){
	    vm.participants = participants
    }

    vm.get = function(){
	    var data = HttpService.getData({method: 'GET', url: 'list_events'})
	    if (data != undefined){
		    vm.setEvents(data.events)
			  vm.setParticipants(data.participants)
	    }else{
		    alert('Problems with getting the list of events')
	    }
    }

    vm.get()

    vm.add = function(team){
        vm.params = ParserService.parseParams($.param(team), 'event')
        return HttpService.getData({method: 'POST', url: 'create_event?'+ vm.params})
    }

    vm.search = function(team){
      vm.params = ParserService.parseParams($.param(team), 'event')
      return HttpService.getData({method: 'POST', url: 'read_event?' + vm.params}).users
    }

    vm.remove = function(type, id){
      HttpService.getData({method: 'POST', url: 'delete_event?id='+id})
      return true
    }
  }

})();
