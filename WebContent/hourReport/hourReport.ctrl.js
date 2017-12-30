angular.module('myApp').controller("HRCtrl", function($scope, $http){

	$http.get("/projectmanager/rest/hourReport/getAllHourReportsManager")
		.then(function(response){
		        $scope.hourreports = response.data;
			console.log($scope.hourreports);
	});
	
	$http.get("/projectmanager/rest/employee/getAllEmployees")
	.then(function(response){
	        $scope.EHRemployees = response.data;
		console.log($scope.EHRemployees);
	});
	

	$http.get("/projectmanager/rest/project/getAllProjects")
	.then(function(response){
	        $scope.EHRprojects = response.data;
		console.log($scope.EHRprojects);
	});
	

	$http.get("/projectmanager/rest/customer/getAllCustomers")
	.then(function(response){
	        $scope.customers = response.data;
		console.log($scope.customers);
	});
	
		var today = new Date();
   		var yyyy = today.getFullYear();
	    var inpYear = $('#yearinput');
	   

		for (var i = 0; i < 5; i++) {
			$("#yearinput").append("<option value="+yyyy+">" + yyyy + "</option>");
		   yyyy--;
		}; 
		
		var month;
		for (var i = 1; i <= 12; i++) {
			if(i < 10){
				month = "0"+i;
				$("#monthinput").append("<option value="+month.charAt(1)+">" + month + "</option>");
			}else{
				month = i;
				$("#monthinput").append("<option value="+month+">" + month + "</option>");
			}
			
			
		   yyyy--;
		};    
		
		$scope.YearMonth = function(){
			if($scope.projectinput == undefined ){
				$scope.projectinput = 0;
			}
			
			if($scope.employeerinput == undefined ){
				$scope.employeerinput = 0;
			}
			if($scope.customerinput == undefined ){
				$scope.customerinput =0;
			}
		
				  if( $("#yearinput").val() != undefined 
					  && $("#monthinput").val() !=  undefined
					  && $scope.projectinput != undefined 
					  && $scope.employeerinput != undefined
					  && $scope.customerinput != undefined){
				
			
			$http.get("/projectmanager/rest/hourReport/getByYearMonthManager?"
					+"year="+$("#yearinput").val()
					+"&month="+$("#monthinput").val()
					+"&projectid="+$scope.projectinput
					+"&employeeid="+$scope.employeerinput
					+"&customerid="+$scope.customerinput)
			.then(function(response){
				$scope.hourreports = response.data;
					console.log($scope.hourreports);
					
					if($scope.hourreports.length > 0){

					}else{
						alert("No Result Found!")
					}
				});
			
			$scope.yearinput ="";
			$scope.monthinput ="";
			$scope.projectinput ="";
			$scope.employeerinput ="";
			$scope.customerinput ="";
		};
		
	}
	
	$scope.deleteRow = function(index){
		console.log($scope.hourreports[index].id);
		if(confirm("confirm delete please...")==true){

			$http.get("/projectmanager/rest/hourReport/deleteHourReport?id="
				+$scope.hourreports[index].id).then(function(response){
					$scope.RhourReportDB = response.data;
				console.log($scope.RhourReportDB);
				
				if($scope.RhourReportDB.id==0){
					$http.get("/projectmanager/rest/hourReport/getAllHourReportsManager")
					.then(function(response){
					        $scope.hourreports = response.data;
						console.log($scope.hourreports);
					});
					
				}else{
					alert("delete could not happend");
				}						
			});		
		};	
	};
	
	$scope.editRow = function(index){
		$("#hourreport").dialog();

		$scope.projectname = $scope.hourreports[index].project.projectname;
		$scope.projectid = $scope.hourreports[index].project.id;
		$scope.date = $scope.hourreports[index].date;
		$scope.starthour = $scope.hourreports[index].starthour; 
		$scope.endhour= $scope.hourreports[index].endhour; 
		$scope.description = $scope.hourreports[index].description;
		
		$scope.edit = function(){
				
				$http.get("/projectmanager/rest/hourReport/updateHourReport?"
						+"id="+$scope.hourreports[index].id
						+"&employee="+$scope.hourreports[index].employee.id
						+"&project="+$scope.projectid
						+"&date="+$scope.date
						+"&starthour="+$scope.starthour
						+"&endhour="+$scope.endhour
						+"&description="+$scope.description).then(function(response){
							$scope.hourreportUpdate = response.data;
							console.log($scope.hourreportUpdate);
								
					if($scope.hourreportUpdate.id == 0){
						
						$http.get("/projectmanager/rest/hourReport/getAllHourReportsManager")
						.then(function(response){
						        $scope.hourreports = response.data;
							console.log($scope.hourreports);
						});
						
					}else{
						alert("update went wrong!");
					}
					
						$scope.employee = "";
					$scope.projects = "";
					$scope.startdate = ""; 
					$scope.enddate = "";
					$scope.description = "";

				});	
				
				$("#hourreport").dialog("destroy");
		};
	};
	
	
});