(function() {
    'use strict';

    angular
        .module('tfgjHipsterApp')
        .controller('FitosanitarioDetailController', FitosanitarioDetailController);

    FitosanitarioDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Fitosanitario'];

    function FitosanitarioDetailController($scope, $rootScope, $stateParams, previousState, entity, Fitosanitario) {
        var vm = this;

        vm.fitosanitario = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('tfgjHipsterApp:fitosanitarioUpdate', function(event, result) {
            vm.fitosanitario = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
