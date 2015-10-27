
(function(angular) {
	'use strict';

	var chagePointApplication = angular.module('chargepointApplication', [])
			.controller('chargepointCtrl',
					[ '$scope', '$http', ChargePointController ]);

	function ChargePointController($scope, $http) {
		//$scope.endpoint = "http://localhost:88";
		//$scope.endpoint = "http://192.168.100.100:88";
		$scope.endpoint = "http://5.10.70.180:88";
		$scope.connectors = "1";
		$scope.logs = new Array();
		$scope.cps = new Array();
		$scope.refresh = true;
		$scope.chargepoint = null;

		// the log refresh automatically.
		window.onload = function startInterval()
		{
			var date = new Date();
 			$('#time').text( date.toLocaleDateString() + " " +date.toLocaleTimeString());
		    setInterval(function(){
		    	
		    	 if( $scope.refresh ){
		    		 var date = new Date();
		    			$('#time').text( date.toLocaleDateString() + " " +date.toLocaleTimeString());
		    		 $scope.getLogsAfter();
		    	 }
		    	
		    } ,2000);
		    
		};
		
		$scope.refreshStatus = function( ) {
			 return $scope.refresh?"Refreshing":"Stopped";
		}

		// get a list of charge points from db.
		$http.get($scope.endpoint + '/chargepoint').success(
				function(response) {
					console.log(response);
					$scope.cps = response;
				});

		// format date
		$scope.formatTime = function(value) {
			 var date = new Date(value);
			// date.setTime(value );
			return date.toLocaleDateString() + " " +date.toLocaleTimeString();
		}
		
 
		// when user click the edit button, going to edit, but not yet save.
		$scope.onEditClick = function(item) {
			$scope.chargepoint = item;
		}

		// when user click the new button, try to input the charge point
		// information
		$scope.onNewButtonClick = function() {
			$scope.chargepoint = {
				"version" : "OCPP15",
				"centralURL" : "http://willow:7080/ocppservice/",
				"connectors" : [ {
					"id" : 0,
					"status" : "Available"
				} ]
			};
		}
		
		// on click the retweet button, to switch between auto refresh or stop
		// refresh.
		$scope.onRetweetClick = function( ) {
			$scope.refresh = !$scope.refresh;
			if($scope.refresh) {
				$("#retweet").removeClass("glyphicon-retweet");
				$("#retweet").addClass("glyphicon-pushpin");
			}else{

				$("#retweet").removeClass("glyphicon-pushpin");
				$("#retweet").addClass("glyphicon-retweet");
			}
		}

		$scope.onHomeButtonClick = function() {
			$("#cps_container").removeClass("hidden");
			$("#log_container").addClass("hidden");
		};
		
		// when user click charge point item to see log, set current charge
		// point as global
		$scope.onLogButtonClick = function(obj) {
			$scope.chargepoint = obj;
			$scope.getLogs();
			$("#cps_container").addClass("hidden");
			$("#log_container").removeClass("hidden");
		};

		// on click the save button, save to DB.
		$scope.saveUpdate = function() {

			var insertFlag = $scope.chargepoint.id == null ? true : false;
			$http.post($scope.endpoint  + '/chargepoint', $scope.chargepoint)
					.success(function(data, status, headers, config) {
						if (insertFlag) {
							$scope.cps.push(data);
						}
					}).error(function(data, status, headers, config) {
						alert("failed, data: " + data + status);
					});

		};
		
		$scope.remove =function(obj) {
			$http.delete($scope.endpoint + '/chargepoint/'+ obj.id) 
			 .success(function(data, status, headers, config) {
				 var index = $scope.cps.indexOf(obj);
				    $scope.cps.splice(index, 1); 
			}).error(function(data, status, headers, config) {
				 console.log(data);
				 console.log(status);
				 console.log(headers);
				 console.log(config);
			});
		};

		$scope.getConnectors = function(count) {
			var num = Number(count);
			var connectors = new Array();
			for (var i = 0; i < num; i++) {
				connectors[i] = {
					id : i,
					status : "Available"
				};
			}
			$scope.chargepoint.connectors = connectors;
			// alert( JSON.stringify($scope.chargepoint.connectors));
		};

		// end of getConnectors
		
		

		// get logs from db for the specified charge point
		$scope.getLogs = function( ) {
			$http.get($scope.endpoint + '/audit/' + $scope.chargepoint.serial).success(
					function(response) {
						$scope.logs = response;
					});
		};
		
		// get logs from DB for the specified charge point after the given time
		$scope.getLogsAfter = function() {
			console.log($scope.logs.length);
			var time = $scope.logs.length > 0 ? $scope.logs[0].requestTime : 0;
			  
				// console.log($scope.logs[0].requestTime);
				// console.log($scope.chargepoint.serial);
				$http.get($scope.endpoint + '/audit/' + $scope.chargepoint.serial + "/" + time)
				.success(
					function(response) {
						for(var i in response) {
							$scope.logs.unshift(response[i]);
						}
						
						if($scope.logs.length > 50){
							// if the array size is larger than 50, then remove
							// 5 items after the 49th element.
							$scope.logs.splice(49, 5);
						}
						// console.log($scope.logs);
						console.log($scope.logs.length);
					});
			 
		};
		

		// clear logs of a certain charge point
		$scope.clearLogs = function(cp) {
			 
			$http.delete($scope.endpoint + '/audit/' + cp.serial) 
			 .success(function(data, status, headers, config) {
				 console.log(data);
				 $scope.logs = new Array();
			}).error(function(data, status, headers, config) {
				 console.log(data);
				 console.log(status);
				 console.log(headers);
				 console.log(config);
			});

		};
	}

})(window.angular);