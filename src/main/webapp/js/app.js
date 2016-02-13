angular
    .module('mediaApp', ['ngRoute', 'ngResource'])
    .config(function ($routeProvider) {
        $routeProvider
            .when('/', {
                templateUrl: 'templates/home.html',
                controller: 'homeController',
                activeTab: 'home'
            })
            .when('/books', {
                templateUrl: 'templates/books.html',
                controller: 'bookController',
                activeTab: 'books'
            })
            .when('/games', {
                templateUrl: 'templates/games.html',
                controller: 'gameController',
                activeTab: 'games'
            })
            .when('/movies', {
                templateUrl: 'templates/movies.html',
                controller: 'moviesController',
                activeTab: 'movies'
            })
    })
    .service('BookService', function ($log, $resource) {
        return {
            getAll: function () {
                var bookResource = $resource('books/list', {}, {
                    query: {method: 'GET', params: {}, isArray: true}
                });
                return bookResource.query();
            }
        }
    })
    .service('GameService', function ($log, $resource) {
        return {
            getAll: function () {
                var gameResource = $resource('games/list', {}, {
                    query: {method: 'GET', params: {}, isArray: true}
                });
                return gameResource.query();
            }
        }
    })
    .service('MovieService', function ($log, $resource) {
        return {
            getAll: function () {
                var movieResource = $resource('movies/list', {}, {
                    query: {method: 'GET', params: {}, isArray: true}
                });
                return movieResource.query();
            }
        }
    })
    .controller('homeController', function ($scope, $route) {
        $scope.$route = $route;
    })
    .controller('bookController', function ($scope, $log, BookService) {
        $scope.books = BookService.getAll();
    })
    .controller('gameController', function ($scope, $log, GameService) {
        $scope.games = GameService.getAll();
    })
    .controller('moviesController', function ($scope, $log, MovieService) {
        $scope.movies = MovieService.getAll();
    })
    .filter('yesNo', function () {
        return function (input) {
            return input ? 'Yes' : 'No';
        }
    });