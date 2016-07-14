package za.co.entelect.forums.java.prac.task1;

import za.co.entelect.forums.java.example.App;
import za.co.entelect.forums.java.example.domain.Color;
import za.co.entelect.forums.java.example.domain.Vehicle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ColorSpotter {

    /*
        Given a Gantry, generate a map that groups the vehicles by colour.
        Such that the keys are colours and the values are lists of vehicles of that colour.

        App.getGantryA();
     */

    public static void main(String[] args) {
        List<Vehicle> vehicles = App.getGantryA().getVehicles();
        Map<Color, List<Vehicle>> vehiclesByColor = groupVehiclesByColor(vehicles);
        printColorReport(vehiclesByColor);
    }

    private static Map<Color, List<Vehicle>> groupVehiclesByColor(List<Vehicle> vehicles) {
        Map<Color, List<Vehicle>> vehiclesByColor = new HashMap<>();

        for (Vehicle vehicle : vehicles) {
            Color color = vehicle.getBodyColour();
            List<Vehicle> vehiclesOfColor = vehiclesByColor.get(color);

            if (vehiclesOfColor == null) {
                vehiclesOfColor = new ArrayList<>();
                vehiclesByColor.put(color, vehiclesOfColor);
            }

            vehiclesOfColor.add(vehicle);
        }

        return vehiclesByColor;
    }

    private static void printColorReport(Map<Color, List<Vehicle>> vehiclesByColor) {
        for (Color color : vehiclesByColor.keySet()) {
            System.out.println(color + ": " + vehiclesByColor.get(color).size());
        }
    }
}
