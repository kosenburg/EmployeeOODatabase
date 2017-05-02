package database.Classes;

import java.util.ArrayList;

/**
 * Created by Kevin on 3/2/2017.
 */
public class Project extends DatabaseClass {
    private String location;
    private String number;
    private String name;
    private ArrayList<Employee> employees;


    public Project(String name, String number, String location) {
        setName(name);
        setLocation(location);
        setNumber(number);
        employees = new ArrayList<>();
    }

    public Project(String name, String number, String location, int oid) {
        super(oid);
        setName(name);
        setLocation(location);
        setNumber(number);
        employees = new ArrayList<>();
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
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

    public ArrayList<Employee> getEmployees() {
        return employees;
    }
}
