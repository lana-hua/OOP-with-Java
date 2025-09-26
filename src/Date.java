public class Date implements Comparable<Date> {
    private int day;
    private int month;
    private int year;

    public static final int QUADRENNIAL = 4;
    public static final int CENTENNIAL = 100;
    public static final int QUATERCENTENNIAL = 400;

    public Date(int month, int day, int year){
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public int getDay(){
        return day;
    }

    public int getMonth(){
        return month;
    }

    public int getYear(){
        return year;
    }

    /**
     * Leap Year Steps:
         * Step 1. If the year is evenly divisible by 4, go to step 2. Otherwise, go to step 5.
         * Step 2. If the year is evenly divisible by 100, go to step 3. Otherwise, go to step 4.
         * Step 3. If the year is evenly divisible by 400, go to step 4. Otherwise, go to step 5.
         * Step 4. The year is a leap year.
         * Step 5. The year is not a leap year
     **/

    public boolean isLeapYear() {
        if (year % QUADRENNIAL == 0){
            if (year % CENTENNIAL== 0){
                if (year % QUATERCENTENNIAL == 0){
                    return true;
                }
                else{
                    return false;
                }
            }
            else{
                return true;
            }
        }
        else{
            return false;
        }
    }

    public boolean isValid() {
        if ((day < 1) || (day > 31) || (month < 1) || (month > 12) || (year < 0)) {
            return false;
        }

        //setting maxDays in each month
        int maxDays;
        if (month == 2) {
            if (isLeapYear()) {
                maxDays = 29;
            }
            else {
                maxDays = 28;
            }
        }
        else if ((month == 4) || (month == 6) || (month == 9) || (month == 11)) {
            maxDays = 30;
        }
        else {
            maxDays = 31;
        }

        return true;
    }

    public Date(String dateInput) {
        String[] dateSections = dateInput.split("/");

        if ((dateSections.length == 3)) {
            this.month = Integer.parseInt(dateSections[0]);
            this.day = Integer.parseInt(dateSections[1]);
            this.year = Integer.parseInt(dateSections[2]);

            if (!isValid()){
                String invalid_command = dateInput + " - invalid calendar date!";
                System.out.println(invalid_command);
            }
        }
        else {
            String invalid_command = dateInput + " - invalid calendar date!";
            System.out.println(invalid_command);
        }
    }

    @Override
    public boolean equals(Object comparison) {
        if (this == comparison) {
            return true;
        }
        if ((comparison == null) || (this.getClass() != comparison.getClass())) {
            return false;
        }

        Date compareDate = (Date) comparison;
        return ((this.day == compareDate.day) && (this.month == compareDate.month) && (this.year == compareDate.year));
    }

    @Override
    public String toString(){
        return (month + "/" + day + "/" + year);
    }

    @Override
    public int compareTo(Date comparison){
        if (this.year != comparison.year){
            return Integer.compare(this.year, comparison.year);
        }
        if (this.month != comparison.month){
            return Integer.compare(this.month, comparison.month);
        }
        return Integer.compare(this.day, comparison.day);
    }
}
