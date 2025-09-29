/**
 The Reservation class manages a collection of bookings with an array.
 It allows the user to search, resize, add, remove, and match bookings in the list.
 It also allows the user to check for conflicts and print for bookings.
 @author Sharon Chen
 */

import java.util.Calendar;

public class Reservation {
    private Booking[] bookings;
    private int size;

    private static final int CAPACITY = 4; //initial capacity
    private static final int NOT_FOUND = -1;

    //Constructs an empty array of bookings for Reservation
    public Reservation() {
        bookings = new Booking[CAPACITY];
        size = 0;
    }

    //Returns the size of the bookings array
    public int getSize(){
        return size;
    }

    //Searches for a specific booking and returns the index if found and NOT_FOUND if not
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

    //Increases the capacity of the bookings array by 4 if the list is full
    private void grow() {
        Booking[] newArray = new Booking[size+4];

        if (size >= 0) System.arraycopy(bookings, 0, newArray, 0, size);

        bookings = newArray;
    } //resize the array

    //Adds a new booking to the end of the bookings array and grows the list if necessary
    public void add(Booking booking) {
        if (size == bookings.length){
            grow();
        }

        bookings[size] = booking;
        size++;

    } //add to end of array

    //Deletes a specific booking from the bookings array
    public void remove(Booking booking) {
        if (contains(booking)){
            int index = find(booking);
            bookings[index] = bookings[size-1];
            bookings[size-1] = null;
            size--;
        }
    } //overwrite with last element

    //Checks if a specific booking exists in the bookings array and returns true if it exists and false if not
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

    public boolean isVehicleBooked(String plate) {
        for (int i = 0; i < Frontend.bookings.getSize(); i++) {
            if (Frontend.bookings.bookings[i].getVehicle().getPlate().equals(plate)) {
                return true;
            }
        }
        return false;
    }

    //given ending date, plate, find if already in booking, return the booking
    //Searches for a booking given the end date and license plate
    public Booking findBookingForReturnVehicle(Date end, String plate){
        for (int i = 0; i < Frontend.bookings.getSize(); i++) {
            if ((Frontend.bookings.bookings[i].getEnd().equals(end) && (Frontend.bookings.bookings[i].getVehicle().getPlate().equals(plate)))) {
                return Frontend.bookings.bookings[i];
            }
        }
        return null;
    }

    //given begin, ending date, plate, find if already in booking, return the booking
    public Booking findBookingForCancelBooking(Date begin, Date end, String plate){
        for (int i = 0; i < Frontend.bookings.getSize(); i++) {
            if ((Frontend.bookings.bookings[i].getBegin().equals(begin)) && (Frontend.bookings.bookings[i].getEnd().equals(end) && (Frontend.bookings.bookings[i].getVehicle().getPlate().equals(plate)))) {
                return Frontend.bookings.bookings[i];
            }
        }
        return null;
    }

    public boolean isVehicleConflict(Date begin, Date end, String plate) {
        Calendar testStart = Calendar.getInstance();
        testStart.set(Calendar.DAY_OF_MONTH, begin.getDay());
        testStart.set(Calendar.MONTH, begin.getMonth() - 1);
        testStart.set(Calendar.YEAR, begin.getYear());

        Calendar testEnd = Calendar.getInstance();
        testEnd.set(Calendar.DAY_OF_MONTH, end.getDay());
        testEnd.set(Calendar.MONTH, end.getMonth() - 1);
        testEnd.set(Calendar.YEAR, end.getYear());

        Calendar bookingStart = Calendar.getInstance();
        Calendar bookingEnd = Calendar.getInstance();

        for (int i = 0; i < Frontend.bookings.getSize(); i++) {
            if (Frontend.bookings.bookings[i].getVehicle().getPlate().equalsIgnoreCase(plate)) {
                bookingStart.set(Calendar.DAY_OF_MONTH, Frontend.bookings.bookings[i].getBegin().getDay());
                bookingStart.set(Calendar.MONTH, Frontend.bookings.bookings[i].getBegin().getMonth() - 1);
                bookingStart.set(Calendar.YEAR, Frontend.bookings.bookings[i].getBegin().getYear());

                bookingEnd.set(Calendar.DAY_OF_MONTH, Frontend.bookings.bookings[i].getEnd().getDay());
                bookingEnd.set(Calendar.MONTH, Frontend.bookings.bookings[i].getEnd().getMonth() - 1);
                bookingEnd.set(Calendar.YEAR, Frontend.bookings.bookings[i].getEnd().getYear());

                boolean isStartWithinRange = testStart.after(bookingStart) && testStart.before(bookingEnd);
                boolean isEndWithinRange = testEnd.after(bookingStart) && testEnd.before(bookingEnd);
                boolean isBookedStartWithinRange = bookingStart.after(testStart) && bookingStart.before(testEnd);
                boolean isBookedEndWithinRange = bookingEnd.after(testStart) && bookingEnd.before(testEnd);
                boolean isSame = testStart.equals(bookingStart) || testEnd.equals(bookingEnd) || testEnd.equals(bookingStart) || testStart.equals(bookingEnd);

                if ((isStartWithinRange) || (isEndWithinRange) || (isBookedStartWithinRange) || (isBookedEndWithinRange) || (isSame)) {
                    return true;
                }
            }
        }
        return false;
    }

    public Date isEmployeeConflict(Date begin, Date end, String employee) {
        Calendar testStart = Calendar.getInstance();
        testStart.set(Calendar.DAY_OF_MONTH, begin.getDay());
        testStart.set(Calendar.MONTH, begin.getMonth() - 1);
        testStart.set(Calendar.YEAR, begin.getYear());

        Calendar testEnd = Calendar.getInstance();
        testEnd.set(Calendar.DAY_OF_MONTH, end.getDay());
        testEnd.set(Calendar.MONTH, end.getMonth() - 1);
        testEnd.set(Calendar.YEAR, end.getYear());

        Calendar bookingStart = Calendar.getInstance();
        Calendar bookingEnd = Calendar.getInstance();

        for (int i = 0; i < Frontend.bookings.getSize(); i++) {
            if (Frontend.bookings.bookings[i].getEmployee().name().equalsIgnoreCase(employee)) {
                bookingStart.set(Calendar.DAY_OF_MONTH, Frontend.bookings.bookings[i].getBegin().getDay());
                bookingStart.set(Calendar.MONTH, Frontend.bookings.bookings[i].getBegin().getMonth() - 1);
                bookingStart.set(Calendar.YEAR, Frontend.bookings.bookings[i].getBegin().getYear());

                bookingEnd.set(Calendar.DAY_OF_MONTH, Frontend.bookings.bookings[i].getEnd().getDay());
                bookingEnd.set(Calendar.MONTH, Frontend.bookings.bookings[i].getEnd().getMonth() - 1);
                bookingEnd.set(Calendar.YEAR, Frontend.bookings.bookings[i].getEnd().getYear());

                boolean isStartWithinRange = testStart.after(bookingStart) && testStart.before(bookingEnd);
                boolean isEndWithinRange = testEnd.after(bookingStart) && testEnd.before(bookingEnd);
                boolean isBookedStartWithinRange = bookingStart.after(testStart) && bookingStart.before(testEnd);
                boolean isBookedEndWithinRange = bookingEnd.after(testStart) && bookingEnd.before(testEnd);
                boolean isBeginSame = testStart.equals(bookingStart);
                boolean isEndSame = testEnd.equals(bookingEnd);

                if ((isStartWithinRange) || (isEndWithinRange) || (isBookedStartWithinRange) || (isBookedEndWithinRange) || (isBeginSame) || (isEndSame)) {
                    return Frontend.bookings.bookings[i].getBegin();
                }
            }
        }
        return null;
    }


    //given ending date find the earliest end date, if matches return true else return false
    public boolean isReturnEarliestEnd (Date returnDate){
        Date earliestDate = Frontend.bookings.bookings[0].getEnd();
        for (int i = 1; i < Frontend.bookings.getSize(); i++) {
            if (Frontend.bookings.bookings[i].getEnd().compareTo(earliestDate) < 1) {
                earliestDate = Frontend.bookings.bookings[i].getEnd();
            }
        }
        return returnDate.compareTo(earliestDate) == 0;
    }

    //PR Command
    public void printByVehicle() {
        if (size == 0) {
            System.out.println("There is no booking record.");
            return;
        }

        //sorting by Vehicles and Begin Dates in order
        for (int i = 0; i < (size - 1); i++) {
            for (int j = 0; j < (size - i - 1); j++) {
                String plate1 = bookings[j].getVehicle().getPlate();
                String plate2 = bookings[j + 1].getVehicle().getPlate();

                if (plate1.compareTo(plate2) > 0) {
                    Booking temp = bookings[j];
                    bookings[j] = bookings[j + 1];
                    bookings[j + 1] = temp;
                }
                else if (plate1.compareTo(plate2) == 0) {
                    Date begin1 = bookings[j].getBegin();
                    Date begin2 = bookings[j + 1].getBegin();
                    //sort begin dates in vehicles
                    if (begin1.compareTo(begin2) > 0) {
                        Booking temp = bookings[j];
                        bookings[j] = bookings[j + 1];
                        bookings[j + 1] = temp;
                    }
                }
            }
        }
        //prints out sorted list
        System.out.println("*List of reservations ordered by license plate number and beginning date.");
        for (int i = 0; i < size; i++) {
            System.out.println(bookings[i].toString());
        }
        System.out.println("*end of list.\n");
    } //ordered by plate then beginning date

    //PD Command
    public void printByDept() {
        if (size == 0) {
            System.out.println("There is no booking record.");
            return;
        }
        //sorting by Department and Employees in order
        for (int i = 0; i < (size - 1); i++) {
            for (int j = 0; j < (size - i - 1); j++) {
                String dept1 = bookings[j].getEmployee().getDepartment();
                String dept2 = bookings[j + 1].getEmployee().getDepartment();
                //sort departments
                if (dept1.compareTo(dept2) > 0) {
                    Booking temp = bookings[j];
                    bookings[j] = bookings[j + 1];
                    bookings[j + 1] = temp;
                } else if (dept1.compareTo(dept2) == 0) {
                    String emp1 = bookings[j].getEmployee().name();
                    String emp2 = bookings[j + 1].getEmployee().name();
                    //sort employees in department
                    if (emp1.compareTo(emp2) > 0) {
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
        for (int i = 0; i < size; i++) {
            String bookingDept = bookings[i].getEmployee().getDepartment();
            if (!bookingDept.equals(currentDept)) {
                currentDept = bookingDept;
                System.out.println("--" + currentDept + "--");
            }
            System.out.println(bookings[i].toString());
        }
        System.out.println("*end of list.\n");
    } //ordered by department then by employee
}
