(function() {
    'use strict';

    angular
        .module('tfgjHipsterApp')
        .controller('Sustancia_activa_europaDetailController', Sustancia_activa_europaDetailController);

    Sustancia_activa_europaDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Sustancia_activa_europa'];

    function Sustancia_activa_europaDetailController($scope, $rootScope, $stateParams, previousState, entity, Sustancia_activa_europa) {
        var vm = this;

        vm.sustancia_activa_europa = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('tfgjHipsterApp:sustancia_activa_europaUpdate', function(event, result) {
            vm.sustancia_activa_europa = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
