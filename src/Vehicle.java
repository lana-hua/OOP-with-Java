public class Vehicle {
    private String plate;
    private String obtained;
    private Make make;
    private int mileage;

    public Vehicle(String plate, String obtained, Make make, int mileage){
        this.plate = plate;
        this.obtained = obtained;
        this.make = make;
        this.mileage = mileage;
    }

    @Override
    public String toString(){
        return plate + ":" + make + ":" + obtained + " [mileage:" + mileage + "]";
    }
//58718D:FORD:2/29/2020 [mileage:64390]
}

