(function() {
    'use strict';

    angular
        .module('tfgJHipsterApp')
        .controller('FitosanitarioMySuffixDialogController', FitosanitarioMySuffixDialogController);

    FitosanitarioMySuffixDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Fitosanitario', 'Editorial'];

    function FitosanitarioMySuffixDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Fitosanitario, Editorial) {
        var vm = this;

        vm.fitosanitario = entity;
        vm.clear = clear;
        vm.save = save;
        vm.editorials = Editorial.query();

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.fitosanitario.id !== null) {
                Fitosanitario.update(vm.fitosanitario, onSaveSuccess, onSaveError);
            } else {
                Fitosanitario.save(vm.fitosanitario, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('tfgJHipsterApp:fitosanitarioUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
