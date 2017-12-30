angular.module('myApp').config(function($routeProvider) {
			$routeProvider
		
			.when("/HRCE", {
		        templateUrl : "hourReportCE/hourreportC.html",
		        controller: "HRCECtrl" 
			
			})
	 });