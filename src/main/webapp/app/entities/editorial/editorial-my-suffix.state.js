(function() {
    'use strict';

    angular
        .module('tfgJHipsterApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('editorial-my-suffix', {
            parent: 'entity',
            url: '/editorial-my-suffix',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'Editorials'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/editorial/editorialsmySuffix.html',
                    controller: 'EditorialMySuffixController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
            }
        })
        .state('editorial-my-suffix-detail', {
            parent: 'editorial-my-suffix',
            url: '/editorial-my-suffix/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'Editorial'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/editorial/editorial-my-suffix-detail.html',
                    controller: 'EditorialMySuffixDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                entity: ['$stateParams', 'Editorial', function($stateParams, Editorial) {
                    return Editorial.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'editorial-my-suffix',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('editorial-my-suffix-detail.edit', {
            parent: 'editorial-my-suffix-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/editorial/editorial-my-suffix-dialog.html',
                    controller: 'EditorialMySuffixDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Editorial', function(Editorial) {
                            return Editorial.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('editorial-my-suffix.new', {
            parent: 'editorial-my-suffix',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/editorial/editorial-my-suffix-dialog.html',
                    controller: 'EditorialMySuffixDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                nombreEditorial: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('editorial-my-suffix', null, { reload: 'editorial-my-suffix' });
                }, function() {
                    $state.go('editorial-my-suffix');
                });
            }]
        })
        .state('editorial-my-suffix.edit', {
            parent: 'editorial-my-suffix',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/editorial/editorial-my-suffix-dialog.html',
                    controller: 'EditorialMySuffixDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Editorial', function(Editorial) {
                            return Editorial.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('editorial-my-suffix', null, { reload: 'editorial-my-suffix' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('editorial-my-suffix.delete', {
            parent: 'editorial-my-suffix',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/editorial/editorial-my-suffix-delete-dialog.html',
                    controller: 'EditorialMySuffixDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Editorial', function(Editorial) {
                            return Editorial.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('editorial-my-suffix', null, { reload: 'editorial-my-suffix' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
