(function() {
    'use strict';

    angular
        .module('tfgJHipsterApp')
        .controller('FitosanitarioMySuffixDeleteController',FitosanitarioMySuffixDeleteController);

    FitosanitarioMySuffixDeleteController.$inject = ['$uibModalInstance', 'entity', 'Fitosanitario'];

    function FitosanitarioMySuffixDeleteController($uibModalInstance, entity, Fitosanitario) {
        var vm = this;

        vm.fitosanitario = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Fitosanitario.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
