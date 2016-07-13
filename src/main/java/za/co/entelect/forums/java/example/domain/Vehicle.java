package za.co.entelect.forums.java.example.domain;

public abstract class Vehicle implements VehicleOperations {

    private int numberOfWheels;
    private int numberOfSeats;
    private int maxSpeed;
    private Color bodyColour;
    private String registrationNumber;

    public Vehicle(Vehicle vehicle) {
        numberOfWheels = vehicle.getNumberOfWheels();
        numberOfSeats = vehicle.getNumberOfSeats();
        maxSpeed = vehicle.getMaxSpeed();
        bodyColour = vehicle.getBodyColour();
        registrationNumber = vehicle.getRegistrationNumber();
    }

    public Vehicle(int numberOfWheels, int numberOfSeats, int maxSpeed, Color bodyColour, String registrationNumber) {
        this.numberOfWheels = numberOfWheels;
        this.numberOfSeats = numberOfSeats;
        this.maxSpeed = maxSpeed;
        this.bodyColour = bodyColour;
        this.registrationNumber = registrationNumber;
    }

    public final void drive(Gantry gantry) {
        System.out.println(getDriveDescription(gantry.getName()));
        gantry.detectVehicle(this);
    }

    public abstract String getDriveDescription(String gantryName);

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Vehicle vehicle = (Vehicle) o;

        if (numberOfWheels != vehicle.numberOfWheels) return false;
        if (numberOfSeats != vehicle.numberOfSeats) return false;
        if (maxSpeed != vehicle.maxSpeed) return false;
        return !(bodyColour != null ? !bodyColour.equals(vehicle.bodyColour) : vehicle.bodyColour != null);

    }

    @Override
    public int hashCode() {
        int result = numberOfWheels;
        result = 31 * result + numberOfSeats;
        result = 31 * result + maxSpeed;
        result = 31 * result + (bodyColour != null ? bodyColour.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return registrationNumber;
    }

    public int getNumberOfWheels() {
        return numberOfWheels;
    }

    public void setNumberOfWheels(int numberOfWheels) {
        this.numberOfWheels = numberOfWheels;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public Color getBodyColour() {
        return bodyColour;
    }

    public void setBodyColour(Color bodyColour) {
        this.bodyColour = bodyColour;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }
}
