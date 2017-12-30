	angular.module('myApp').config(function($routeProvider) {
	    $routeProvider
		   
	    	.when("/EP", {
		        templateUrl : "summary/manager.html",
		        controller  :"MCtrl"
		    
		     })
		     .when("/AC", {
		        templateUrl : "summary/activeCustomers.html",
		        controller  :"MCtrl"
		    
		     })
		     .when("/AP", {
		        templateUrl : "summary/activeProjects.html",
		        controller  :"MCtrl"
		    
		     })
	});		     
		     