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
        String invalidDateMessage = dateInput + " - invalid calendar date.";
        System.out.println(invalidDateMessage);
    }

    public static void printTodayOrFuture(String dateInput) {
        String invalid_command = dateInput + " - is today or a future date.";
        System.out.println(invalid_command);
    }

    public static void printInvalidEmployeeMessage(String employee) {
        String invalidEmployeeMessage = employee + " - not an eligible employee to book.";
        System.out.println(invalidEmployeeMessage);
    }

    public static void printVehicleConflictMessage(String plate, Date begin, Date end) {
        String vehicleConflictMessage = plate + " - booking with " + begin + " ~ " + end + " not available.";
        System.out.println(vehicleConflictMessage);
    }

    public static void printEmployeeConflictMessage(String employee, Date begin) {
        String employeeConflictMessage = employee + " - has an existing booking conflicting with the beginning date " + begin;
        System.out.println(employeeConflictMessage);
    }

    public static void printInvalidBookingDate(Booking booking) {
        String invalidBookingDate = booking.validateBookingDate();
        System.out.println(invalidBookingDate);
    }

    public static void bookVehicle(String[] dataToken) {
        Date begin = new Date(dataToken[1]);
        Date end = new Date(dataToken[2]);
        String plate = dataToken[3];
        String employeeName = dataToken[4];

        if(begin.isValid())

        /**Booking tempBooking = new Booking(begin, end, null, null);
        if(tempBooking.validateBookingDate() != null){
            printInvalidBookingDate(tempBooking);
            return;
        }
        else{
            Vehicle tempVehicle = new Vehicle(plate, null, null, 0);
            if(!fleet.contains(tempVehicle)){
                printNotInFleetMessage(tempVehicle);
                return;
            }
            else{
                if(bookings.isVehicleConflict(begin, end, plate)){
                    printVehicleConflictMessage(plate, begin, end);
                    return;
                }
                else {
                    if (!isValidEmployee(employeeName)){
                        printInvalidEmployeeMessage(employeeName);
                        return;
                    }
                    else {
                        if (bookings.isEmployeeConflict(begin, end, employeeName) != null) {
                            printEmployeeConflictMessage(employeeName, bookings.isEmployeeConflict(begin, end, employeeName));
                            return;
                        } else {
                            tempBooking = null;
                            tempVehicle = null;
                            Booking newBooking = new Booking(begin, end, plate, employeeName);
                        }
                    }
                }
            }
        }**/
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
