(function() {
    'use strict';

    angular
        .module('tfgJHipsterApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('fitosanitario-my-suffix', {
            parent: 'entity',
            url: '/fitosanitario-my-suffix?page&sort&search',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'Fitosanitarios'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/fitosanitario/fitosanitariosmySuffix.html',
                    controller: 'FitosanitarioMySuffixController',
                    controllerAs: 'vm'
                }
            },
            params: {
                page: {
                    value: '1',
                    squash: true
                },
                sort: {
                    value: 'id,asc',
                    squash: true
                },
                search: null
            },
            resolve: {
                pagingParams: ['$stateParams', 'PaginationUtil', function ($stateParams, PaginationUtil) {
                    return {
                        page: PaginationUtil.parsePage($stateParams.page),
                        sort: $stateParams.sort,
                        predicate: PaginationUtil.parsePredicate($stateParams.sort),
                        ascending: PaginationUtil.parseAscending($stateParams.sort),
                        search: $stateParams.search
                    };
                }],
            }
        })
        .state('fitosanitario-my-suffix-detail', {
            parent: 'fitosanitario-my-suffix',
            url: '/fitosanitario-my-suffix/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'Fitosanitario'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/fitosanitario/fitosanitario-my-suffix-detail.html',
                    controller: 'FitosanitarioMySuffixDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                entity: ['$stateParams', 'Fitosanitario', function($stateParams, Fitosanitario) {
                    return Fitosanitario.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'fitosanitario-my-suffix',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('fitosanitario-my-suffix-detail.edit', {
            parent: 'fitosanitario-my-suffix-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/fitosanitario/fitosanitario-my-suffix-dialog.html',
                    controller: 'FitosanitarioMySuffixDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Fitosanitario', function(Fitosanitario) {
                            return Fitosanitario.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('fitosanitario-my-suffix.new', {
            parent: 'fitosanitario-my-suffix',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/fitosanitario/fitosanitario-my-suffix-dialog.html',
                    controller: 'FitosanitarioMySuffixDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                numregistro: null,
                                nombrecomercial: null,
                                titular: null,
                                formulado: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('fitosanitario-my-suffix', null, { reload: 'fitosanitario-my-suffix' });
                }, function() {
                    $state.go('fitosanitario-my-suffix');
                });
            }]
        })
        .state('fitosanitario-my-suffix.edit', {
            parent: 'fitosanitario-my-suffix',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/fitosanitario/fitosanitario-my-suffix-dialog.html',
                    controller: 'FitosanitarioMySuffixDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Fitosanitario', function(Fitosanitario) {
                            return Fitosanitario.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('fitosanitario-my-suffix', null, { reload: 'fitosanitario-my-suffix' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('fitosanitario-my-suffix.delete', {
            parent: 'fitosanitario-my-suffix',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/fitosanitario/fitosanitario-my-suffix-delete-dialog.html',
                    controller: 'FitosanitarioMySuffixDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Fitosanitario', function(Fitosanitario) {
                            return Fitosanitario.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('fitosanitario-my-suffix', null, { reload: 'fitosanitario-my-suffix' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
