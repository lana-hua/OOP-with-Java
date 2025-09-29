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
            case "PR" -> bookings.printByVehicle(); //needs to be ordered by plate then beginning date
            case "PD" -> bookings.printByDept();//needs to be ordered by department then by employee
            case "PT" -> tripList.print();//needs to be ordered by ending date
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
        if(!bookings.isVehicleBooked(plate)) {
            fleet.remove(temp);
        } else {
            System.out.println(plate + " - has existing bookings; cannot be removed.");
        }

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

    public static void printInvalidDate(String date){
        String invalidDateMessage = date + " - invalid calendar date.";
        System.out.println(invalidDateMessage);
    }

    public static void printTodayOrFuture(String date) {
        String invalid_command = date + " - is today or a future date.";
        System.out.println(invalid_command);
    }

    public static void bookVehicle(String[] dataToken) {

        if (Booking.isValidBookingDate(dataToken) && Booking.isValidBooking(dataToken)) {
            Date begin = new Date(dataToken[1]);
            Date end = new Date(dataToken[2]);
            String plate = dataToken[3];
            Vehicle vehicle = fleet.getVehicle(plate);
            Employee employeeName = Employee.valueOf(dataToken[4].substring(0, 1).toUpperCase() + dataToken[4].toLowerCase().substring(1));


            Booking newBooking = new Booking(begin, end, vehicle, employeeName);
            bookings.add(newBooking);

            System.out.println(newBooking.toString() + " booked.");
        } else {return;}
    }

    public static void printBeginDateErrorMessage(String errorType, Date begin) {
        switch (errorType) {
            case "Valid Error" -> System.out.println(begin + " - beginning date is not a valid calendar date.");
            case "Today or Future Error" -> System.out.println(begin + " - beginning date is not today or a future date.");
            case "Beyond 3 Months Error" -> System.out.println(begin + " - beginning date beyond 3 months.");
        }
    }

    public static void printEndDateErrorMessage(String errorType, Date begin, Date end) {
        switch (errorType) {
            case "Valid Error" -> System.out.println(begin + " - ending date is not a valid calendar date.");
            case "Equal to or Later Error" -> System.out.println(end + " - ending date must be equal or after the beginning date " + begin);
            case "More than a Week Error" -> System.out.println(begin + " ~ " + end + " - duration more than a week.");
        }
    }

    public static void printInvalidBookingMessage(String errorType, String plate, String employee, Date begin, Date end) {
        switch (errorType) {
            case "Vehicle does not Exist Error" -> System.out.println(plate + " is not in the fleet.");
            case "Vehicle not Available Error" -> System.out.println(plate + " - booking with " + begin + " ~ " + end + " not available.");
            case "Employee not Eligible Error" -> System.out.println(employee + " - not an eligible employee to book.");
            case "Employee Conflict Error" -> System.out.println(employee + " - has an existing booking conflicting with the beginning date " + begin);
        }
    }

    public static void printValidCancelBookingMessage(Date begin, Date end, String plate) {
        String validCancelBookingMessage = plate + ":" + begin + " ~ " + end + " has been canceled.";
        System.out.println(validCancelBookingMessage);
    }

    public static void printInvalidCancelBookingMessage(Date begin, Date end, String plate) {
        String invalidCancelBookingMessage = plate + ":" +begin + " ~ " + end + " - cannot find the booking.";
        System.out.println(invalidCancelBookingMessage);
    }

    public static void cancelBooking(String[] dataToken) {
        Date begin = new Date(dataToken[1]);
        Date end = new Date(dataToken[2]);
        String plate = dataToken[3];

        if (!begin.isValid() || !begin.isTodayOrFuture() || !end.isValid() || !end.isTodayOrFuture()){
            printInvalidDate(plate);
            return;
        }
        if (bookings.findBookingForCancelBooking(begin, end, plate) == null){
            printInvalidCancelBookingMessage(begin, end, plate);
            return;
        }
        else if (bookings.findBookingForCancelBooking(begin, end, plate) != null){
            bookings.remove(bookings.findBookingForCancelBooking(begin, end, plate));
            printValidCancelBookingMessage(begin, end, plate);
        }

    }

    public static void returnVehicle(String[] dataToken) {
        Date returnDate = new Date(dataToken[1]);
        String plate = dataToken[2];
        int mileage = Integer.parseInt(dataToken[3]);

        if (bookings.findBookingForReturnVehicle(returnDate, plate) == null){
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

            System.out.println("Trip completed: " + newTrip.toString());
        }
    }

    public static void quit() {
        System.out.println("\nVehicle Management System is terminated.");
        System.exit(0);
    }

}
