(function() {
  'use strict';

  angular
    .module('web')
    .service('ApiService', ApiService);

  /** @ngInject */
  function ApiService(AuthService, PermissionService, UserService, TeamService, EventService, $location, $q) {
    /** @ngInject */
    var vm = this;
    vm.location = $location;
    vm.adminCount = 0;
    vm.authService = AuthService;

    vm.updateUsers = function(){
      var deferred = $q.defer();
      UserService.get().then(function(response){
        vm.usersList = response.users;
        vm.usersList.forEach(function(user){
          if (user.isAdmin){
            vm.adminCount++;
          }
        });
        deferred.resolve();
      }, function(){
        deferred.reject();
      });
      return deferred.promise;
    };

    vm.createUser = function(user){
      AuthService.isLogged().then(function(response){
        if (response){
          PermissionService.isAdmin().then(function(response){
            if (response.check){
              UserService.add(user).then(function(){
                vm.updateUsers();
              });
            }else{
              alert(response.info);
            }
          });
        }
      });
    };

    vm.updateUser = function(user){
      AuthService.isLogged().then(function(response) {
        if (response) {
          PermissionService.isAdmin().then(function (response) {
            if (response.check) {
              UserService.edit(user).then(function () {
                vm.updateUsers();
              });
            } else {
              alert(response.info)
            }
          })
        }
      });
    };

    vm.deleteUser = function(user){
      AuthService.isLogged().then(function(response){
        if (response){
          PermissionService.isAdmin().then(function(response){
            if (response.check){
              if (vm.getAdminCount() >= 2) {
                UserService.remove(user).then(function () {
                  vm.updateUsers();
                });
              } else {
                alert("System should have at least 1 admin.")
              }
            }else{
              alert(response.info);
            }
          })
        }
      });
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
      var deferred = $q.defer();
      TeamService.get().then(function(response){
        vm.teamsList = response.teams;
        vm.participations = response.participations;
        deferred.resolve();
      },function(){
        deferred.reject();
      });
      return deferred.promise;
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
      if(vm.authService.getUser().isAdmin || vm.authService.getUser().isSupervisor){
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
      if(vm.authService.getUser().isAdmin || vm.authService.getUser().isSupervisor){
        EventService.edit(event).then(function(){
          vm.updateEvent();
        });
      }else{
        alert("You should be admin or supervisor to edit events.")
      }
    };

    vm.updateEvents = function(){
      var deferred = $q.defer();
      EventService.get().then(function(response){
        vm.eventsList = response.events;
        vm.participants = response.participants;
        deferred.resolve();
      },function(){
        deferred.reject();
      });
      return deferred.promise;
    };

    vm.updateData = function() {
      AuthService.isLogged().then(function(response){
        if (response == true){
          vm.updateUsers();
          vm.updateTeams();
          vm.updateEvents();
        }
      });
    };

    vm.getAdminCount = function(){
      return vm.adminCount;
    };
  }

})();
