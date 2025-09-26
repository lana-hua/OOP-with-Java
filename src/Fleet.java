public class Fleet {
    private static final int CAPACITY = 4; //initial capacity
    private static final int NOT_FOUND = -1;
    private Vehicle[] fleet;
    private int size; //current number of vehicles in the fleet

    private int find(Vehicle vehicle) {
        if (contains(vehicle)) {
            for (int i = 0; i < size; i++){
                if (fleet[i].compareTo(vehicle) == 0){
                    return i;
                }
            }
        }
        return NOT_FOUND;
    } //search the given vehicle

    private void grow() {
        Vehicle[] newArray = new Vehicle[size+4];

        if (size >= 0) System.arraycopy(fleet, 0, newArray, 0, size);

        fleet = newArray;
    }

    public void add(Vehicle vehicle) {
        if (size % CAPACITY == 0){
            grow();
            fleet[size] = vehicle;
        }
        else {
            fleet[size] = vehicle;
            size++;
        }
    } //add to end of array

    public void remove(Vehicle vehicle) {
        if (contains(vehicle)){
            int index = find(vehicle);
            fleet[index] = fleet[size-1];
            fleet[size-1] = null;
            size--;
        } else {

        }
    } //overwrite with last element

    public boolean contains(Vehicle vehicle) {
        for (int i = 0; i < size; i++){
            if (fleet[i].compareTo(vehicle) == 0){
                return true;
            }
        }

        String notInFleet = vehicle.getPlate() + " is not in the fleet.";
        System.out.println(notInFleet);
        return false;
    }

    public void printByMake() {

    } //ordered by make, then date obtained
}
