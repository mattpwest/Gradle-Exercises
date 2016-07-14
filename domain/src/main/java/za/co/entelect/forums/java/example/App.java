package za.co.entelect.forums.java.example;

import za.co.entelect.forums.java.example.domain.*;

import java.util.*;

public class App {

    private static Car carA = new Car(4, 4, 200, Color.Black, "ASD123GP");
    private static Car carB = new Car(4, 2, 250, Color.Cyan, "SDF234GP");
    private static Car carC = new Car(4, 4, 200, Color.Black, "YUI678GP");
    private static Truck truckA = new Truck(6, 2, 120, Color.Blue, 4, "CVB456GP");
    private static Truck truckB = new Truck(18, 2, 400, Color.Red, 100, "ERT234GP");
    private static Truck truckC = new Truck(10, 2, 120, Color.Green, 3, "BNM678GP");
    private static Truck truckD = new Truck(6, 2, 120, Color.Blue, 4, "WER345GP");

    public static void main( String[] args ) {

        Set<Vehicle> vehiclesSet = getVehicleSet();
        List<Vehicle> vehiclesList = getVehicleList();
        Map<String, Vehicle> vehiclesMap = getVehicleMap();
        Deque<Vehicle> vehiclesQueue = getVehicleQueue();
        List<Gantry> gantryList = getGantryList();

        System.out.println("Set count: " + vehiclesSet.size() );
        System.out.println(vehiclesSet.toString());
        System.out.println("List count: " + vehiclesList.size() );
        System.out.println(vehiclesList.toString());
        System.out.println("Map count: " + vehiclesMap.size() );
        System.out.println(vehiclesMap.toString());
        System.out.println("Queue count: " + vehiclesQueue.size() );
        System.out.println(vehiclesQueue.toString());

        System.out.println("Here's an object from the map with key carB: " + vehiclesMap.get("carB").toString());

        System.out.println("Here's the first object in the queue: " + vehiclesQueue.pop().toString());

        System.out.println(getGantryA().getVehicles().toString());

        for (int times = 0; times < 10; times++) {
            for (int vehicleIndex = 0; vehicleIndex < vehiclesList.size(); vehicleIndex++) {
                Vehicle vehicle = vehiclesList.get(vehicleIndex);

                int time = times * vehiclesList.size() + vehicleIndex + 1;
                vehicle.drive(gantryList.get(time % gantryList.size()));
            }
        }
    }

    public static Set<Vehicle> getVehicleSet() {
        Set<Vehicle> vehicles = new HashSet<>();
        vehicles.add(new Car(carA));
        vehicles.add(new Car(carB));
        vehicles.add(new Car(carC));
        vehicles.add(new Truck(truckA));
        vehicles.add(new Truck(truckB));
        vehicles.add(new Truck(truckC));
        vehicles.add(new Truck(truckD));

        return vehicles;
    }

    public static List<Vehicle> getVehicleList() {
        List<Vehicle> vehicles = new ArrayList<>();
        vehicles.add(new Car(carA));
        vehicles.add(new Car(carB));
        vehicles.add(new Car(carC));
        vehicles.add(new Truck(truckA));
        vehicles.add(new Truck(truckB));
        vehicles.add(new Truck(truckC));
        vehicles.add(new Truck(truckD));

        return vehicles;
    }

    public static Map<String, Vehicle> getVehicleMap() {
        Map<String, Vehicle> vehicles = new HashMap<>();
        vehicles.put("carA", new Car(carA));
        vehicles.put("carB", new Car(carB));
        vehicles.put("carC", new Car(carC));
        vehicles.put("truckA", new Truck(truckA));
        vehicles.put("truckB", new Truck(truckB));
        vehicles.put("truckC", new Truck(truckC));
        vehicles.put("truckD", new Truck(truckD));

        return vehicles;
    }

    public static Deque<Vehicle> getVehicleQueue() {
        Deque<Vehicle> vehicles = new ArrayDeque<>();
        vehicles.add(new Car(carA));
        vehicles.add(new Car(carB));
        vehicles.add(new Car(carC));
        vehicles.add(new Truck(truckA));
        vehicles.add(new Truck(truckB));
        vehicles.add(new Truck(truckC));
        vehicles.add(new Truck(truckD));

        return vehicles;
    }

    public static List<Gantry> getGantryList() {
        List<Gantry> gantries = new ArrayList<>();
        gantries.add(getGantryA());
        gantries.add(getGantryB());
        gantries.add(getGantryC());
        return gantries;
    }

    public static Gantry getGantryA() {
        return generateGantry("Zambesi", 7.0, 1, 245);
    }

    public static Gantry getGantryB() {
        return generateGantry("Kroondal", 50.0, 2, 112);
    }

    public static Gantry getGantryC() {
        return generateGantry("Cullinan", 125.0, 3, 122);
    }

    private static Gantry generateGantry(String name, Double cost, int seed, int quantity) {
        Random random = new Random(seed);
        Gantry gantry = new Gantry(name, cost);
        List<Vehicle> vehicles = getVehicleList();
        List<Vehicle> vehiclesSpotted = new ArrayList<>();

        for (int i = 0; i < quantity; i++) {
            vehiclesSpotted.add(vehicles.get(random.nextInt(vehicles.size())));
        }
        gantry.setVehicles(vehiclesSpotted);
        return gantry;
    }
}
