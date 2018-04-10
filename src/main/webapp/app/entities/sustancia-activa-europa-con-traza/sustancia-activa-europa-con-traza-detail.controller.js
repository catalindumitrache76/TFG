(function() {
    'use strict';

    angular
        .module('tfgjHipsterApp')
        .controller('Sustancia_activa_europa_con_trazaDetailController', Sustancia_activa_europa_con_trazaDetailController);

    Sustancia_activa_europa_con_trazaDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Sustancia_activa_europa_con_traza'];

    function Sustancia_activa_europa_con_trazaDetailController($scope, $rootScope, $stateParams, previousState, entity, Sustancia_activa_europa_con_traza) {
        var vm = this;

        vm.sustancia_activa_europa_con_traza = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('tfgjHipsterApp:sustancia_activa_europa_con_trazaUpdate', function(event, result) {
            vm.sustancia_activa_europa_con_traza = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
