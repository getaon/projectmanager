angular.module('myApp').controller("EHRCtrl", function($scope, $http){
	
	var x = localStorage.getItem("employeeid");
	console.log(x);
	
	 $http.get("/projectmanager/rest/EmployeeProjectService/getConnectedProject?userId="+userId)
	.then(function(response){
	        $scope.EHRprojects = response.data;
		console.log($scope.EHRprojects);
	}); 

	$http.get("/projectmanager/rest/employee/getAllEmployees")
	.then(function(response){
	        $scope.EHRemployees = response.data;
		console.log($scope.EHRemployees);
	});
	
	$http.get("http://localhost/projectmanager/rest/hourReport/getLastReports?userId="+x)
		.then(function(response){
		        $scope.report = response.data;
			console.log($scope.report);
	});
	
	
		$http.get("http://localhost/projectmanager/rest/Properties/getHourList")
		.then(function(response){
		        $scope.hours = response.data;
			console.log($scope.hours);
			
			var  a = $scope.hours.split(",");
			
			var hourbegin = a[0];
			var r = hourbegin.split("-");
			var h = parseInt(r[0]);
						
			var hourend = a[1];
			var t = hourend.split("-");
			var m = parseInt(t[0]);
				
				var sor;
			for (var i = h ; i <= m; i++) {
				sor=i+":00";
				
				$("#starttime").append("<option value="+sor+">"+sor+"</option>");
				$("#endtime").append("<option value="+sor+">"+sor+"</option>");	
			}	
		});
	
	$scope.addRow = function(){
	
		$scope.date = $.datepicker.formatDate('yy-mm-dd', $("#datepicker12").datepicker('getDate'));
		
		console.log($scope.date);
		
		$http.get("/projectmanager/rest/hourReport/createHourReport?employee="
				+userId+"&project="+$scope.projects
				+"&date="+$scope.date
				+"&satrthour="+$("#starttime").val()
				+"&endhour="+$("#endtime").val()
				+"&description="+$scope.description).then(function(response){
					$scope.createdHourReport = response.data;
					console.log($scope.createdHourReport);	
				
				if($scope.createdHourReport.id == 0){
					$http.get("/projectmanager/rest/hourReport/getLastReports?userId="+userId)
					.then(function(response){
					        $scope.report = response.data;
						console.log($scope.report);
					});
					alert("problem has found..");
					
				}else{
					alert("new HourReport added!");
					$("#addReport").dialog( "destroy" );
				}
		});
	};
	
	$scope.button = function(){
		$("#addReport").dialog();
	};

	
	 $http.get("http://localhost/projectmanager/rest/Properties/getdays")
	 .then(function(response) {
	 var days = response.data;
	 var daysArr = days.split(",");



	 $('#datepicker12').datepicker({
		 	beforeShowDay: function(date) {
	         var day = date.getDay();
	         var result = daysArr[day].includes("true"); 
	         return [result, '', ''];
	     }
	 });

	 
	 });
	 
	//-----------------CHANGE PASSWORD------------------ 
	 
	 
	 
 });

