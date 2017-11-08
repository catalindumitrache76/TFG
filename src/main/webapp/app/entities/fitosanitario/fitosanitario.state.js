(function() {
    'use strict';

    angular
        .module('tfgjHipsterApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('fitosanitario', {
            parent: 'entity',
            url: '/fitosanitario?page&sort&search',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'Fitosanitarios'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/fitosanitario/fitosanitarios.html',
                    controller: 'FitosanitarioController',
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
                }]
            }
        })
        .state('fitosanitario-detail', {
            parent: 'fitosanitario',
            url: '/fitosanitario/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'Fitosanitario'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/fitosanitario/fitosanitario-detail.html',
                    controller: 'FitosanitarioDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                entity: ['$stateParams', 'Fitosanitario', function($stateParams, Fitosanitario) {
                    return Fitosanitario.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'fitosanitario',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('fitosanitario-detail.edit', {
            parent: 'fitosanitario-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/fitosanitario/fitosanitario-dialog.html',
                    controller: 'FitosanitarioDialogController',
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
        .state('fitosanitario.new', {
            parent: 'fitosanitario',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/fitosanitario/fitosanitario-dialog.html',
                    controller: 'FitosanitarioDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                num_registro: null,
                                nombre_comercial: null,
                                titular: null,
                                formulado: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('fitosanitario', null, { reload: 'fitosanitario' });
                }, function() {
                    $state.go('fitosanitario');
                });
            }]
        })
        .state('fitosanitario.edit', {
            parent: 'fitosanitario',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/fitosanitario/fitosanitario-dialog.html',
                    controller: 'FitosanitarioDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Fitosanitario', function(Fitosanitario) {
                            return Fitosanitario.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('fitosanitario', null, { reload: 'fitosanitario' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('fitosanitario.delete', {
            parent: 'fitosanitario',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/fitosanitario/fitosanitario-delete-dialog.html',
                    controller: 'FitosanitarioDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Fitosanitario', function(Fitosanitario) {
                            return Fitosanitario.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('fitosanitario', null, { reload: 'fitosanitario' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
