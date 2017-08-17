(function() {
    'use strict';

    angular
        .module('tfgJHipsterApp')
        .controller('EditorialMySuffixDetailController', EditorialMySuffixDetailController);

    EditorialMySuffixDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Editorial', 'Fitosanitario'];

    function EditorialMySuffixDetailController($scope, $rootScope, $stateParams, previousState, entity, Editorial, Fitosanitario) {
        var vm = this;

        vm.editorial = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('tfgJHipsterApp:editorialUpdate', function(event, result) {
            vm.editorial = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
