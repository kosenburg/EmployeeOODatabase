package database.Classes;

import java.util.ArrayList;

/**
 * Created by Kevin on 3/2/2017.
 */
public class Project {
    private String location;
    private int number;
    private String name;
    private ArrayList<Employee> employees;

    public Project(String location, int number, String name, Employee manager) {
        setName(name);
        setLocation(location);
        setNumber(number);
        employees = new ArrayList<Employee>();
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addEmployee(Employee e) {
        employees.add(e);
    }


}
