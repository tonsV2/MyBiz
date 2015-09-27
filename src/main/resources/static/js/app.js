(function(angular) {

	var app = angular.module('myApp', ['ui.router', 'ngResource', 'myApp.controllers', 'myApp.services']);

	app.config(function($stateProvider) {
		$stateProvider.state('expenses', { // state for showing all expenses
			url: '/expenses',
			templateUrl: 'partials/expenses.html',
			controller: 'ExpenseListController'
		}).state('viewExpense', { //state for showing single expense
			url: '/expenses/:id/view',
			templateUrl: 'partials/expense-view.html',
			controller: 'ExpenseViewController'
		}).state('newExpense', { //state for adding a new expense
			url: '/expenses/new',
			templateUrl: 'partials/expense-add.html',
			controller: 'ExpenseCreateController'
		}).state('editExpense', { //state for updating a expense
			url: '/expenses/:id/edit',
			templateUrl: 'partials/expense-edit.html',
			controller: 'ExpenseEditController'
		});
	}).run(function($state) {
		$state.go('expenses'); //make a transition to expenses state when app starts
	});

})(angular);