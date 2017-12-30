angular.module('myApp').config(function($routeProvider) {
	 $routeProvider
	
	 .when("/EHR", {
        templateUrl : "employee/employeesreport.html",
        controller: "EHRCtrl"
    
	})
	
});