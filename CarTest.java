public class CarTest {
    private static void testInitialSpeed(){
        Volvo240 volvo = new Volvo240();
        System.out.println(volvo.getCurrentSpeed());
        boolean result = (volvo.getCurrentSpeed() == 0);
        reportResult("Volvo initial speed", result);
    }

    private static void testStartEngine(){
        Volvo240 volvo = new Volvo240();
        volvo.startEngine();
        boolean resultStartEngine = (volvo.getCurrentSpeed() == 0.1);
        reportResult("Engine starts correctly", resultStartEngine);
    }

    private static void testStopEngine(){
        Volvo240 volvo = new Volvo240();
        volvo.startEngine();
        volvo.stopEngine();
        boolean resultStopEngine = (volvo.getCurrentSpeed() == 0);
        reportResult("Engine stops correctly", resultStopEngine);
    }

    private static void testGas(){
        Volvo240 volvo = new Volvo240();
        volvo.gas(0.5);
        boolean resultGas = (volvo.getCurrentSpeed()>0);
        reportResult("Gas increases speed", resultGas);
    }

    private static void testBrake(){
        Volvo240 volvo = new Volvo240();
        volvo.startEngine();
        volvo.gas(1);
        double speedBeforeBrake = volvo.getCurrentSpeed();
        volvo.brake(0.5);
        boolean resultBrake = (volvo.getCurrentSpeed() < speedBeforeBrake);
        reportResult("Brake decreases speed", resultBrake);
    }

    private static void testTurning(){
        Volvo240 volvo = new Volvo240();
        volvo.turnLeft();
        boolean resultTurnLeft = (volvo.getDirection() == Direction.WEST);
        reportResult("Turn left works correctly", resultTurnLeft);
        volvo.turnRight();
        boolean resultTurnRight = (volvo.getDirection() == Direction.NORTH);
        reportResult("Trun right works correctly", resultTurnRight);
    }

    private static void testMovement(){
        Volvo240 volvo = new Volvo240();
        double yBeforeMove = volvo.getY();
        volvo.startEngine();
        volvo.move();
        boolean resultMoveY = (volvo.getY() > yBeforeMove);
        reportResult("Moving north increases y", resultMoveY);
        volvo.turnRight();
        double xBeforeMove = volvo.getX();
        volvo.move();
        boolean resultMoveX  = (volvo.getX() > xBeforeMove);
        reportResult("Moving east increases x", resultMoveX);
    }

    private static void testInvalidAmounts(){
        Volvo240 testVolvo = new Volvo240();
        testVolvo.startEngine();
        double speedBeforeGas = testVolvo.getCurrentSpeed();
        testVolvo.gas(-0.5);
        boolean resultInvalidGas = (testVolvo.getCurrentSpeed() == speedBeforeGas);
        reportResult("Invalid gas amount is rejected", resultInvalidGas);
        Volvo240 brakeTestVolvo = new Volvo240();
        brakeTestVolvo.startEngine();
        double speedBeforeInvalidBrake = brakeTestVolvo.getCurrentSpeed();
        brakeTestVolvo.brake(-0.5);
        boolean resultInvalidBrake = (brakeTestVolvo.getCurrentSpeed() == speedBeforeInvalidBrake);
        reportResult ("Invalid brake amount is rejected", resultInvalidBrake);
    }

    private static void testSpeedLimits(){
        Volvo240 minimumSpeedVolvo = new Volvo240();
        minimumSpeedVolvo.startEngine();
        minimumSpeedVolvo.brake(1);
        boolean resultMinimumSpeedVolvo = (minimumSpeedVolvo.getCurrentSpeed() >= 0);
        reportResult ("Speed cannot go  below zero", resultMinimumSpeedVolvo);
        Volvo240 maximumSpeedVolvo = new Volvo240();
        for (int i = 0; i < 100; i++) {
            maximumSpeedVolvo.gas(1);
        }
        boolean resultMaximumSpeedVolvo = (maximumSpeedVolvo.getCurrentSpeed() <= maximumSpeedVolvo.getEnginePower());
        reportResult("Speed cannot exceed engine power", resultMaximumSpeedVolvo);
    }

    private static void testSpeedSafety(){
        Volvo240 gasVolvo = new Volvo240();
        gasVolvo.startEngine();
        double speedBeforeSafetyGas = gasVolvo.getCurrentSpeed();
        gasVolvo.gas(1);
        boolean resultGasVolvo = (gasVolvo.getCurrentSpeed() >= speedBeforeSafetyGas);
        reportResult ("Gas never decreases speed", resultGasVolvo);
        Volvo240 brakeVolvo = new Volvo240();
        brakeVolvo.startEngine();
        double speedBeforeSafetyBrake = brakeVolvo.getCurrentSpeed();
        brakeVolvo.brake(1);
        boolean resultBrakeVolvo = (brakeVolvo.getCurrentSpeed() <= speedBeforeSafetyBrake);
        reportResult("Brake never increases speed", resultBrakeVolvo);
    }

    private static void testSaabMaxSpeed(){
        Saab95 testSaab = new Saab95();
        testSaab.setTurboOn();
        for (int i = 0; i  < 100; i++){
            testSaab.gas(1);

        }
        boolean resultSaabMaxSpeed = (testSaab.getCurrentSpeed() <= testSaab.getEnginePower());
        reportResult("Saab maximum speed", resultSaabMaxSpeed);
    }

    public static void main(String[] args) {
        testInitialSpeed();
        testTurbo();
        testStartEngine();
        testStopEngine();
        testGas();
        testBrake();
        testTurning();
        testMovement();
        testInvalidAmounts();
        testSpeedLimits();
        testSpeedSafety();
        testSaabMaxSpeed();
    }

        private static void reportResult(String testName, boolean success) {
            System.out.println((success ? "SUCCESS: " : "FAILURE: ") + testName);
        }

        /** Tests whether a Saab95 with turbo enabled
         *  gains more speed than one without.
         */
        private static void testTurbo() {
            Saab95 slowSaab = new Saab95();
            Saab95 fastSaab = new Saab95();
            fastSaab.setTurboOn();
            slowSaab.setTurboOff();
            fastSaab.gas(0.5);
            slowSaab.gas(0.5);
            boolean result = slowSaab.getCurrentSpeed() < fastSaab.getCurrentSpeed();
            reportResult("Turbo yields more speed", result);
        }
}
