'use strict';

angular.module('myApp.view5', ['ngRoute'])

        .config(['$routeProvider', function ($routeProvider) {
                $routeProvider.when('/view5', {
                    templateUrl: 'app/view5/view5.html',
                    controller: 'View5Ctrl'
                });
            }])

        .controller('View5Ctrl', function ($http, $scope) {
            $scope.user = {
                username: '',
                password: ''
            };
            $scope.createUser = function () {
                $http.post('api/signUp', $scope.user);
            };
        });