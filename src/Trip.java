/**
 First, a single, very descriptive sentence describing the class.
 Then, additional lines of description are added to elaborate on the
 details if necessary.
 @author Sharon Chen
 */

public class Trip {
    private Booking booking;
    private int beginMileage;
    private int endMileage;

    public Trip (Booking booking, int beginMileage, int endMileage){
        this.booking = booking;
        this.beginMileage = beginMileage;
        this.endMileage = endMileage;
    }

    public Booking getBooking(){
        return booking;
    }

    public int getBeginMileage() {
        return beginMileage;
    }

    public int getEndMileage(){
        return endMileage;
    }

    @Override
    public boolean equals(Object comparison) {
        if (this == comparison) {
            return true;
        }
        if ((comparison == null) || (this.getClass() != comparison.getClass())) {
            return false;
        }

        Trip compareTrip = (Trip) comparison;
        return ((this.booking == compareTrip.booking) && (this.beginMileage == compareTrip.beginMileage) && (this.endMileage == compareTrip.endMileage));
    }

    // 58718D 10/15/2025 ~ 10/15/2025 original mileage: 64390 current mileage: 64500 mileage used: 110

    @Override
    public String toString(){
        int mileageUsed = endMileage - beginMileage;
        return (booking.getVehicle().getPlate() + " " + booking.getBegin() + " ~ " + booking.getEnd() + " original mileage: " + beginMileage + " current mileage: " + endMileage + " mileage used: " + mileageUsed);
    }
}