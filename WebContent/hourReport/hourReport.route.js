angular.module('myApp').config(function($routeProvider) {
	$routeProvider
	
	.when("/HR", {
        templateUrl : "hourReport/hourreport.html",
        controller: "HRCtrl"
     
     });
	
});