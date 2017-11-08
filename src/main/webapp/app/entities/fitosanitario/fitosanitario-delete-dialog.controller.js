(function() {
    'use strict';

    angular
        .module('tfgjHipsterApp')
        .controller('FitosanitarioDeleteController',FitosanitarioDeleteController);

    FitosanitarioDeleteController.$inject = ['$uibModalInstance', 'entity', 'Fitosanitario'];

    function FitosanitarioDeleteController($uibModalInstance, entity, Fitosanitario) {
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
