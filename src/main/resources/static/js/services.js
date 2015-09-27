(function(angular) {
	var app = angular.module('myApp.services', []);
	app.factory('Expense', function($resource) {
		return $resource('api/expense/:id', { id: '@id' }, {
			update: {
				method: 'PUT'
			}
		});
	});
})(angular);
