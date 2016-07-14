package za.co.entelect.forums.java.prac.task3;

import za.co.entelect.forums.java.example.App;
import za.co.entelect.forums.java.example.domain.Gantry;
import za.co.entelect.forums.java.example.domain.Vehicle;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Corruption {

    /*
        Given a collection of Gantries, generate a list of unique vehicles with their total toll bill.

        App.getGantryList();
     */

    public static void main(String[] args) {
        List<Gantry> gantries = App.getGantryList();
        Map<Vehicle, BigDecimal> vehicleBill = new HashMap<>();

        for (Gantry gantry : gantries) {
            for (Vehicle vehicle : gantry.getVehicles()) {
                if (!vehicleBill.containsKey(vehicle)) {
                    vehicleBill.put(vehicle, new BigDecimal(gantry.getToll()));
                } else {
                    BigDecimal value = vehicleBill.get(vehicle);
                    vehicleBill.put(vehicle, value.add(new BigDecimal(gantry.getToll())));
                }
            }
        }

        for (Vehicle vehicle : vehicleBill.keySet()) {
            System.out.println(vehicle.getRegistrationNumber() + ": " + vehicleBill.get(vehicle));
        }
    }
}
