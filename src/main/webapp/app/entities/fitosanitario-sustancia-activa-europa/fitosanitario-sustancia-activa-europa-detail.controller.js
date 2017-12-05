(function() {
    'use strict';

    angular
        .module('tfgjHipsterApp')
        .controller('Fitosanitario_sustancia_activa_europaDetailController', Fitosanitario_sustancia_activa_europaDetailController);

    Fitosanitario_sustancia_activa_europaDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Fitosanitario_sustancia_activa_europa'];

    function Fitosanitario_sustancia_activa_europaDetailController($scope, $rootScope, $stateParams, previousState, entity, Fitosanitario_sustancia_activa_europa) {
        var vm = this;

        vm.fitosanitario_sustancia_activa_europa = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('tfgjHipsterApp:fitosanitario_sustancia_activa_europaUpdate', function(event, result) {
            vm.fitosanitario_sustancia_activa_europa = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
