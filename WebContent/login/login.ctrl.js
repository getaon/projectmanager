	angular.module('myApp').controller("LoginCtrl", function($scope, $http){
	
		$(".menue").hide();
		$(".divdiv").hide();
		$(".div1").hide();
		$(".div2").hide();
		
		$scope.ShowPassword = function() {
		    var x = document.getElementById("myInput");
		    if (x.type === "password") {
		        x.type = "text";
		    } else {
		        x.type = "password";
		    }
		}
		
	$scope.func = function(){
		   $http.get("/projectmanager/rest/UsersService/getUserByName?username="
				   +$scope.username+"&password="+$scope.password)
			      .then(function(response, $location) {
    	     var selection = response.data;
    		console.log(selection);
    		userId = selection.id;
    	    usertype = selection.type;
    	    console.log(userId);
	    	
	    	if(selection.username == "undefined"){	
				alert("pleas enter user name and password");
				
			}else{
				if(selection != null){
    				if(selection.type == "employee" ){
    					localStorage.setItem("employeeid",userId);
							$(".employeemenu").show();
							$(".menubuttons").show();
							$("#logo").show();
							 window.location = 'http://localhost/projectmanager/myproject.html#!/EHR';
			        }else
			        if(selection.type == "customer"){
    					localStorage.setItem("customerid",userId);

						$(".customermenu").show();
						$(".menubuttons").show();
						$("#logo").show();
						 window.location = 'http://localhost/projectmanager/myproject.html#!/ActivePC';
		       		 }else
		       		 if(selection.type == "manager"){
	    				localStorage.setItem("managerid",userId);

							$(".managermenue").show();
							$(".menubuttons").show();
							$("#logo").show();
						 window.location = 'http://localhost/projectmanager/myproject.html#!/AC';
					}else{
						alert("user name and password incorrect");
						
						$("#forgetpassword").show();
			 		}
			 	}
			}		
		});	
	};
	    	
	    $scope.forgetpassword = function(){	
	    	$("#findUser").dialog();
	    
	    	$scope.userRequst = function(){
		    	$http.get("/projectmanager/rest/UsersService/forgetPassword?username="
		    			+$scope.forgotUser)
		    			.then(function(response){
		    				$scope.sendmail = response.data;
		    				console.log($scope.sendmail);
		    				
		    				if($scope.sendmail.id == 0){
		    					alert("you pressed on foget password..mail"
						    			 +" with your password and username sended! ");
						    	console.log("forgetpassword");
		    				}else if($scope.sendmail == null){
		    					alert("no such user name ");
		    				}
		    				
    				$("#forgotUser").val("");
    				$("#findUser").dialog( "destroy" );
	    				
    			});
	    		
	    	};
	    };	
	});  