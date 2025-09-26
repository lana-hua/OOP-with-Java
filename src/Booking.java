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