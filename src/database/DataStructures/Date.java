package database.DataStructures;

/**
 * Created by Kevin on 3/2/2017.
 */
public class Date {
    private String date;
    private String day = "";
    private String month = "";
    private String year = "";

    public Date(String date) { // Date format: 00/00/0000
        parseDate(date);
    }

    private void parseDate(String date) {
        String[] values = date.split("/");
        if (values.length == 3) {
            setMonth(values[0]);
            setDay(values[1]);
            setYear(values[2]);
        }

    }

    public String getDate() {
        return date;
    }

    public String getDay() {
        return day;
    }

    private void setDay(String day) {
        this.day = day;
    }

    public String getMonth() {
        return month;
    }

    private void setMonth(String month) {
        this.month = month;
    }

    public String getYear() {
        return year;
    }

    private void setYear(String year) {
        this.year = year;
    }
}
