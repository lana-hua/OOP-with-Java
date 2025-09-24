import java.util.Scanner;
import java.util.ArrayList;
import java.util.InputMismatchException;


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //commands = {"A", ""}
        System.out.println("Vehicle Management System is running.");

        ArrayList<Vehicle> fleet = new ArrayList<>();

        while (true) {
            try {
                String input = scanner.nextLine();

                String[] data_token = input.split(" ");
                String command = data_token[0];

                command_check(command, data_token);
            } catch (InputMismatchException e) {
                System.err.println("Invalid input.");
                scanner.next(); // Consume the invalid input to prevent infinite loop
            }

        }
    }

    public static void command_check(String command, String[] data_token) {
        if (command.equals("A")) {

        }
        else if (command.equals("Q")) {
            quit();
        }
        else {
            String invalid_command = command + " - invalid command!";
            System.out.println(invalid_command);
        }
    }

    public static void addVehicle() {

    }

    public static void quit(){
        System.out.println("Vehicle Management System is terminated.");
        System.exit(0);
    }

}
