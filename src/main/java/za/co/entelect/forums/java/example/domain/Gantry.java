package za.co.entelect.forums.java.example.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Gantry {

    private String name;
    private Double toll;
    private List<Vehicle> vehicles;
    private Map<Vehicle, List<LocalDateTime>> vehicleTimes;
    private LocalDateTime currentTime = LocalDateTime.now().minusDays(10);


    public Gantry(String name, Double toll) {
        this.name = name;
        this.toll = toll;
        vehicles = new ArrayList<Vehicle>();
        vehicleTimes = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    public Double getToll() {
        return toll;
    }

    public void setToll(Double toll) {
        this.toll = toll;
    }

    public void detectVehicle(Vehicle vehicle) {
        vehicles.add(vehicle);

        currentTime = currentTime.plusHours(1);
        addTimeForCar(vehicle, currentTime);
    }

    private void addTimeForCar(Vehicle vehicle, LocalDateTime currentTime) {
        if (!vehicleTimes.containsKey(vehicle)) {
            vehicleTimes.put(vehicle, new ArrayList<LocalDateTime>());
        }

        vehicleTimes.get(vehicle).add(currentTime);
    }
}
