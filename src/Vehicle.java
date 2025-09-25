public class Vehicle implements Comparable<Vehicle> {
    private String plate; //license plate number
    private Date obtained; //Date class described in the next page
    private Make make; //Make is an enum class
    private int mileage; //current reading on the odometer


    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public Make getMake() {
        return make;
    }

    public void setMake(Make make) {
        this.make = make;
    }

    public Date getObtained() {
        return obtained;
    }

    public void setObtained(Date obtained) {
        this.obtained = obtained;

    }

    public Vehicle(String plate, Date obtained, Make make, int mileage) {
        this.plate = plate;
        this.obtained = obtained;
        this.make = make;
        this.mileage = mileage;
    }

    public static boolean isValidMileage(int mileage) {
        if (mileage > 0) {
            return true;
        }
        else {
            String invalidMileage = mileage + " - invalid mileage!";
            System.out.println(invalidMileage);
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


