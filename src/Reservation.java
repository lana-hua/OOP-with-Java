public class Reservation {
    private Booking[] bookings;
    private int size;
    private int find(Booking booking) {} //search the given booking
    private void grow() {} //resize the array
    public void add(Booking booking) {} //add to end of array
    public void remove(Booking booking) {} //overwrite with last element
    public boolean contains(Booking booking) {}
    public void printByVehicle(){} //ordered by plate then beginning date
    public void printByDept() {} //ordered by department then by employee
}
