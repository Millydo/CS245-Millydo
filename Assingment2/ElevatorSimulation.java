import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * In this class it enacts the elevator simulation and updates the average times 
 * passenger arrival and conveyance 
 */
public class ElevatorSimulation {
    private HashMap<Integer, Integer> passengerArrivalTime = new HashMap<>();
    private HashMap<Integer, Integer> passengerConveyanceTime = new HashMap<>();
    private int totalTime = 0;
    private int longestTime = Integer.MIN_VALUE;
    private int shortestTime = Integer.MAX_VALUE;
    private int totalPassengers = 0;
    private double averageTime = 0.0;
    ProtectedFile myFloors = new ProtectedFile();

    int totalFloors = myFloors.getFloors();

    void Print() {
        System.out.println("total floors" + totalFloors);
    }

    public void elevatorUnloadLoad(ArrayList<ArrayList<Integer>> elevators, int currentFloor) {
        // Unload all passengers in the elevator bound for the current floor
        for (ArrayList<Integer> elevator : elevators) {
            if (elevator.contains(currentFloor)) {
                elevator.removeIf(floor -> floor == currentFloor);
                
            }
        }
    }
//handles the travel elavtor ensure it only travels the max amount 
    public void elevatorTravel(ArrayList<ArrayList<Integer>> elevators, int maxFloorTravel, int totalFloors) {
        Random random = new Random();
       for (ArrayList<Integer> elevator : elevators) {
            int currentFloor = elevator.isEmpty() ? 0 : elevator.get(elevator.size() - 1);
            int floorsToTravel = random.nextInt(maxFloorTravel) + 1;
            int newFloor = currentFloor + floorsToTravel;
            if (newFloor > totalFloors) {
                newFloor = totalFloors;
            }
            elevator.add(newFloor);
        }
    }

    public void newPassengersAppear(ArrayList<ArrayList<Integer>> elevators, double probability, int totalFloors, int time) {
        Random random = new Random();
        for (int i = 0; i < totalFloors; i++) {
            double rand = random.nextDouble();
            if (rand <= probability) {
                int floorWithPassenger = i + 1; // Floor index starts from 0, adding 1 to get actual floor number
                int elevatorIndex = random.nextInt(elevators.size());
                elevators.get(elevatorIndex).add(floorWithPassenger); // Add the passenger to the selected elevator
                passengerArrivalTime.put(totalPassengers, time); // Record passenger arrival time
                totalPassengers++;
            }
        }
    }
    //determines the start floor using random class and probaility 

    public int determineStartFloor(ArrayList<ArrayList<Integer>> elevators) {
        int minFloor = Integer.MAX_VALUE;
        for (ArrayList<Integer> elevator : elevators) {
            if (!elevator.isEmpty() && elevator.get(0) < minFloor) {
                minFloor = elevator.get(0);
            }
        }
        return minFloor == Integer.MAX_VALUE ? 0 : minFloor;
    }
    //determines the end floor 

    public int determineEndFloor(int startFloor, int totalFloors) {
        Random random = new Random();
        int maxTravel = Math.min(totalFloors - startFloor, 5);
        return startFloor + random.nextInt(maxTravel) + 1;
    }
//enacts the simulation 
    public void simulateElevatorActions(int duration, int floors, int elevators, double passengers) {
        ArrayList<ArrayList<Integer>> elevatorsList = new ArrayList<>();
        for (int i = 0; i < elevators; i++) {
            elevatorsList.add(new ArrayList<>());
            System.out.println("Elevator " + i + " position: " + elevatorsList.get(i));
        }

        for (int tick = 0; tick <= duration; tick++) {
            int startFloor = determineStartFloor(elevatorsList);
            elevatorUnloadLoad(elevatorsList, startFloor);
            elevatorTravel(elevatorsList, 5, floors);
            newPassengersAppear(elevatorsList, passengers, floors, tick);

            for (int i = 0; i < elevators; i++) {
                System.out.println("Elevator " + i + " position: " + elevatorsList.get(i));
            }
        }
    }
//records passenger conveyance 
    private void recordPassengerConveyance(int passengerId, int time) {
        if (passengerArrivalTime.containsKey(passengerId)) {
            passengerConveyanceTime.put(passengerId, time);
    
            int arrivalTime = passengerArrivalTime.get(passengerId);
            int conveyanceTime = passengerConveyanceTime.get(passengerId);
    
            // Calculate the time difference
            int timeDifference = conveyanceTime - arrivalTime;
    
            // Update statistics only if conveyance time is greater than arrival time
            if (timeDifference >= 0) {
                update(timeDifference);
            }
        }
    }//updates  time and total passengers 
    
    private void update(int timeDifference) {
        if (timeDifference > longestTime) {
            longestTime = timeDifference;
        }
    
        if (timeDifference < shortestTime) {
            shortestTime = timeDifference;
        }
    
        totalTime += timeDifference;
        totalPassengers++;
    
        averageTime = (double) totalTime / totalPassengers;
    }
    
    //prints simlation results
    public void reportSimulationResults() {
        

       
        System.out.println("Average time between passenger arrival and conveyance: " + averageTime);
        System.out.println("Longest time between passenger arrival and conveyance: " + longestTime);
        System.out.println("Shortest time between passenger arrival and conveyance: " + shortestTime);
    }
}
