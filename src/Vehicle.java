public class Vehicle implements Comparable<Vehicle> {
    private String plate; //license plate number
    private Date obtained; //Date class described in the next page
    private Make make; //Make is an enum class
    private int mileage; //current reading on the odometer


    public Vehicle(String plate, Date obtained, Make make, int mileage) {
        this.plate = plate;
        this.obtained = obtained;
        this.make = make;
        this.mileage = mileage;
    }

    //@Override
    //public boolean equals(Vehicle obj) {

    //}

    @Override
    public String toString() {
        return plate + ":" + make + ":" + obtained + " [mileage:" + mileage + "]";
    }

    @Override
    public int compareTo(Vehicle o) {
        return plate.compareTo(o.plate);
    }
}