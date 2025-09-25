import java.util.Objects;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.InputMismatchException;


public class Main {
    static ArrayList<Vehicle> fleet = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //commands = {"A", ""}
        System.out.println("Vehicle Management System is running.");

        while (true) {
            if (!scanner.hasNextLine()) {
                break;  // no more input
            }

            String input = scanner.nextLine().trim();

            if (input.isEmpty()) {
                continue; // ignore blank lines
            }

            if (input.equalsIgnoreCase("exit")) {
                System.out.println("Exiting system.");
                break;
            }

            String[] data_token = input.split("\\s+");
            String command = data_token[0];

            command_check(command, data_token);
        }

        scanner.close();
    }

    public static void command_check(String command, String[] data_token) {
        //System.out.println(command);
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
    //Check date
    //check make
    //check Mileage
    //check if already in fleet
    //add to fleet

    public static void addVehicle(String[] data_token) {
        String plate = data_token[1];
        String obtained = data_token[2];
        Make make = Make.valueOf(data_token[3].toUpperCase());

        //Check make
        if (!isValidMake(data_token[3].toUpperCase())){
            String invalid_make = data_token[3] + " - invalid command!";
            System.out.println(invalid_make);
            return;
        }
        int mileage = Integer.parseInt(data_token[4]);
        Vehicle new_vehicle = new Vehicle(plate, obtained, make, mileage);

        fleet.add(new_vehicle);

        String addVehicle_confirmation = new_vehicle.toString() + " has been added to the fleet.";
        System.out.println(addVehicle_confirmation);
    }
    public static boolean isValidMake(String make){
        switch (make) {
            case "HONDA", "CHEVY", "TOYOTA", "FORD" -> {
                return true;
            }
            default -> {
                return false;
            }
        }
    }

    public static void quit(){
        System.out.println("Vehicle Management System is terminated.");
        System.exit(0);
    }

}
