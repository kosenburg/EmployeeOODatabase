package database.utilities;

import database.Classes.*;

import java.lang.reflect.Field;
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

    public static void main(String[] args) {

    }



    //TODO Implement the find and searches for the lists of classes in here and leave logic for additional steps in
    //TODO the command objects.

}
