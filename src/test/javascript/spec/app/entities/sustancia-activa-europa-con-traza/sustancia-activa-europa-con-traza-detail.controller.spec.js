'use strict';

describe('Controller Tests', function() {

    describe('Sustancia_activa_europa_con_traza Management Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockPreviousState, MockSustancia_activa_europa_con_traza;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockPreviousState = jasmine.createSpy('MockPreviousState');
            MockSustancia_activa_europa_con_traza = jasmine.createSpy('MockSustancia_activa_europa_con_traza');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity,
                'previousState': MockPreviousState,
                'Sustancia_activa_europa_con_traza': MockSustancia_activa_europa_con_traza
            };
            createController = function() {
                $injector.get('$controller')("Sustancia_activa_europa_con_trazaDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'tfgjHipsterApp:sustancia_activa_europa_con_trazaUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
