/**
 First, a single, very descriptive sentence describing the class.
 Then, additional lines of description are added to elaborate on the
 details if necessary.
 @author Sharon Chen
 */

import java.util.Calendar;

public class Booking {
    private Date begin;
    private Date end;
    private Vehicle vehicle;
    private Employee employee;

    public Booking(Date begin, Date end, Vehicle vehicle, Employee employee){
        this.begin = begin;
        this.end = end;
        this.vehicle = vehicle;
        this.employee = employee;
    }

    public Date getBegin(){
        return begin;
    }

    public Date getEnd(){
        return end;
    }

    public Vehicle getVehicle(){
        return vehicle;
    }

    public Employee getEmployee(){
        return employee;
    }
//The license plate number does not exist in the fleet.
//6. The vehicle associated with the license plate number is not available for the dates entered.
//7. The employee is not eligible to book a vehicle.
//8. The employee has an existing booking conflicting with the dates entered.

//     switch (errorType) {
//            case "Vehicle does not Exist Error" -> System.out.println(plate + " is not in the fleet.");
//            case "Vehicle not Available Error" -> System.out.println(plate + " - booking with " + begin + " ~ " + end + " not available.");
//            case "Employee not Eligible Error" -> System.out.println(employee + " - not an eligible employee to book.");
//            case "Employee Conflict Error" -> System.out.println(employee + " - has an existing booking conflicting with the beginning date " + begin);
//        }
    public static boolean isValidBooking(String[] dataToken) {
        String plate = dataToken[3];
        Vehicle vehicle = new Vehicle(plate);
        String employee = dataToken[4];
        Date begin = new Date(dataToken[1]);
        Date end = new Date(dataToken[2]);

        if (!Frontend.fleet.contains(vehicle)) {
            Frontend.printInvalidBookingMessage("Vehicle does not Exist Error", vehicle.getPlate(), null, null, null);
            return false;
        } else if (Frontend.bookings.isVehicleConflict(begin, end, plate)) {
            Frontend.printInvalidBookingMessage("Vehicle not Available Error", plate, null, begin, end);
            return false;
        } else if (!Employee.isValidEmployee(employee)) {
            Frontend.printInvalidBookingMessage("Employee not Eligible Error", null, employee, null,null);
            return false;
        } else if (Frontend.bookings.isEmployeeConflict(begin, end, employee) != null) {
            Frontend.printInvalidBookingMessage("Employee Conflict Error", null, employee, begin, end);
            return false;
        }
        return true;
    }

    public static boolean isValidBookingDate(String[] dataToken) {
        Date begin = new Date(dataToken[1]);
        Date end = new Date(dataToken[2]);

        if (!begin.isBookingDateValid("begin", begin)) {
            return false;
        } else if (!begin.isTodayOrFuture()) {
            Frontend.printBeginErrorMessage("Today or Future Error", begin);
            return false;
        } else if (!begin.isWithin3Months()) {
            Frontend.printBeginErrorMessage("Beyond 3 Months Error", begin);
            return false;
        } else if (!end.isBookingDateValid("end", end)) {
            return false;
        } else if (end.compareTo(begin) < 0) {
            Frontend.printEndErrorMessage("Equal to or Later Error", begin, end);
            return false;
        } else if (!end.isWithin7Days(begin, end)) {
            Frontend.printEndErrorMessage("More than a Week Error", begin, end);
            return false;
        }
        return true;
    }


    @Override
    public boolean equals(Object comparison) {
        if (this == comparison) {
            return true;
        }
        if ((comparison == null) || (this.getClass() != comparison.getClass())) {
            return false;
        }

        Booking compareBooking = (Booking) comparison;
        return ((this.begin == compareBooking.begin) && (this.end == compareBooking.end) && (this.vehicle == compareBooking.vehicle) && (this.employee == compareBooking.employee));
    }

    //67359S:FORD:11/1/2019 [mileage:59644] [beginning 10/31/2025 ending 11/2/2025:KAUR]
    @Override
    public String toString(){
        return (vehicle.toString() + " [beginning " + begin + " ending " + end + ":" + employee + "]");
    }

}