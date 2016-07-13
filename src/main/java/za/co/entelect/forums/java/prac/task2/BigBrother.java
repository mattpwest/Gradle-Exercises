package za.co.entelect.forums.java.prac.task2;

import za.co.entelect.forums.java.example.App;
import za.co.entelect.forums.java.example.domain.Vehicle;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BigBrother {

    /*
        Given a Gantry, generate a list of unique vehicles that have passed through that gantry.

        App.getGantryA();
     */

    public static void main(String[] args) {
        List<Vehicle> vehicles = App.getGantryA().getVehicles();
        Set<Vehicle> uniqueRegistrations = new HashSet<>();
        uniqueRegistrations.addAll(vehicles);

        for (Vehicle vehicle: uniqueRegistrations) {
            System.out.println(vehicle.getRegistrationNumber());
        }
    }
}
