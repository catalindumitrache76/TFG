(function() {
    'use strict';
    angular
        .module('tfgjHipsterApp')
        .factory('Fitosanitario', Fitosanitario);

    Fitosanitario.$inject = ['$resource'];

    function Fitosanitario ($resource) {
        var resourceUrl =  'api/fitosanitarios/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                    }
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    }
})();
