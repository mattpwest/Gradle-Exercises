package za.co.entelect.forums.java.example.domain;

public class Car extends Vehicle {

    public Car(Car car) {
        super(car);
    }

    public Car(int numberOfWheels, int numberOfSeats, int maxSpeed, Color bodyColour, String registrationNumber) {
        super(numberOfWheels, numberOfSeats, maxSpeed, bodyColour, registrationNumber);
    }

    public String getDriveDescription(String gantryName) {
        return String.format("%s car with registration %s just drove past the %s gantry.",
            getBodyColour().toString(),
            getRegistrationNumber(),
            gantryName);
    }

}
