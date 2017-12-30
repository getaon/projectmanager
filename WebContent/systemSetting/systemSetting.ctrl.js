	angular.module('myApp').controller("system", function($scope, $http){

									var sor;
								for (var i = 6 ; i <= 23; i++) {
									sor=i+":00";
									
									$("#starttime").append("<option value="+sor+">"+sor+"</option>");
									$("#endtime").append("<option value="+sor+">"+sor+"</option>");	
								}
								console.log(sor);		

					$scope.retime = function(){
						$http.get("/projectmanager/rest/Properties/setHourList?beginHour="
								+$("#starttime").val()+"&endHour="+$("#endtime").val())
								.then(function(response){
									$scope.reply = response.data;
									console.log($scope.reply);
						});
					}
	
//	------------------------------------------------------------------------
			
				 $scope.SendDays = function (){
					 var sunday = $("#Sunday").is(":checked") ? "true" : "false";
					 var monday = $("#Monday").is(":checked") ? "true" : "false";
					 var tuesday = $("#Tuesday").is(":checked") ? "true" : "false";
					 var wednesday = $("#Wednesday").is(":checked") ? "true" : "false";
					 var thursday = $("#Thursday").is(":checked") ? "true" : "false";
					 var friday = $("#Friday").is(":checked") ? "true" : "false";
					 var saturday = $("#Saturday").is(":checked") ? "true" : "false";
						$http.get("http://localhost/projectmanager/rest/Properties/setdaysofWork?sunday="
								+sunday+
								"&monday="+monday+
								"&tuesday="+tuesday+
								"&wednesday="+wednesday+
								"&thursday="+thursday+
								"&friday="+friday+
								"&saturday="+saturday)
							.then(function(response) {
						    $scope.days = response.data;
						    if( $scope.days == 'OK'){
						    	alert("You set Days to report !!");
	
						   }
						});
				 }
	});


	