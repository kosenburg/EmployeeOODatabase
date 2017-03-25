package database.Classes;

import java.util.ArrayList;

/**
 * Created by Kevin on 3/2/2017.
 */
public class Project implements DatabaseClass {
    private String location;
    private int number;
    private String name;
    private ArrayList<Employee> employees;

    public Project(String location, int number, String name) {
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

    public Employee getEmployee(String ssn) {
        for(Employee employee:employees) {
            if (employee.getSsn().equals(ssn)) {
                return employee;
            }
        }
        return null;
    }

    public Employee[] getAllEmployees() {
        Employee[] list = new Employee[employees.size()];

        int i = 0;
        for (Employee employee: employees) {
            list[i] = employee;
            i++;
        }

        return list;
    }
}
