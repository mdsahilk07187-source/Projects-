import java.util.*;

interface ElevatorOperations {
    void requestFloor(int floor);
    void runElevator();
}


class Elevator implements ElevatorOperations {
    private final int maxFloor;
    private int currentFloor;
    private boolean movingUp;
    private final TreeSet<Integer> upRequests;
    private final TreeSet<Integer> downRequests;

    public Elevator(int maxFloor) {
        this.maxFloor = maxFloor;
        this.currentFloor = 0; 
        this.movingUp = true;
        this.upRequests = new TreeSet<>();
        this.downRequests = new TreeSet<>(Comparator.reverseOrder());
    }

    @Override
    public void requestFloor(int floor) {
        if (floor < 0 || floor > maxFloor) {
            System.out.println("Invalid floor request: " + floor);
            return;
        }
        if (floor == currentFloor) {
            System.out.println("You are already on floor " + floor);
            return;
        }

        
        if (floor > currentFloor && upRequests.contains(floor) ||
            floor < currentFloor && downRequests.contains(floor)) {
            System.out.println("Request for floor " + floor + " already exists.");
            return;
        }

        if (floor > currentFloor) {
            upRequests.add(floor);
        } else {
            downRequests.add(floor);
        }

        System.out.println("Floor " + floor + " request added.");
    }

    @Override
    public void runElevator() {
        while (!upRequests.isEmpty() || !downRequests.isEmpty()) {
            if (movingUp) {
                processRequests(upRequests, true);
                movingUp = false; 
            } else {
                processRequests(downRequests, false);
                movingUp = true; 
            }
        }
        System.out.println("No more requests. Elevator is idle at floor " + currentFloor);
    }

    private void processRequests(TreeSet<Integer> requests, boolean goingUp) {
        Iterator<Integer> it = requests.iterator();
        while (it.hasNext()) {
            int targetFloor = it.next();
            moveToFloor(targetFloor, goingUp);
            it.remove();
        }
    }

    private void moveToFloor(int targetFloor, boolean goingUp) {
        System.out.println("Moving " + (goingUp ? "UP" : "DOWN") + " from floor " + currentFloor);
        while (currentFloor != targetFloor) {
            currentFloor += goingUp ? 1 : -1;
            System.out.println("Currently at floor " + currentFloor);
            try {
                Thread.sleep(2000); // Simulate time to move to next floor
            } catch (InterruptedException e) {
                System.out.println("Movement interrupted: " + e.getMessage());
            }
        }
        System.out.println("Stopped at floor " + currentFloor + " (Doors opening for 2 seconds...)");
        try {
            Thread.sleep(2000); 
        } catch (InterruptedException e) {
            System.out.println("Pause interrupted: " + e.getMessage());
        }
    }
}


public class ElevatorSystemSimulation {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Elevator elevator = new Elevator(10); 

        System.out.println("=== Elevator System Simulation ===");
        System.out.println("Enter floor requests (0 to 10). Type -1 to start elevator.");

        while (true) {
            System.out.print("Request floor: ");
            int floor = scanner.nextInt();
            if (floor == -1) break;
            elevator.requestFloor(floor);
        }

        elevator.runElevator();
    }
}
