//public class Date implements Comparable<Date> {
public class Date {
    private int day;
    private int month;
    private int year;

    public static final int QUADRENNIAL = 4;
    public static final int CENTENNIAL = 100;
    public static final int QUATERCENTENNIAL = 400;

    public Date(int day, int month, int year){
        this.day = day;
        this.month = month;
        this.year = year;
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

    //NOT DONE
    public boolean isValid() {
        if ((day < 1) || (day > 31) || (month < 1) || (month > 12) || (year < 0)) {
            return false;
        }
        return true;
    }

    public Date(String dateInput) {
        String[] dateSections = dateInput.split("/");

        if ((dateSections.length == 3) && (isValid())) {
            this.month = Integer.parseInt(dateSections[0]);
            this.day = Integer.parseInt(dateSections[1]);
            this.year = Integer.parseInt(dateSections[2]);
        }
        else {
            String invalid_command = dateInput + " - invalid calendar date!";
            System.out.println(invalid_command);
        }
    }
}
