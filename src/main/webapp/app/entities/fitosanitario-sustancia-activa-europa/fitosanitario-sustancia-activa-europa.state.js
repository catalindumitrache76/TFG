(function() {
    'use strict';

    angular
        .module('tfgjHipsterApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('fitosanitario-sustancia-activa-europa', {
            parent: 'entity',
            url: '/fitosanitario-sustancia-activa-europa?page&sort&search',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'Fitosanitario_sustancia_activa_europas'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/fitosanitario-sustancia-activa-europa/fitosanitario-sustancia-activa-europas.html',
                    controller: 'Fitosanitario_sustancia_activa_europaController',
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
        .state('fitosanitario-sustancia-activa-europa-detail', {
            parent: 'fitosanitario-sustancia-activa-europa',
            url: '/fitosanitario-sustancia-activa-europa/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'Fitosanitario_sustancia_activa_europa'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/fitosanitario-sustancia-activa-europa/fitosanitario-sustancia-activa-europa-detail.html',
                    controller: 'Fitosanitario_sustancia_activa_europaDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                entity: ['$stateParams', 'Fitosanitario_sustancia_activa_europa', function($stateParams, Fitosanitario_sustancia_activa_europa) {
                    return Fitosanitario_sustancia_activa_europa.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'fitosanitario-sustancia-activa-europa',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('fitosanitario-sustancia-activa-europa-detail.edit', {
            parent: 'fitosanitario-sustancia-activa-europa-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/fitosanitario-sustancia-activa-europa/fitosanitario-sustancia-activa-europa-dialog.html',
                    controller: 'Fitosanitario_sustancia_activa_europaDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Fitosanitario_sustancia_activa_europa', function(Fitosanitario_sustancia_activa_europa) {
                            return Fitosanitario_sustancia_activa_europa.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('fitosanitario-sustancia-activa-europa.new', {
            parent: 'fitosanitario-sustancia-activa-europa',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/fitosanitario-sustancia-activa-europa/fitosanitario-sustancia-activa-europa-dialog.html',
                    controller: 'Fitosanitario_sustancia_activa_europaDialogController',
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
                                activeSubstanceID: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('fitosanitario-sustancia-activa-europa', null, { reload: 'fitosanitario-sustancia-activa-europa' });
                }, function() {
                    $state.go('fitosanitario-sustancia-activa-europa');
                });
            }]
        })
        .state('fitosanitario-sustancia-activa-europa.edit', {
            parent: 'fitosanitario-sustancia-activa-europa',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/fitosanitario-sustancia-activa-europa/fitosanitario-sustancia-activa-europa-dialog.html',
                    controller: 'Fitosanitario_sustancia_activa_europaDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Fitosanitario_sustancia_activa_europa', function(Fitosanitario_sustancia_activa_europa) {
                            return Fitosanitario_sustancia_activa_europa.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('fitosanitario-sustancia-activa-europa', null, { reload: 'fitosanitario-sustancia-activa-europa' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('fitosanitario-sustancia-activa-europa.delete', {
            parent: 'fitosanitario-sustancia-activa-europa',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/fitosanitario-sustancia-activa-europa/fitosanitario-sustancia-activa-europa-delete-dialog.html',
                    controller: 'Fitosanitario_sustancia_activa_europaDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Fitosanitario_sustancia_activa_europa', function(Fitosanitario_sustancia_activa_europa) {
                            return Fitosanitario_sustancia_activa_europa.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('fitosanitario-sustancia-activa-europa', null, { reload: 'fitosanitario-sustancia-activa-europa' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
