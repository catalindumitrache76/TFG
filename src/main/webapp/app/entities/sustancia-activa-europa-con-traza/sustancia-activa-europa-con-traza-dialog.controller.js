(function() {
    'use strict';

    angular
        .module('tfgjHipsterApp')
        .controller('Sustancia_activa_europa_con_trazaDialogController', Sustancia_activa_europa_con_trazaDialogController);

    Sustancia_activa_europa_con_trazaDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Sustancia_activa_europa_con_traza'];

    function Sustancia_activa_europa_con_trazaDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Sustancia_activa_europa_con_traza) {
        var vm = this;

        vm.sustancia_activa_europa_con_traza = entity;
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
            if (vm.sustancia_activa_europa_con_traza.id !== null) {
                Sustancia_activa_europa_con_traza.update(vm.sustancia_activa_europa_con_traza, onSaveSuccess, onSaveError);
            } else {
                Sustancia_activa_europa_con_traza.save(vm.sustancia_activa_europa_con_traza, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('tfgjHipsterApp:sustancia_activa_europa_con_trazaUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
