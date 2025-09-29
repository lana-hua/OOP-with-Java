public class TripList {
    private Node last; //the reference to the last node of the linked list.

    public void add(Trip trip) {
        if (last == null) {
            Node newNode = new Node(trip);
            newNode.next = newNode;
            last = newNode;
        } else {
            Node newNode = new Node(trip);
            newNode.next = last.next;
            last = newNode;
        }
    }

    public void print() {
        if (last == null) {return;}

        int length = 0;
        Node ptr = last.next;

        do {
            length++;
            ptr = ptr.next;
        } while (ptr != last.next);

        boolean[] visited = new boolean[length];

        for (int printed = 0; printed < length; printed++) {
            Node minNode = null;
            int minIndex = -1;

            ptr = last.next;
            for (int i = 0; i < length; i++) {
                if (!visited[i]) {
                    if (minNode == null || (ptr.trip.getBooking().getEnd().compareTo(minNode.trip.getBooking().getEnd()) < 0)) {
                        minNode = ptr;
                        minIndex = i;
                    }
                }
                ptr = ptr.next;
            }

            System.out.println(minNode.trip.toString());
            visited[minIndex] = true;
        }
    }
     //print the list ordered by the ending date
}
