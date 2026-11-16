import java.awt.Color;

public abstract class Car implements Movable {
    private int nrDoors;
    private double enginePower;
    private double currentSpeed;
    private Color color;
    private String modelName;
    private double x;
    private double y;
    private Direction direction;

    protected Car(int nrDoors, double enginePower, Color color, String modelName) {
        this.nrDoors = nrDoors;
        this.enginePower = enginePower;
        this.color = color;
        this.modelName = modelName;
        this.currentSpeed = 0;
        this.x = 0;
        this.y = 0;
        this.direction = Direction.NORTH;

    }

    public int getNrDoors() {
        return nrDoors;
    }

    public double getEnginePower() {
        return enginePower;
    }

    public double getCurrentSpeed() {
        return currentSpeed;
    }

    public Color getColor() {
        return color;
    }

    public String getModelName() {
        return modelName;
    }
    public Direction getDirection(){
        return direction;
    }
    public double getX(){
        return x;
    }
    public double getY(){
        return y;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void startEngine() {
        this.currentSpeed = 0.1;
    }

    public void stopEngine() {
        this.currentSpeed = 0;
    }
    protected abstract double speedFactor();

    protected void incrementSpeed(double amount){
        double newSpeed = getCurrentSpeed() + speedFactor() * amount;
        currentSpeed = Math.min(enginePower, newSpeed);
    }
    protected void decrementSpeed(double amount){
        double newSpeed = getCurrentSpeed() - speedFactor() * amount;
        currentSpeed = Math.max(0, newSpeed);
    }
    public void gas(double amount){
        if (amount >= 0 && amount <= 1){
            incrementSpeed(amount);
        }
    }
    public void brake(double amount){
        if (amount >= 0 && amount <= 1){
            decrementSpeed(amount);
        }

    }
    @Override
    public void turnLeft(){
        switch (direction){
            case NORTH:
                direction = Direction.WEST;
                break;
            case SOUTH:
                direction = Direction.EAST;
                break;
            case EAST:
                direction = Direction.NORTH;
                break;
            case WEST:
                direction = Direction.SOUTH;
                break;
        }
    }
    @Override
    public void turnRight(){
        switch (direction){
            case NORTH:
                direction = Direction.EAST;
                break;
            case SOUTH:
                direction = Direction.WEST;
                break;
            case EAST:
                direction = Direction.SOUTH;
                break;
            case WEST:
                direction = Direction.NORTH;
                break;
        }
    }
    @Override
    public void move(){
        switch (direction){
            case NORTH:
                y += currentSpeed;
                break;
            case SOUTH:
                y -= currentSpeed;
                break;
            case EAST:
                x +=currentSpeed;
                break;
            case WEST:
                x -= currentSpeed;
                break;
        }
    }




}
