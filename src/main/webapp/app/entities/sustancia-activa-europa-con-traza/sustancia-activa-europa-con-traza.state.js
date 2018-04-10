(function() {
    'use strict';

    angular
        .module('tfgjHipsterApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('sustancia-activa-europa-con-traza', {
            parent: 'entity',
            url: '/sustancia-activa-europa-con-traza?page&sort&search',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'Sustancia_activa_europa_con_trazas'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/sustancia-activa-europa-con-traza/sustancia-activa-europa-con-trazas.html',
                    controller: 'Sustancia_activa_europa_con_trazaController',
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
        .state('sustancia-activa-europa-con-traza-detail', {
            parent: 'sustancia-activa-europa-con-traza',
            url: '/sustancia-activa-europa-con-traza/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'Sustancia_activa_europa_con_traza'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/sustancia-activa-europa-con-traza/sustancia-activa-europa-con-traza-detail.html',
                    controller: 'Sustancia_activa_europa_con_trazaDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                entity: ['$stateParams', 'Sustancia_activa_europa_con_traza', function($stateParams, Sustancia_activa_europa_con_traza) {
                    return Sustancia_activa_europa_con_traza.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'sustancia-activa-europa-con-traza',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('sustancia-activa-europa-con-traza-detail.edit', {
            parent: 'sustancia-activa-europa-con-traza-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/sustancia-activa-europa-con-traza/sustancia-activa-europa-con-traza-dialog.html',
                    controller: 'Sustancia_activa_europa_con_trazaDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Sustancia_activa_europa_con_traza', function(Sustancia_activa_europa_con_traza) {
                            return Sustancia_activa_europa_con_traza.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('sustancia-activa-europa-con-traza.new', {
            parent: 'sustancia-activa-europa-con-traza',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/sustancia-activa-europa-con-traza/sustancia-activa-europa-con-traza-dialog.html',
                    controller: 'Sustancia_activa_europa_con_trazaDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                real_id: null,
                                tipo: null,
                                language: null,
                                name: null,
                                source: null,
                                date: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('sustancia-activa-europa-con-traza', null, { reload: 'sustancia-activa-europa-con-traza' });
                }, function() {
                    $state.go('sustancia-activa-europa-con-traza');
                });
            }]
        })
        .state('sustancia-activa-europa-con-traza.edit', {
            parent: 'sustancia-activa-europa-con-traza',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/sustancia-activa-europa-con-traza/sustancia-activa-europa-con-traza-dialog.html',
                    controller: 'Sustancia_activa_europa_con_trazaDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Sustancia_activa_europa_con_traza', function(Sustancia_activa_europa_con_traza) {
                            return Sustancia_activa_europa_con_traza.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('sustancia-activa-europa-con-traza', null, { reload: 'sustancia-activa-europa-con-traza' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('sustancia-activa-europa-con-traza.delete', {
            parent: 'sustancia-activa-europa-con-traza',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/sustancia-activa-europa-con-traza/sustancia-activa-europa-con-traza-delete-dialog.html',
                    controller: 'Sustancia_activa_europa_con_trazaDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Sustancia_activa_europa_con_traza', function(Sustancia_activa_europa_con_traza) {
                            return Sustancia_activa_europa_con_traza.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('sustancia-activa-europa-con-traza', null, { reload: 'sustancia-activa-europa-con-traza' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
