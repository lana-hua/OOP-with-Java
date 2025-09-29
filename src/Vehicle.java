/**
 First, a single, very descriptive sentence describing the class.
 Then, additional lines of description are added to elaborate on the
 details if necessary.
 @author Lana Huang
 */
public class Vehicle implements Comparable<Vehicle> {
    private String plate; //license plate number
    private Date obtained; //Date class described in the next page
    private Make make; //Make is an enum class
    private int mileage; //current reading on the odometer

    public Vehicle(String plate) {
        this.plate = plate;
        this.obtained = null;
        this.make = null;
        this.mileage = 0;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public String getPlate() {
        return plate;
    }

    public Make getMake() {
        return make;
    }

    public Date getDate() {
        return obtained;
    }

    /**
     *
     * @param plate
     * @param obtained
     * @param make
     * @param mileage
     */
    public Vehicle(String plate, Date obtained, Make make, int mileage) {
        this.plate = plate;
        this.obtained = obtained;
        this.make = make;
        this.mileage = mileage;
    }

    /**
     *
     * @param dataToken
     */
    public Vehicle (String[] dataToken) {
        if (Vehicle.isValidVehicle(dataToken)){
            this.plate = dataToken[1];
            this.obtained = new Date(dataToken[2]);
            this.make = Make.valueOf(dataToken[3].toUpperCase());
            this.mileage = Integer.parseInt(dataToken[4]);
        }
    }

    /**
     *
     * @param dataToken
     * @return
     */
    public static boolean isValidVehicle(String[] dataToken) {
        Date obtained = new Date(dataToken[2]);
        int mileage = Integer.parseInt(dataToken[4]);

        //check date
        if (!obtained.isCalendarDateValid(dataToken[2])) {
            return false;
        } else if (!Make.isValidMake(dataToken[3])) {
            return false;
        } else if (!Vehicle.isValidMileage(mileage)) {
            return false;
        } else { return true; }

    }

    /**
     *
     * @param mileage
     * @return
     */
    public static boolean isValidMileage(int mileage) {
        if (mileage > 0) {
            return true;
        }
        else {
            Frontend.printInvalidMileageMessage(mileage);
            return false;
        }
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;               // same reference
        if (o == null || getClass() != o.getClass()) return false;

        Vehicle other = (Vehicle) o;              // safe cast now
        return this.plate.equals(other.plate);
    }

    @Override
    public String toString() {
        return plate + ":" + make + ":" + obtained + " [mileage:" + mileage + "]";
    }

    @Override
    public int compareTo(Vehicle o) {
        return plate.compareTo(o.plate);
    }
}