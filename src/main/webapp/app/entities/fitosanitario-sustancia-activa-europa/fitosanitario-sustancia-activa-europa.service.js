(function() {
    'use strict';
    angular
        .module('tfgjHipsterApp')
        .factory('Fitosanitario_sustancia_activa_europa', Fitosanitario_sustancia_activa_europa);

    Fitosanitario_sustancia_activa_europa.$inject = ['$resource'];

    function Fitosanitario_sustancia_activa_europa ($resource) {
        var resourceUrl =  'api/fitosanitario-sustancia-activa-europas/:id';

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
