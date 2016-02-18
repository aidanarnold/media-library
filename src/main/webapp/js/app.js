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
    .factory('Book', function ($resource) {
        return $resource('/books/:id', {id: '@id'}, {
            update: {
                method: 'PUT' // this method issues a PUT request
            }
        });
    })
    .service('BookService', function ($log, $window, Book) {
        return {
            getAll: function () {
                return Book.query();
            },
            getBook: function (bookId) {
                return Book.get({id: bookId});
            },
            addBook: function (b) {
                console.log("add book");
                var book = new Book();
                book.title = b.title;
                book.author = b.author;
                book.read = b.read;
                book.$save(function () {
                    $window.location.reload();
                    //toast
                });
            },
            updateBook: function (b) {
                console.log("update book");
                var book = new Book();
                book.id = b.id;
                book.title = b.title;
                book.author = b.author;
                book.read = b.read;
                book.$update({id: b.id}, function () {
                    $window.location.reload();
                });
            },
            deleteBook: function (bookId) {
                console.log("delete book");
                bootbox.dialog({
                    size: 'small',
                    message: "Are you sure?",
                    buttons: {
                        no: {
                            label: "Nevermind",
                            className: "btn-default",
                            callback: function () {
                                console.log("Nevermind");
                            }
                        },
                        yes: {
                            label: "Delete It Forever!",
                            className: "btn-danger",
                            callback: function () {
                                Book.delete({id: bookId}, function () {
                                    $window.location.reload();
                                });
                            }
                        }
                    }
                });
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

        $scope.addBook = function (b) {
            BookService.addBook(b);
        }

        $scope.updateBook = function (b) {
            BookService.updateBook(b);
        }

        $scope.deleteBook = function (bookId) {
            BookService.deleteBook(bookId);
        }
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