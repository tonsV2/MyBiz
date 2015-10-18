(function(angular) {

	var app = angular.module('myApp', ['ui.router', 'ngResource', 'myApp.controllers', 'myApp.services']);

	app.config(function($stateProvider) {
		$stateProvider.state('dashboard', {
			url: '/dashboard',
			templateUrl: 'partials/dashboard.html',
			controller: 'DashboardController'
		});

		$stateProvider.state('income', {
			url: '/income',
			templateUrl: 'partials/incomes.html',
			controller: 'IncomeController'
		});

		$stateProvider.state('expenses', { // state for showing all expenses
			url: '/expenses',
			templateUrl: 'partials/expense/expense-list.html',
			controller: 'ExpenseListController'
		}).state('viewExpense', { //state for showing single expense
			url: '/expenses/:id/view',
			templateUrl: 'partials/expense/expense-view.html',
			controller: 'ExpenseViewController'
		}).state('newExpense', { //state for adding a new expense
			url: '/expenses/new',
			templateUrl: 'partials/expense/expense-add.html',
			controller: 'ExpenseCreateController'
		}).state('editExpense', { //state for updating a expense
			url: '/expenses/:id/edit',
			templateUrl: 'partials/expense/expense-edit.html',
			controller: 'ExpenseEditController'
		});
	}).run(function($state) {
		$state.go('expenses'); //make a transition to expenses state when app starts
	});

})(angular);
