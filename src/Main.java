import java.util.Scanner;

public class Main {
    static Fleet fleet = new Fleet();
    public static void main(String[] args) {
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
            case "D" -> removeVehicle(dataToken);
            case "B" -> bookVehicle(dataToken);
            case "C" -> cancelBooking(dataToken);
            case "R" -> returnVehicle(dataToken);
            case "Q" -> quit();
            case "PF" -> System.out.println(fleet);//needs to be ordered by make then date
            case "PR" -> System.out.println(reservations);//needs to be ordered by plate then beginning date
            case "PD" -> System.out.println(reservations);//needs to be ordered by department then by employee
            case "PT" -> System.out.println(tripList);//needs to be ordered by ending date
            default -> {
                String invalid_command = command + " - invalid command!";
                System.out.println(invalid_command);
            }
        }
    }

    public static void addVehicle(String[] data_token) {
        String plate = data_token[1];
        Date obtained = new Date(data_token[2]);
        Make make;
        int mileage = Integer.parseInt(data_token[4]);

        //Check date
        if (!obtained.isValid()){
            return;
        }

        if (Make.isValidMake(data_token[3].toUpperCase())) {
            make = Make.valueOf(data_token[3].toUpperCase());;
        }
        else {
            return;
        }

        if (!Vehicle.isValidMileage(mileage)) {
            return;
        }

        Vehicle newVehicle = new Vehicle(plate, obtained, make, mileage);
        fleet.add(newVehicle);

        String vehicleConfirmation = newVehicle.toString() + " has been added to the fleet.";
        System.out.println(vehicleConfirmation);
    }

    public static void removeVehicle(String[] dataToken) {

    }

    public static void bookVehicle(String[] dataToken) {

    }

    public static void cancelBooking(String[] dataToken) {

    }
    public static void returnVehicle(String[] dataToken) {

    }


    public static void quit() {
        System.out.println("Vehicle Management System is terminated.");
        System.exit(0);
    }

}

