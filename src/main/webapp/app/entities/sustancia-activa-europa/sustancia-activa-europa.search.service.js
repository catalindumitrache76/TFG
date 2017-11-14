(function() {
    'use strict';

    angular
        .module('tfgjHipsterApp')
        .factory('Sustancia_activa_europaSearch', Sustancia_activa_europaSearch);

    Sustancia_activa_europaSearch.$inject = ['$resource'];

    function Sustancia_activa_europaSearch($resource) {
        var resourceUrl =  'api/_search/sustancia-activa-europas/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true}
        });
    }
})();
