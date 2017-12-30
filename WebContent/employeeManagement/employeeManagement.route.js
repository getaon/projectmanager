
	angular.module('myApp').config(function($routeProvider) {
			$routeProvider
		
			.when("/EM", {
	        templateUrl : "employeeManagement/employeesmanagement.html",
	        controller: "EMCtrl"
	    
	     })
	 });