angular.module('myApp').controller("HRCECtrl", function($scope, $http){
	console.log(usertype);
	console.log(userId);
	
	
	 $http.get("/projectmanager/rest/hourReport/getReltiveProjects?"
			+"userId="+userId
			+"&usertype="+usertype)
	.then(function(response){
	        $scope.EHRprojects = response.data;
		console.log($scope.EHRprojects);
	});
	

	$http.get("/projectmanager/rest/hourReport/getAllHourReports?userId="+userId
			+"&usertype="+usertype)
		.then(function(response){
		        $scope.hourreports = response.data;
			console.log($scope.hourreports);
	});
	
	$scope.YearMonth = function(){
		
		if($scope.projects == undefined ){
			$scope.projects = 0;
		}

		$http.get("/projectmanager/rest/hourReport/getByYearMonth?"
				+"year="+$("#year").val()
				+"&month=" +  $("#month").val()
				+"&projectid="+ $scope.projects
				+"&userId="+userId
				+"&usertype="+usertype)
		.then(function(response){
		        $scope.hourreports = response.data;
			console.log($scope.hourreports);
			
			if( $scope.hourreports.length > 0){
				
			}else{
				alert("no Result found!");
			}
		});
	};
	
	var today = new Date();
		var yyyy = today.getFullYear();
    var inpYear = $('#yearinput');
   

	for (var i = 0; i < 5; i++) {
		$("#year").append("<option value="+yyyy+">" + yyyy + "</option>");
	   yyyy--;
	}; 
	
	var month;
	for (var i = 1; i <= 12; i++) {
		if(i < 10){
			month = "0"+i;
			$("#month").append("<option value="+month.charAt(1)+">" + month + "</option>");
		}else{
			month = i;
			$("#month").append("<option value="+month+">" + month + "</option>");
		}

		yyyy--;
	};    
	

});