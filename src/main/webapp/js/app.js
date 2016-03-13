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
                controller: 'movieController',
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
    .factory('Game', function ($resource) {
        return $resource('/games/:id', {id: '@id'}, {
            update: {
                method: 'PUT' // this method issues a PUT request
            }
        });
    })
    .factory('Movie', function ($resource) {
        return $resource('/movies/:id', {id: '@id'}, {
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
    .service('GameService', function ($log, Game) {
        var self = this;

        self.updateGames = function () {
            self.list = Game.query();
            return self.list;
        };

        self.list = self.updateGames();

        self.save = function (g) {
            var gameToSave = new Game();
            gameToSave.title = g.title;
            gameToSave.platform = g.platform;
            gameToSave.played = g.played;

            if (!g.id) {
                gameToSave.$save(function () {
                    $log.info("book added");
                    self.list = self.updateBooks();
                });
            }
            else {
                gameToSave.id = g.id;
                gameToSave.$update({id: g.id}, function () {
                    $log.info("game updated");
                    self.list = self.updateGames();
                });
            }
        };

        self.delete = function ($index, gameId) {
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
                            Game.delete({id: gameId}, function () {
                                self.list.splice($index, 1);
                            });
                        }
                    }
                }
            })
        }
    })
    .service('MovieService', function ($log, Movie) {
        var self = this;

        self.updateMovies = function () {
            self.list = Movie.query();
            return self.list;
        };

        self.list = self.updateMovies();

        self.save = function (m) {
            var movieToSave = new Movie();
            movieToSave.title = m.title;
            movieToSave.year = m.year
            movieToSave.watched = m.watched;

            if (!m.id) {
                movieToSave.$save(function () {
                    $log.info("movie added");
                    self.list = self.updateMovies();
                });
            }
            else {
                movieToSave.id = m.id;
                movieToSave.$update({id: m.id}, function () {
                    $log.info("movie updated");
                    self.list = self.updateMovies();
                });
            }
        };

        self.delete = function ($index, movieId) {
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
                            Movie.delete({id: movieId}, function () {
                                self.list.splice($index, 1);
                            });
                        }
                    }
                }
            })
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
                //$scope.books = BookService.updateBooks();
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
    .controller('gameController', function ($scope, $log, $modal, GameService) {
        $scope.gameService = GameService;

        $scope.openGameModal = function (game) {

            var modalInstance = $modal.open({
                templateUrl: 'gameModal.html',
                controller: 'gameModalController',
                game: game,
                resolve: {
                    game: function () {
                        return game;
                    }
                }
            });

            modalInstance.result.then(function () {
            });
        };

        $scope.deleteBook = function ($index, gameId) {
            GameService.delete($index, gameId);
        }
    })
    .controller('gameModalController', function ($scope, $log, $modalInstance, GameService, game) {

        $scope.game = angular.copy(game);

        $scope.save = function (g) {
            GameService.save(g);
            $modalInstance.close();
        }

        $scope.cancel = function () {
            $modalInstance.dismiss('cancel');
        };
    })
    .controller('movieController', function ($scope, $log, $modal, MovieService) {
        $scope.movieService = MovieService;

        $scope.openMovieModal = function (movie) {

            var modalInstance = $modal.open({
                templateUrl: 'movieModal.html',
                controller: 'movieModalController',
                movie: movie,
                resolve: {
                    movie: function () {
                        return movie;
                    }
                }
            });

            modalInstance.result.then(function () {
            });
        };

        $scope.deleteMovie = function ($index, movieId) {
            MovieService.delete($index, movieId);
        }
    })
    .controller('movieModalController', function ($scope, $log, $modalInstance, MovieService, movie) {

        $scope.movie = angular.copy(movie);

        $scope.save = function (m) {
            MovieService.save(m);
            $modalInstance.close();
        }

        $scope.cancel = function () {
            $modalInstance.dismiss('cancel');
        };
    })
    .filter('yesNo', function () {
        return function (input) {
            return input ? 'Yes' : 'No';
        }
    });