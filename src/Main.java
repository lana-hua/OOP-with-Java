import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Vehicle Management System is running.");

        while (true){
            String input = scanner.nextLine();

            if (input.equals("Q")) {
                System.out.println("Vehicle Management System is terminated.");
                break; // Exit the loop
            }
        }

    }
}
