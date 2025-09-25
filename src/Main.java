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

    public static void command_check(String command, String[] data_token) {
        switch (command) {
            case "A" -> addVehicle(data_token);
            case "PF" -> System.out.println(fleet);
            case "Q" -> quit();
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


    public static void quit(){
        System.out.println("Vehicle Management System is terminated.");
        System.exit(0);
    }

}

