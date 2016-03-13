angular
    .module('mediaApp', ['ngRoute', 'ngResource', 'ui.bootstrap'])
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
    .service('BookService', function ($log, Book) {
        var self = this;

        self.updateBooks = function () {
            self.list = Book.query();
            return self.list;
        };

        self.list = self.updateBooks();

        self.save = function (b) {
            var bookToSave = new Book();
            bookToSave.title = b.title;
            bookToSave.author = b.author;
            bookToSave.read = b.read;

            if (!b.id) {
                bookToSave.$save(function () {
                    $log.info("book added");
                    self.list = self.updateBooks();
                });
            }
            else {
                bookToSave.id = b.id;
                bookToSave.$update({id: b.id}, function () {
                    $log.info("book updated");
                    self.list = self.updateBooks();
                });
            }
        };

        self.delete = function ($index, bookId) {
            bootbox.dialog({
                size: 'small',
                message: "Are you sure?",
                buttons: {
                    no: {
                        label: "Nevermind",
                        className: "btn-default",
                        callback: function () {
                            $log.debug("delete cancelled");
                            return false;
                        }
                    },
                    yes: {
                        label: "Delete It Forever!",
                        className: "btn-danger",
                        callback: function () {
                            Book.delete({id: bookId}, function () {
                                self.list.splice($index, 1);
                            });
                        }
                    }
                }
            })
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
    .controller('bookController', function ($scope, $log, $modal, BookService) {
        $scope.bookService = BookService;

        $scope.openBookModal = function (book) {

            var modalInstance = $modal.open({
                templateUrl: 'bookModal.html',
                controller: 'bookModalController',
                book: book,
                resolve: {
                    book: function () {
                        return book;
                    }
                }
            });

            modalInstance.result.then(function () {
                $scope.books = BookService.updateBooks();
            });
        };

        $scope.deleteBook = function ($index, bookId) {
            BookService.delete($index, bookId);
        }
    })
    .controller('bookModalController', function ($scope, $log, $modalInstance, BookService, book) {

        $scope.book = angular.copy(book);

        $scope.save = function (b) {
            BookService.save(b);
            $modalInstance.close();
        }

        $scope.cancel = function () {
            $modalInstance.dismiss('cancel');
        };
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