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

    public boolean isWithin3Months(){
        Calendar today = Calendar.getInstance();
        Date todaysDate = new Date((today.get(Calendar.MONTH)+1), (today.get(Calendar.DAY_OF_MONTH)), (today.get(Calendar.YEAR)));

        Calendar threeMonthsLater = (Calendar) today.clone();
        threeMonthsLater.add(Calendar.MONTH, 3);
        Date threeMonthsLaterDate = new Date((threeMonthsLater.get(Calendar.MONTH)+1), (threeMonthsLater.get(Calendar.DAY_OF_MONTH)), (threeMonthsLater.get(Calendar.YEAR)));

        if ((begin.compareTo(todaysDate) >= 0) && (begin.compareTo(threeMonthsLaterDate) <= 0)){
            return true;
        }
        else{
            return false;
        }
    }

    public boolean isWithin7Days(){
        Calendar bookingStart = Calendar.getInstance();
        bookingStart.set(Calendar.DAY_OF_MONTH, this.begin.getDay());
        bookingStart.set(Calendar.MONTH, this.begin.getMonth() - 1);
        bookingStart.set(Calendar.YEAR, this.begin.getYear());

        Calendar bookingEnd = Calendar.getInstance();
        bookingEnd.set(Calendar.DAY_OF_MONTH, this.end.getDay());
        bookingEnd.set(Calendar.MONTH, this.end.getMonth() - 1);
        bookingEnd.set(Calendar.YEAR, this.end.getYear());

        long durationMsec = bookingEnd.getTimeInMillis() - bookingStart.getTimeInMillis();
        long durationDays = durationMsec / (1000 * 60 * 60 * 24);

        if ((durationDays >= 0) && (durationDays <=7)){
            return true;
        }
        else{
            return false;
        }
    }

    public String validateBooking(){
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

        return null;
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