(function() {
    'use strict';
    angular
        .module('tfgJHipsterApp')
        .factory('Editorial', Editorial);

    Editorial.$inject = ['$resource'];

    function Editorial ($resource) {
        var resourceUrl =  'api/editorials/:id';

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
