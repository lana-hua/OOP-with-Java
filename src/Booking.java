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

    public static boolean isValidBookingDate(String[] dataToken) {
        Date begin = new Date(dataToken[1]);
        Date end = new Date(dataToken[2]);

        if (!begin.isBookingDateValid("begin", begin)) {
            return false;
        } else if (begin.isTodayOrFuture()) {
            Frontend.printBeginErrorMessage("Today or Future Error", begin);
            return false;
        } else if (!begin.isWithin3Months()) {
            Frontend.printBeginErrorMessage("Beyond 3 Months Error", begin);
            return false;
        } else if (!end.isBookingDateValid("end", end)) {
            return false;
        } else if (end.compareTo(begin) < 0) {
            Frontend.printEndErrorMessage("Equal to or Later Error", begin, end);
        }
        return true;
//        else if (begin.isWithin3Months()) return false;
//        else if (!end.isValid() || end.compareTo(begin) > 0) { return false;}
//        else {return true;}
    }




    public String validateBookingDate(){
        if (!begin.isValid()){
            return (begin + " - beginning date is not a valid calendar date.");
        }

        if (begin.isTodayOrFuture()){
            return (begin + " - beginning date is not today or a future date.");
        }

        if (!end.isValid()){
            return (end + " - ending date is not a valid calendar date.");
        }

        if (end.isTodayOrFuture()){
            return (end + " - ending date is not today or a future date.");
        }

        if (end.compareTo(begin) < 0){
            return (end + " - ending date must be equal or after the beginning date " + begin);
        }

        if (!begin.isWithin3Months()){
            return (begin + " - beginning date beyond 3 months.");
        }

//        if (!isWithin7Days()){
//            return (begin + " ~ " + end  + " - duration more than a week.");
//        }

        return null;
    }


    public boolean isEmployeeConflict(){
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