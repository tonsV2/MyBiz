(function(angular) {
	var app = angular.module('myApp.controllers', []);

	app.controller('DashboardController', function($scope, $state, $window, Expense) {
	});

	app.controller('IncomeController', function($scope, $state, $window, Expense) {
	});

	app.controller('ExpenseListController', function($scope, $state, $window, Expense) {
		$scope.expenses = Expense.query(); //fetch all expenses. Issues a GET to /api/expenses

		$scope.deleteExpense = function(expense) { // Delete a expense. Issues a DELETE to /api/expenses/:id
			if ($window.confirm('Really delete this?')) {
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
			$scope.expense = Expense.get({ id: $stateParams.id });
		};

		$scope.loadExpense(); // Load a expense which can be edited on UI
	});

})(angular);
