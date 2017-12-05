(function() {
    'use strict';

    angular
        .module('tfgjHipsterApp')
        .controller('FitosanitarioDialogController', FitosanitarioDialogController);

    FitosanitarioDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Fitosanitario'];

    function FitosanitarioDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Fitosanitario) {
        var vm = this;

        vm.fitosanitario = entity;
        vm.clear = clear;
        vm.save = save;

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
            $scope.$emit('tfgjHipsterApp:fitosanitarioUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
