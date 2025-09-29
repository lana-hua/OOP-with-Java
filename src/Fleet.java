public class Fleet {
    private static final int CAPACITY = 4; //initial capacity
    private static final int NOT_FOUND = -1;
    private Vehicle[] fleet;
    private int size; //current number of vehicles in the fleet

    public Fleet() {
        fleet = new Vehicle[CAPACITY]; // initialize array with starting capacity
        size = 0;
    }

    private int find(Vehicle vehicle) {
        if (contains(vehicle)) {
            for (int i = 0; i < size; i++){
                if (fleet[i].getPlate().compareTo(vehicle.getPlate()) == 0){
                    return i;
                }
            }
        }
        return NOT_FOUND;
    } //search the given vehicle

    private void grow() {
        Vehicle[] newArray = new Vehicle[size+4];

        if (size >= 0) System.arraycopy(fleet, 0, newArray, 0, fleet.length);

        fleet = newArray;
    }

    public void add(Vehicle vehicle) {
        if (size == fleet.length) {
            grow();
        }
        fleet[size] = vehicle;
        size++;
    } //add to end of array

    public void remove(Vehicle vehicle) {
        int index = find(vehicle);
        if (index != NOT_FOUND){
            Frontend.removedMessage(fleet[index]);
            fleet[index] = fleet[size - 1];
            fleet[size - 1] = null;
            size--;
        } else {
            Frontend.notInFleetMessage(vehicle);
        }
    } //overwrite with last element

    public boolean contains(Vehicle vehicle) {
        for (int i = 0; i < size; i++){
            if (fleet[i].getPlate().compareTo(vehicle.getPlate()) == 0){
                return true;
            }
        }
        return false;
    }

    public void printByMake() {
        if (size == 0) {
            Frontend.noVehicleInFleet();
            //selection sort by make then by date
        } else {
            System.out.println("*List of vehicles in the fleet, ordered by make and date obtained.");
            for (int i = 0; i < size - 1; i++) {
                int minIndex = i;
                for (int j = i + 1; j < size; j++) {
                    int compare = fleet[j].getMake().compareTo(fleet[minIndex].getMake());

                    if (compare == 0) {
                        compare = fleet[i].getDate().compareTo(fleet[minIndex].getDate());
                    }

                    if (compare < 0) {
                        minIndex = j;
                    }

                }
                if (minIndex != i) {
                    Vehicle temp = fleet[i];
                    fleet[i] = fleet[minIndex];
                    fleet[minIndex] = temp;
                }
            }

            for (int i = 0; i < size; i++) {
                System.out.println(fleet[i]);
            }

            System.out.println("*end of list.\n");
        }

    }//ordered by make, then date obtained
}
