(function(angular) {
	'use strict';
	var app = angular.module('myApp.services', []);

	app.factory('Expense', function($resource) {
		return $resource('api/expense/:id', { id: '@id' }, {
			update: {
				method: 'PUT'
			}
		});
	});

	app.factory('ExpenseTotal', function($resource) {
		return $resource('api/expense/quarter/:q', { q: '@q' });
	});

})(angular);
