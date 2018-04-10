(function() {
    'use strict';

    angular
        .module('tfgjHipsterApp')
        .controller('Sustancia_activa_europa_con_trazaDeleteController',Sustancia_activa_europa_con_trazaDeleteController);

    Sustancia_activa_europa_con_trazaDeleteController.$inject = ['$uibModalInstance', 'entity', 'Sustancia_activa_europa_con_traza'];

    function Sustancia_activa_europa_con_trazaDeleteController($uibModalInstance, entity, Sustancia_activa_europa_con_traza) {
        var vm = this;

        vm.sustancia_activa_europa_con_traza = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Sustancia_activa_europa_con_traza.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
