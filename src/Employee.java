public enum Employee {
    Patel ("Computer Science"),
    Lim ("Electrical Engineering"),
    Zimnes ("Computer Science"),
    Harper ("Electrical Engineering"),
    Kaur ("Information Technology and Informatics"),
    Taylor ("Math"),
    Ramesh ("Math"),
    Ceravolo ("Business Analytics and Information Technology");

    private String department;

    Employee(String department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return name() + ": " + department;
    }
}
