(function() {
    'use strict';

    angular
        .module('tfgjHipsterApp')
        .factory('FitosanitarioSearch', FitosanitarioSearch);

    FitosanitarioSearch.$inject = ['$resource'];

    function FitosanitarioSearch($resource) {
        var resourceUrl =  'api/_search/fitosanitarios/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true}
        });
    }
})();
