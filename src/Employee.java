public enum Employee {
    Patel (Department.COMPUTER_SCIENCE),
    Lim (Department.ELECTRICAL_ENGINEERING),
    Zimnes (Department.COMPUTER_SCIENCE),
    Harper (Department.ELECTRICAL_ENGINEERING),
    Kaur (Department.INFORMATION_TECHNOLOGY_AND_INFORMATICS),
    Taylor (Department.MATHEMATICS),
    Ramesh (Department.MATHEMATICS),
    Ceravolo (Department.BUSINESS_ANALYTICS_AND_INFORMATION_TECHNOLOGY);

    private Department dept;

    public Department getDepartment(){
        return dept;
    }

    Employee(Department department) {
        this.dept = department;
    }

    public static boolean isValidEmployee(String employee) {
        String capitalizedName = employee.substring(0, 1).toUpperCase() + employee.toLowerCase().substring(1);
        switch (capitalizedName) {
            case "Patel", "Lim", "Zimnes", "Harper", "Kaur", "Taylor", "Ramesh", "Ceravolo" -> {
                return true;
            }
            default -> {
                return false;
            }
        }
    }

    @Override
    public String toString() {
        return name() + ": " + dept;
    }

}
