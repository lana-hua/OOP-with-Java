public class TripList {
    private Node last; //the reference to the last node of the linked list.

    public void add(Trip trip) {
        Node newNode = new Node(trip);
        if (last == null) {
            newNode.next = newNode;
            last = newNode;
        } else {
            newNode.next = last.next;
            last.next = newNode;
            last = newNode;
        }
    }

    public void print() {
        if (last == null) {
            System.out.println("There is no archived trips.");
            return;
        }
        System.out.println("*List of completed trips ordered by ending date.");

        int length = 1;
        Node ptr = last.next;

        while (ptr != last) {
            length++;
            ptr = ptr.next;
        }

        boolean[] visited = new boolean[length];

        for (int i = 0; i < length; i++) {
            Node minNode = null;
            int minIndex = -1;

            ptr = last.next;
            for (int j = 0; j < length; j++) {
                if (!visited[j]) {
                    if (minNode == null || (ptr.trip.getBooking().getEnd().compareTo(minNode.trip.getBooking().getEnd()) < 0)) {
                        minNode = ptr;
                        minIndex = j;
                    }
                }
                ptr = ptr.next;
            }
            System.out.println(minNode.trip.toString());
            visited[minIndex] = true;

        }
        System.out.println("*end of list.\n");
    }
}
