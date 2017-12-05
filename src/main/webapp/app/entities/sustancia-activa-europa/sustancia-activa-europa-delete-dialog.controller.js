(function() {
    'use strict';

    angular
        .module('tfgjHipsterApp')
        .controller('Sustancia_activa_europaDeleteController',Sustancia_activa_europaDeleteController);

    Sustancia_activa_europaDeleteController.$inject = ['$uibModalInstance', 'entity', 'Sustancia_activa_europa'];

    function Sustancia_activa_europaDeleteController($uibModalInstance, entity, Sustancia_activa_europa) {
        var vm = this;

        vm.sustancia_activa_europa = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Sustancia_activa_europa.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
