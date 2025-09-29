import java.util.Scanner;

public class Frontend {
  static Fleet fleet = new Fleet();
  static Reservation bookings = new Reservation();
  static TripList tripList = new TripList();

    public static void run() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Vehicle Management System is running.\n");

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

            checkCommand(command, dataToken);
        }

        scanner.close();
    }

    public static void checkCommand(String command, String[] dataToken) {
        switch (command) {
            case "A" -> addVehicle(dataToken);
            case "D" -> removeVehicle(dataToken);
            case "B" -> bookVehicle(dataToken);
            case "C" -> cancelBooking(dataToken);
            case "R" -> returnVehicle(dataToken);
            case "PF" -> fleet.printByMake();//needs to be ordered by make then date
            case "PR" -> System.out.println(bookings);//needs to be ordered by plate then beginning date
            case "PD" -> System.out.println(bookings);//needs to be ordered by department then by employee
            case "PT" -> System.out.println(tripList);//needs to be ordered by ending date
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

    public static void printInvalidMileageMessage(int mileage) {
        String invalidMileageMessage = mileage + " - invalid mileage.";
        System.out.println(invalidMileageMessage);
    }

    public static void printInvalidMakeMessage(String make) {
        String invalidMake = make + " - invalid make.";
        System.out.println(invalidMake);
    }

    public static void removeVehicle(String[] dataToken) {
        String plate = dataToken[1];
        Vehicle temp = new Vehicle(plate);
        fleet.remove(temp);
    }

    public static void printNotInFleetMessage(Vehicle vehicle) {
        String notInFleetMessage = vehicle.getPlate() + " is not in the fleet.";
        System.out.println(notInFleetMessage);
    }

    public static void printRemovedVehicleMessage(Vehicle vehicle) {
        String removedMessage = vehicle.toString() + " has been removed from the fleet.";
        System.out.println(removedMessage);
    }

    public static void printNoVehicleInFleet() {
        System.out.println("There is no vehicle in the fleet.");
    }

    public static void printInvalidDate(String dateInput){
        String invalid_command = dateInput + " - invalid calendar date.";
        System.out.println(invalid_command);
    }

    public static void printTodayOrFuture(String dateInput) {
        String invalid_command = dateInput + " - is today or a future date.";
        System.out.println(invalid_command);
    }

    public static void printInvalidEmployeeMessage(String employee) {
        String invalidEmployeeMessage = employee + " - not an eligible employee to book.";
        System.out.println(invalidEmployeeMessage);
    }

    public static void bookVehicle(String[] dataToken) {
        if (Booking.isValidBookingDate(dataToken)) {

        } else {return;}
    }

    public static void printBeginErrorMessage(String errorType, Date begin) {
        switch (errorType) {
            case "Valid Error" -> System.out.println(begin + " - beginning date is not a valid calendar date.");
            case "Today or Future Error" -> System.out.println(begin + " - beginning date is not today or a future date.");
            case "Beyond 3 Months Error" -> System.out.println(begin + " - beginning date beyond 3 months.");
        }
    }
    public static void printEndErrorMessage(String errorType, Date begin, Date end) {
        switch (errorType) {
            case "Valid Error" -> System.out.println(begin + " - end date is not a valid calendar date.");
            case "Equal to or Later Error" -> System.out.println(end + " - ending date must be equal or after the beginning date" + begin);
            case "Beyond 3 Months Error" -> System.out.println(begin + " - end date beyond 3 months.");
        }
    }

    public static void cancelBooking(String[] dataToken) {

    }

    //R 8/24/2025 58719D 87170
    public static void returnVehicle(String[] dataToken) {
        Date returnDate = new Date(dataToken[1]);
        String plate = dataToken[2];
        int mileage = Integer.parseInt(dataToken[3]);

        if (!returnDate.isValid() || returnDate.isTodayOrFuture()) {
            return;
        } else if (bookings.findBookingForReturnVehicle(returnDate,plate) == null){
            String cannotFindBookingMessage = plate + " booked with ending date " + returnDate + " - cannot find the booking.";
            System.out.println(cannotFindBookingMessage);
            return;

        } else if (!bookings.isReturnEarliestEnd(returnDate)) {
            String notEarliestEndDateMessage = plate + " booked with ending date " + returnDate + " - returning not in order of ending date.";
            System.out.println(notEarliestEndDateMessage);
            return;

        } else if (!Vehicle.isValidMileage(mileage)) {
            return;

        } else if(bookings.findBookingForReturnVehicle(returnDate,plate).getVehicle().getMileage() >= mileage) {
            String invalidMileageMessage = "Invalid mileage - current mileage: " + bookings.findBookingForReturnVehicle(returnDate,plate).getVehicle().getMileage() + " entered mileage: " + mileage;
            System.out.println(invalidMileageMessage);
            return;

        } else {
            Booking booking = bookings.findBookingForReturnVehicle(returnDate,plate);

            Trip newTrip = new Trip(booking, booking.getVehicle().getMileage(),mileage);
            tripList.add(newTrip);

            booking.getVehicle().setMileage(mileage);
            bookings.remove(booking);

        }
    }


    public static void quit() {
        System.out.println("Vehicle Management System is terminated.");
        System.exit(0);
    }

}
