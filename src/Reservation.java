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

    private static final int CAPACITY = 4; //initial capacity
    private static final int NOT_FOUND = -1;

    private int find(Booking booking) {
        if (contains(booking)) {
            for (int i = 0; i < size; i++){
                if (bookings[i].equals(booking)){
                    return i;
                }
            }
        }
        return NOT_FOUND;
    } //search the given booking

    private void grow() {
        Booking[] newArray = new Booking[size+4];

        if (size >= 0) System.arraycopy(bookings, 0, newArray, 0, size);

        bookings = newArray;
    } //resize the array

    public void add(Booking booking) {
        if (size % 4 == 0){
            grow();
            bookings[size] = booking;
        }
        else {
            bookings[size] = booking;
            size++;
        }
    } //add to end of array

    public void remove(Booking booking) {

    } //overwrite with last element

    public boolean contains(Booking booking) {

    }

    public void printByVehicle(){

    } //ordered by plate then beginning date

    public void printByDept() {

    } //ordered by department then by employee
}
