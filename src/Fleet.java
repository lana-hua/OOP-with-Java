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

    } //resize the array

    public void add(Vehicle vehicle) {

    } //add to end of array

    public void remove(Vehicle vehicle) {

    } //overwrite with last element

    public boolean contains(Vehicle vehicle) {
        for (int i = 0; i < size; i++){
            if (fleet[i].compareTo(vehicle) == 0){
                return true;
            }
        }
        return false;
    }

    public void printByMake() {

    } //ordered by make, then date obtained
}