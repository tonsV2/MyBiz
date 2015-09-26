var app = angular.module("myApp", ['ngResource']);

app.controller('Expense', function($http, $scope) {
	var source = 'api/expense/1';
	$http.get(source).
		success(function(data) {
			$scope.data = data;
		});
});
