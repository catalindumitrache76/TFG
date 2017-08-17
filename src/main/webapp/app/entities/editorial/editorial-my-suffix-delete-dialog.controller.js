(function() {
    'use strict';

    angular
        .module('tfgJHipsterApp')
        .controller('EditorialMySuffixDeleteController',EditorialMySuffixDeleteController);

    EditorialMySuffixDeleteController.$inject = ['$uibModalInstance', 'entity', 'Editorial'];

    function EditorialMySuffixDeleteController($uibModalInstance, entity, Editorial) {
        var vm = this;

        vm.editorial = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Editorial.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
