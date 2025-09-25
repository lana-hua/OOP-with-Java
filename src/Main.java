import java.util.Scanner;
import java.util.ArrayList;



public class Main {
    static ArrayList<Vehicle> fleet = new ArrayList<>();
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

    //DATA TOKEN : A LiscenseNum Date Make Odometer
    //
    //check date
    //check make
    //check mileage
    //check if already in fleet
    //add to fleet

    public static void addVehicle(String[] data_token) {
        String plate = data_token[1];
        Date obtained = new Date(data_token[2]);
        Make make;
        int mileage = Integer.parseInt(data_token[4]);

        //Check date
        if (!obtained.isValid()){
            return;
        }

        if (isValidMake(data_token[3].toUpperCase())) {
            make = Make.valueOf(data_token[3].toUpperCase());;
        }
        else {
            return;
        }

        if (!isValidMileage(mileage)) {
            return;
        }

        Vehicle newVehicle = new Vehicle(plate, obtained, make, mileage);

        fleet.add(newVehicle);

        String vehicleConfirmation = newVehicle.toString() + " has been added to the fleet.";
        System.out.println(vehicleConfirmation);
    }

    public static boolean isValidMake(String make) {
        switch (make) {
            case "HONDA", "CHEVY", "TOYOTA", "FORD" -> {
                return true;
            }
            default -> {
                String invalidMake = make + " - invalid make!";
                System.out.println(invalidMake);
                return false;
            }
        }
    }

    public static boolean isValidMileage(int mileage) {
        if (mileage > 0) {
            return true;
        }
        else {
            String invalidMileage = mileage + " - invalid mileage!";
            System.out.println(invalidMileage);
            return false;
        }
    }

    public static void quit(){
        System.out.println("Vehicle Management System is terminated.");
        System.exit(0);
    }

}