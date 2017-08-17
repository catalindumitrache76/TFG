(function() {
    'use strict';

    angular
        .module('tfgJHipsterApp')
        .controller('EditorialMySuffixDialogController', EditorialMySuffixDialogController);

    EditorialMySuffixDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Editorial', 'Fitosanitario'];

    function EditorialMySuffixDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Editorial, Fitosanitario) {
        var vm = this;

        vm.editorial = entity;
        vm.clear = clear;
        vm.save = save;
        vm.fitosanitarios = Fitosanitario.query();

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.editorial.id !== null) {
                Editorial.update(vm.editorial, onSaveSuccess, onSaveError);
            } else {
                Editorial.save(vm.editorial, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('tfgJHipsterApp:editorialUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
