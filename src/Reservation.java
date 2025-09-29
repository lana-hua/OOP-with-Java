public class Reservation {
    private Booking[] bookings;
    private int size;

    private static final int CAPACITY = 4; //initial capacity
    private static final int NOT_FOUND = -1;

    public Reservation() {
        bookings = new Booking[CAPACITY];
        size = 0;
    }

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
        if (size == bookings.length){
            grow();
        }

        bookings[size] = booking;
        size++;

    } //add to end of array

    public void remove(Booking booking) {
        if (contains(booking)){
            int index = find(booking);
            bookings[index] = bookings[size-1];
            bookings[size-1] = null;
            size--;
        }
    } //overwrite with last element

    public boolean contains(Booking booking) {
        for (int i = 0; i < size; i++){
            if (bookings[i].equals(booking)){
                return true;
            }
        }

        String notInBookings = booking.getVehicle().getPlate() + " cannot find the booking.";
        System.out.println(notInBookings);
        return false;
    }

    //given ending date, plate, find if already in booking, return the booking
    public Booking findBookingForReturnVehicle(Date end, String plate){
        for (int i = 0; i < bookings.length; i++) {
            if (bookings[i].getEnd().equals(end) && (bookings[i].getVehicle().getPlate().equals(plate))) {
                return bookings[i];
            }
        }
        return null;
    }

    //PR Command
    public void printByVehicle(){
        if (size == 0) {
            System.out.println("There is no booking record.");
            return;
        }

        //sorting by Vehicles in order
        for (int i = 0; i < (size-1); i++){
            for (int j = 0; j < (size - i - 1); j++) {
                String plate1 = bookings[j].getVehicle().getPlate();
                String plate2 = bookings[j + 1].getVehicle().getPlate();

                if(plate1.compareTo(plate2) > 0){
                    Booking temp = bookings[j];
                    bookings[j] = bookings[j + 1];
                    bookings[j + 1] = temp;
                }
            }
        }
        //prints out sorted list
        System.out.println("*List of reservations ordered by license plate number and beginning date.");
        for (int i = 0; i < size; i++){
            System.out.println(bookings[i].toString());
        }
        System.out.println("*end of list.");
    } //ordered by plate then beginning date

    //PD Command
    public void printByDept() {
        if (size == 0) {
            System.out.println("There is no booking record.");
            return;
        }
        //sorting by Department and Employees in order
        for (int i = 0; i < (size-1); i++){
            for (int j = 0; j < (size - i - 1); j++) {
                String dept1 = bookings[j].getEmployee().getDepartment();
                String dept2 = bookings[j + 1].getEmployee().getDepartment();
                //sort departments
                if(dept1.compareTo(dept2) > 0){
                    Booking temp = bookings[j];
                    bookings[j] = bookings[j + 1];
                    bookings[j + 1] = temp;
                }
                else if (dept1.compareTo(dept2) == 0) {
                    String emp1 = bookings[j].getEmployee().name();
                    String emp2 = bookings[j + 1].getEmployee().name();
                    //sort employees in department
                    if(emp1.compareTo(emp2) > 0){
                        Booking temp = bookings[j];
                        bookings[j] = bookings[j + 1];
                        bookings[j + 1] = temp;
                    }
                }
            }
        }
        //prints out sorted list
        System.out.println("*List of reservations ordered by department and employee.");
        String currentDept = "";
        for (int i = 0; i < size; i++){
            String bookingDept = bookings[i].getEmployee().getDepartment();
            if(!bookingDept.equals(currentDept)) {
                currentDept = bookingDept;
                System.out.println("--" + currentDept + "--");
            }
            System.out.println(bookings[i].toString());
        }
        System.out.println("*end of list.");
    } //ordered by department then by employee
}
