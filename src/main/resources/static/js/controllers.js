(function(angular) {
	'use strict';
	var app = angular.module('myApp.controllers', []);

//	app.controller('DashboardController', function($scope, ExpenseTotal) {
	app.controller('DashboardController', function($scope, $http) {
		$scope.options =  (function range(begin, length, step) {
			var callback = function (_, i) {
				return begin + step * i;
			};
			return Array.apply(null, new Array(length)).map(callback);
		})(new Date().getFullYear(), 50, -1);

		/*
		function range(begin, length, step) {
			var callback = function (_, i) {
				return begin + step * i;
			};
			return Array.apply(null, new Array(length)).map(callback);
		}
		$scope.options = range(new Date().getFullYear(), 50, -1);
*/
		function getQuarter(today) {
			today = today || new Date();
			return Math.floor((today.getMonth() + 3) / 3);
		}

		$scope.setCurrentQuarter = function() {
			var quarter = getQuarter();
			console.log("Quarter: %s", quarter);
			$scope.selectedQuarter = quarter - 1;
			return quarter;
		};

		$scope.updateTotalExpenses = function() {
//			$scope.totalExpenses = ExpenseTotal.get({ q: $scope.selectedQuarter });
			$scope.selectedYear = $scope.selectedYear || new Date().getFullYear();
			$scope.selectedQuarter = $scope.selectedQuarter || getQuarter();
			var url = 'api/expense/year/' + $scope.selectedYear + '/quarter/' + $scope.selectedQuarter;
			console.log("url: %s", url);
			$http.get(url).success(function(result) {
				console.log("result: %s", result);
				$scope.totalExpenses = result;
			});
		}
	});

	app.controller('IncomeController', function($scope, $state, $window, Expense) {
	});

	app.controller('ExpenseListController', function($scope, $state, $window, Expense) {
		$scope.expenses = Expense.query(); //fetch all expenses. Issues a GET to /api/expenses

		$scope.deleteExpense = function(expense) { // Delete a expense. Issues a DELETE to /api/expenses/:id
			if ($window.confirm("Really delete '" + expense.name + "'?")) {
				expense.$delete(function() {
					$window.location.href = ''; //redirect to home
				});
			}
		};
	});

	app.controller('ExpenseViewController', function($scope, $stateParams, Expense) {
		$scope.expense = Expense.get({ id: $stateParams.id }); //Get a single expense.Issues a GET to /api/expenses/:id
	});

	app.controller('ExpenseCreateController', function($scope, $state, $stateParams, Expense) {
		$scope.expense = new Expense();	//create new expense instance. Properties will be set via ng-model on UI

		$scope.addExpense = function() { //create a new expense. Issues a POST to /api/expenses
			$scope.expense.$save(function() {
				$state.go('expenses'); // on success go back to home i.e. expenses state.
			});
		};
	});

	app.controller('ExpenseEditController', function($scope, $state, $stateParams, Expense) {
		$scope.updateExpense = function() { //Update the edited expense. Issues a PUT to /api/expenses/:id
			$scope.expense.$update(function() {
				$state.go('expenses'); // on success go back to home i.e. expenses state.
			});
		};

		$scope.loadExpense = function() { //Issues a GET request to /api/expenses/:id to get a expense to update
			$scope.expense = Expense.get({ id: $stateParams.id }, function() {
				// JSON doesn't support date types so we have to manually convert our string to a date object.
				$scope.expense.date = new Date($scope.expense.date);
			});
		};

		$scope.loadExpense(); // Load a expense which can be edited on UI
	});

})(angular);
