(function() {
    'use strict';

    angular
        .module('tfgJHipsterApp')
        .controller('EditorialMySuffixController', EditorialMySuffixController);

    EditorialMySuffixController.$inject = ['Editorial'];

    function EditorialMySuffixController(Editorial) {

        var vm = this;

        vm.editorials = [];

        loadAll();

        function loadAll() {
            Editorial.query(function(result) {
                vm.editorials = result;
                vm.searchQuery = null;
            });
        }
    }
})();
