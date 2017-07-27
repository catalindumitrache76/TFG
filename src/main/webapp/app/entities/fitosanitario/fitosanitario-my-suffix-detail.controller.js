(function() {
    'use strict';

    angular
        .module('tfgJHipsterApp')
        .controller('FitosanitarioMySuffixDetailController', FitosanitarioMySuffixDetailController);

    FitosanitarioMySuffixDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Fitosanitario', 'Editorial'];

    function FitosanitarioMySuffixDetailController($scope, $rootScope, $stateParams, previousState, entity, Fitosanitario, Editorial) {
        var vm = this;

        vm.fitosanitario = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('tfgJHipsterApp:fitosanitarioUpdate', function(event, result) {
            vm.fitosanitario = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
