(function() {
  'use strict';

  angular
    .module('web')
    .service('ApiService', ApiService);

  /** @ngInject */
  function ApiService(AuthService, UserService, TeamService, EventService) {
    /** @ngInject */
    var vm = this;

    vm.updateUsers = function(){
      UserService.get().then(function(response){
        vm.userslist = response.users;
      });
    };

    vm.createUser = function(user){
      if(AuthService.getUser().isAdmin){
        UserService.add(user).then(function(){
          vm.updateUsers();
        });
      }else{
        alert("You should be admin to create users.")
      }
    };

    vm.updateUser = function(user){
      if(AuthService.getUser().isAdmin){
        UserService.edit(user).then(function(){
          vm.updateUsers();
        });
      }else{
        alert("You should be admin to edit users.")
      }
    };

    vm.deleteUser = function(id){
      if(AuthService.getUser().isAdmin){
        if (UserService.remove(id)){
          vm.updateUsers();
        }else{
          alert("Error occured while deleting user!")
        }
      }else{
        alert("You should be admin to create users.")
      }
    };

    vm.createTeam = function(team){
      if(AuthService.getUser().isAdmin || AuthService.getUser().isManager){
        TeamService.add(team).then(function(){
          vm.updateTeams();
        });
      }else{
        alert("You should be admin or manager to create teams.")
      }
    };

    vm.deleteTeam = function(id){
      if(AuthService.getUser().isAdmin || AuthService.getUser().isManager){
        if (TeamService.remove(id)){
          vm.updateTeams();
        }else{
          alert("Error occured while deleting team!")
        }
      }else{
        alert("You should be admin or manager to delete teams.")
      }
    };

    vm.updateTeam = function(team){
      if(AuthService.getUser().isAdmin || AuthService.getUser().isManager){
        TeamService.edit(team).then(function(){
          vm.updateTeams();
        });
      }else{
        alert("You should be admin or manager to edit teams.")
      }
    };

    vm.updateTeams = function(){
      TeamService.get().then(function(response){
        vm.teamsList = response.teams;
        vm.participations = response.participations;
      });
    };

    vm.createEvent = function(event){
      if(AuthService.getUser().isAdmin || AuthService.getUser().isSupervisor){
        EventService.add(event).then(function(){
          vm.updateEvents();
        });
      }else{
        alert("You should be admin or supervisor to create events.")
      }
    };

    vm.deleteEvent = function(id){
      if(AuthService.getUser().isAdmin || AuthService.getUser().isSupervisor){
        if (EventService.remove(id)){
          vm.updateEvents();
        }else{
          alert("Error occured while deleting event!")
        }
      }else{
        alert("You should be admin or supervisor to delete events.")
      }
    };

    vm.updateEvent = function(event){
      if(AuthService.getUser().isAdmin || AuthService.getUser().isSupervisor){
        EventService.edit(event).then(function(){
          vm.updateEvent();
        });
      }else{
        alert("You should be admin or supervisor to edit events.")
      }
    };

    vm.updateEvents = function(){
      EventService.get().then(function(response){
        vm.eventsList = response.events;
        vm.participants = response.participants;
      });
    };

    vm.updateData = function() {
      vm.updateUsers();
      vm.updateTeams();
      vm.updateEvents();
    };
  }

})();
