package database.utilities;

import database.Classes.*;

import java.util.ArrayList;

/**
 * Created by Kevin on 3/25/2017.
 */
public class ClassesContainer {
    private ArrayList<Employee> employees;
    private ArrayList<Dependent> dependents;
    private ArrayList<Department> departments;
    private ArrayList<Project> projects;

    public ClassesContainer() {
        employees = new ArrayList<>();
        departments = new ArrayList<>();
        dependents = new ArrayList<>();
        projects = new ArrayList<>();
    };

    public void addClass(DatabaseClass classToAdd) {
        if (classToAdd instanceof Department) {
            departments.add((Department) classToAdd);
        } else if (classToAdd instanceof Dependent) {
            dependents.add((Dependent) classToAdd);
        } else if (classToAdd instanceof  Employee) {
            employees.add((Employee) classToAdd);
        } else if (classToAdd instanceof Project) {
            projects.add((Project) classToAdd);
        } else {
            throw new IllegalArgumentException();
        }
    }

    //TODO Implement the find and searches for the lists of classes in here and leave logic for additional steps in
    //TODO the command objects.

}
