(function() {
    'use strict';
    angular
        .module('tfgjHipsterApp')
        .factory('Sustancia_activa_europa', Sustancia_activa_europa);

    Sustancia_activa_europa.$inject = ['$resource'];

    function Sustancia_activa_europa ($resource) {
        var resourceUrl =  'api/sustancia-activa-europas/:id';

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
