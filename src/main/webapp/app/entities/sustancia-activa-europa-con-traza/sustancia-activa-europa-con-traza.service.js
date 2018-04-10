(function() {
    'use strict';
    angular
        .module('tfgjHipsterApp')
        .factory('Sustancia_activa_europa_con_traza', Sustancia_activa_europa_con_traza);

    Sustancia_activa_europa_con_traza.$inject = ['$resource'];

    function Sustancia_activa_europa_con_traza ($resource) {
        var resourceUrl =  'api/sustancia-activa-europa-con-trazas/:id';

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
