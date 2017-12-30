	angular.module('myApp').config(function($routeProvider) {
	    $routeProvider
	
		    .when("/PM", {
		        templateUrl : "projectManagement/projectsmanagement.html",
		        controller: "PMCtrl"
		    
		     })
		      .when("/PMAP", {
		        templateUrl : "projectManagement/activeProjects.html",
		        controller: "PMCtrl"
		    
		     })
		      .when("/PMPAE", {
		        templateUrl : "projectManagement/projectAndEmployee.html",
		        controller: "PMCtrl"
		    
		     })
	  });