public enum Department {
    COMPUTER_SCIENCE("Computer Science"),
    ELECTRICAL_ENGINEERING("Electrical Engineering"),
    INFORMATION_TECHNOLOGY_AND_INFORMATICS("Information Technology and Informatics"),
    MATHEMATICS("Mathematics"),
    BUSINESS_ANALYTICS_AND_INFORMATION_TECHNOLOGY("Business Analytics and Information Technology");

    private final String fullName;

    Department(String fullName) {
        this.fullName = fullName;
    }

    @Override
    public String toString() {
        return fullName;
    }
}
