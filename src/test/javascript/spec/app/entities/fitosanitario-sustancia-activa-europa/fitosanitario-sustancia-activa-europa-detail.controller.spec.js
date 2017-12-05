'use strict';

describe('Controller Tests', function() {

    describe('Fitosanitario_sustancia_activa_europa Management Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockPreviousState, MockFitosanitario_sustancia_activa_europa;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockPreviousState = jasmine.createSpy('MockPreviousState');
            MockFitosanitario_sustancia_activa_europa = jasmine.createSpy('MockFitosanitario_sustancia_activa_europa');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity,
                'previousState': MockPreviousState,
                'Fitosanitario_sustancia_activa_europa': MockFitosanitario_sustancia_activa_europa
            };
            createController = function() {
                $injector.get('$controller')("Fitosanitario_sustancia_activa_europaDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'tfgjHipsterApp:fitosanitario_sustancia_activa_europaUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
