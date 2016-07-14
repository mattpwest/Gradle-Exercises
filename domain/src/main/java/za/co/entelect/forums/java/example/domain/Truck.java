package za.co.entelect.forums.java.example.domain;

public class Truck extends Vehicle {

    private double loadCapacityInTons;

    public Truck(Truck truck) {
        super(truck);
        loadCapacityInTons = truck.getLoadCapacityInTons();
    }

    public Truck(int numberOfWheels, int numberOfSeats, int maxSpeed, Color bodyColour, double loadCapacityInTons, String registrationNumber) {
        super(numberOfWheels, numberOfSeats, maxSpeed, bodyColour, registrationNumber);
        this.loadCapacityInTons = loadCapacityInTons;
    }

    public double getLoadCapacityInTons() {
        return loadCapacityInTons;
    }

    public void setLoadCapacityInTons(double loadCapacityInTons) {
        this.loadCapacityInTons = loadCapacityInTons;
    }

    public void drive() {
        StringBuilder driveResult = new StringBuilder("I'm a truck driving with a ");
        driveResult.append(loadCapacityInTons);
        driveResult.append(" load capacity.");
        System.out.println(driveResult.toString());
    }

    public String getDriveDescription(String gantryName) {
        return String.format("%s truck with registration %s and capacity of %.2f tons just drove past the %s gantry.",
            getBodyColour().toString(),
            getRegistrationNumber(),
            getLoadCapacityInTons(),
            gantryName);
    }
}
