(function() {
    'use strict';

    angular
        .module('tfgjHipsterApp')
        .factory('Sustancia_activa_europa_con_trazaSearch', Sustancia_activa_europa_con_trazaSearch);

    Sustancia_activa_europa_con_trazaSearch.$inject = ['$resource'];

    function Sustancia_activa_europa_con_trazaSearch($resource) {
        var resourceUrl =  'api/_search/sustancia-activa-europa-con-trazas/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true}
        });
    }
})();
