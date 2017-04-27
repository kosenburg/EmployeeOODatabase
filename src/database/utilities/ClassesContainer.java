package database.utilities;

import database.Classes.*;

import java.util.ArrayList;

/**
 * Created by Kevin on 3/25/2017.
 */
public class ClassesContainer {
    private static ArrayList<DatabaseClass> employees = new ArrayList<>();
    private static ArrayList<DatabaseClass> dependents = new ArrayList<>();
    private static ArrayList<DatabaseClass> departments = new ArrayList<>();
    private static ArrayList<DatabaseClass> projects = new ArrayList<>();


    public static void addClass(DatabaseClass classToAdd) {
        System.out.println("Adding object of " + classToAdd.getClass());
        if (classToAdd instanceof Department) {
            departments.add(classToAdd);
        } else if (classToAdd instanceof Dependent) {
            dependents.add(classToAdd);
        } else if (classToAdd instanceof  Employee) {
            employees.add(classToAdd);
        } else if (classToAdd instanceof Project) {
            projects.add(classToAdd);
        } else {
            throw new IllegalArgumentException();
        }
    }

    public static DatabaseClass getDBObject(String type, int oID) {
        for (DatabaseClass dbClass: getClassList(type)) {
            if (dbClass.getOID() == oID) {
         //       System.out.println("returning class: " + dbClass.getOID());
                return dbClass;
            }
        }
        return null;
    }

    public static ArrayList<DatabaseClass> getClassList(String type) {
        switch (type.toLowerCase()) {
            case "department":
                return departments;
            case "dependent":
                return dependents;
            case "employee":
                return employees;
            case "project":
                return projects;
            default:
                return null;
        }

    }

    public static void removeClass(DatabaseClass classToRemove) {
        System.out.println("Removing object of " + classToRemove.getClass());
        if (classToRemove instanceof Department) {
            remove(departments, classToRemove);
        } else if (classToRemove instanceof Dependent) {
            remove(dependents, classToRemove);
        } else if (classToRemove instanceof  Employee) {
            remove(employees, classToRemove);
        } else if (classToRemove instanceof Project) {
            remove(projects, classToRemove);
        } else {
            throw new IllegalArgumentException();
        }
    }

    private static void remove(ArrayList<DatabaseClass> classes, DatabaseClass toRemove) {
        for (DatabaseClass dbClass: classes) {
            if (dbClass.getOID() == toRemove.getOID()) {
                classes.remove(dbClass);
                break;
            }
        }
    }

}
