(function() {
    'use strict';

    angular
        .module('tfgjHipsterApp')
        .factory('Fitosanitario_sustancia_activa_europaSearch', Fitosanitario_sustancia_activa_europaSearch);

    Fitosanitario_sustancia_activa_europaSearch.$inject = ['$resource'];

    function Fitosanitario_sustancia_activa_europaSearch($resource) {
        var resourceUrl =  'api/_search/fitosanitario-sustancia-activa-europas/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true}
        });
    }
})();
