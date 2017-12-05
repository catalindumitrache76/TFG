(function() {
    'use strict';

    angular
        .module('tfgjHipsterApp')
        .controller('Sustancia_activa_europaDialogController', Sustancia_activa_europaDialogController);

    Sustancia_activa_europaDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Sustancia_activa_europa'];

    function Sustancia_activa_europaDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Sustancia_activa_europa) {
        var vm = this;

        vm.sustancia_activa_europa = entity;
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
            if (vm.sustancia_activa_europa.id !== null) {
                Sustancia_activa_europa.update(vm.sustancia_activa_europa, onSaveSuccess, onSaveError);
            } else {
                Sustancia_activa_europa.save(vm.sustancia_activa_europa, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('tfgjHipsterApp:sustancia_activa_europaUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
