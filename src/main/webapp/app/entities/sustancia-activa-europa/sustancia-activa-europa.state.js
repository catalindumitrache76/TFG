(function() {
    'use strict';

    angular
        .module('tfgjHipsterApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('sustancia-activa-europa', {
            parent: 'entity',
            url: '/sustancia-activa-europa?page&sort&search',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'Sustancia_activa_europas'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/sustancia-activa-europa/sustancia-activa-europas.html',
                    controller: 'Sustancia_activa_europaController',
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
        .state('sustancia-activa-europa-detail', {
            parent: 'sustancia-activa-europa',
            url: '/sustancia-activa-europa/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'Sustancia_activa_europa'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/sustancia-activa-europa/sustancia-activa-europa-detail.html',
                    controller: 'Sustancia_activa_europaDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                entity: ['$stateParams', 'Sustancia_activa_europa', function($stateParams, Sustancia_activa_europa) {
                    return Sustancia_activa_europa.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'sustancia-activa-europa',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('sustancia-activa-europa-detail.edit', {
            parent: 'sustancia-activa-europa-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/sustancia-activa-europa/sustancia-activa-europa-dialog.html',
                    controller: 'Sustancia_activa_europaDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Sustancia_activa_europa', function(Sustancia_activa_europa) {
                            return Sustancia_activa_europa.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('sustancia-activa-europa.new', {
            parent: 'sustancia-activa-europa',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/sustancia-activa-europa/sustancia-activa-europa-dialog.html',
                    controller: 'Sustancia_activa_europaDialogController',
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
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('sustancia-activa-europa', null, { reload: 'sustancia-activa-europa' });
                }, function() {
                    $state.go('sustancia-activa-europa');
                });
            }]
        })
        .state('sustancia-activa-europa.edit', {
            parent: 'sustancia-activa-europa',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/sustancia-activa-europa/sustancia-activa-europa-dialog.html',
                    controller: 'Sustancia_activa_europaDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Sustancia_activa_europa', function(Sustancia_activa_europa) {
                            return Sustancia_activa_europa.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('sustancia-activa-europa', null, { reload: 'sustancia-activa-europa' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('sustancia-activa-europa.delete', {
            parent: 'sustancia-activa-europa',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/sustancia-activa-europa/sustancia-activa-europa-delete-dialog.html',
                    controller: 'Sustancia_activa_europaDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Sustancia_activa_europa', function(Sustancia_activa_europa) {
                            return Sustancia_activa_europa.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('sustancia-activa-europa', null, { reload: 'sustancia-activa-europa' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
