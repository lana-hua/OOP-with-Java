import java.util.Scanner;

public class Frontend {
    static Fleet fleet = new Fleet();
//  static Reservation bookings = new Reservation();
//  static TripList tripList = new TripList();

    public static void run() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Vehicle Management System is running.");

        while (true) {
            String input = scanner.nextLine().trim();

            if (input.isEmpty()) {
                continue;
            }

            if (input.equals("Q")) {
                quit();
                break;
            }

            String[] dataToken = input.split("\\s+");
            String command = dataToken[0];

            command_check(command, dataToken);
        }

        scanner.close();
    }

    public static void command_check(String command, String[] dataToken) {
        switch (command) {
            case "A" -> addVehicle(dataToken);
//            case "D" -> removeVehicle(dataToken);
//            case "B" -> bookVehicle(dataToken);
//            case "C" -> cancelBooking(dataToken);
//            case "R" -> returnVehicle(dataToken);
            case "PF" -> System.out.println(fleet);//needs to be ordered by make then date
//            case "PR" -> System.out.println(bookings);//needs to be ordered by plate then beginning date
//            case "PD" -> System.out.println(bookings);//needs to be ordered by department then by employee
//            case "PT" -> System.out.println(tripList);//needs to be ordered by ending date
            default -> {
                String invalid_command = command + " - invalid command!";
                System.out.println(invalid_command);
            }
        }
    }

    public static void addVehicle(String[] dataToken) {
        if (Vehicle.isValidVehicle(dataToken)) {
            Vehicle newVehicle = new Vehicle(dataToken);
            fleet.add(newVehicle);

            String vehicleConfirmation = newVehicle.toString() + " has been added to the fleet.";
            System.out.println(vehicleConfirmation);
        } else { return; }
    }
//
//    public static void removeVehicle(String[] dataToken) {
//        if (Vehicle.isValidVehicle(dataToken)) {
//            Vehicle removedVehicle = new Vehicle(dataToken);
//            if (fleet.contains(removedVehicle)) {
//                fleet.remove(removedVehicle);
//            }
//        } else { return;}
//    }

//    public static void bookVehicle(String[] dataToken) {
//
//    }
//
//    public static void cancelBooking(String[] dataToken) {
//
//    }
//
//    public static void returnVehicle(String[] dataToken) {
//
//    }


    public static void quit() {
        System.out.println("Vehicle Management System is terminated.");
        System.exit(0);
    }

}
