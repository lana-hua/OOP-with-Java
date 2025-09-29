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

    public String getDepartment(){
        return department;
    }

    Employee(String department) {
        this.department = department;
    }

    public static boolean isValidEmployee(String employee) {
        String capitalizedName = employee.substring(0, 1).toUpperCase() + employee.toLowerCase().substring(1);
        switch (capitalizedName) {
            case "Patel", "Lim", "Zimnes", "Harper", "Kaur", "Taylor", "Ramesh", "Ceravolo" -> {
                return true;
            }
            default -> {
                Frontend.printInvalidEmployeeMessage(employee);
                return false;
            }
        }
    }

    @Override
    public String toString() {
        return name() + ": " + department;
    }

}
