(function() {
    'use strict';

    angular
        .module('tfgjHipsterApp')
        .controller('Fitosanitario_sustancia_activa_europaDeleteController',Fitosanitario_sustancia_activa_europaDeleteController);

    Fitosanitario_sustancia_activa_europaDeleteController.$inject = ['$uibModalInstance', 'entity', 'Fitosanitario_sustancia_activa_europa'];

    function Fitosanitario_sustancia_activa_europaDeleteController($uibModalInstance, entity, Fitosanitario_sustancia_activa_europa) {
        var vm = this;

        vm.fitosanitario_sustancia_activa_europa = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Fitosanitario_sustancia_activa_europa.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
