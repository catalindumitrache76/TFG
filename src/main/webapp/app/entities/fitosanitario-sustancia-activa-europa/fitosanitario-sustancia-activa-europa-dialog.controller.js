(function() {
    'use strict';

    angular
        .module('tfgjHipsterApp')
        .controller('Fitosanitario_sustancia_activa_europaDialogController', Fitosanitario_sustancia_activa_europaDialogController);

    Fitosanitario_sustancia_activa_europaDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Fitosanitario_sustancia_activa_europa'];

    function Fitosanitario_sustancia_activa_europaDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Fitosanitario_sustancia_activa_europa) {
        var vm = this;

        vm.fitosanitario_sustancia_activa_europa = entity;
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
            if (vm.fitosanitario_sustancia_activa_europa.id !== null) {
                Fitosanitario_sustancia_activa_europa.update(vm.fitosanitario_sustancia_activa_europa, onSaveSuccess, onSaveError);
            } else {
                Fitosanitario_sustancia_activa_europa.save(vm.fitosanitario_sustancia_activa_europa, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('tfgjHipsterApp:fitosanitario_sustancia_activa_europaUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
