package database.utilities;

import database.Classes.Department;
import database.Classes.Dependent;
import database.Classes.Employee;
import database.Classes.Project;

import java.lang.reflect.Field;

/**
 * Created by Kevin on 3/30/2017.
 */
public class FieldContainer {
    private static Field[] fields;

    public static Field[] getFields(String type) {
        type = type.toLowerCase();
        if (type.equals("department")) {
            return Department.class.getDeclaredFields();
        } else if (type.equals("dependent")) {
            return Dependent.class.getDeclaredFields();
        } else if (type.equals("project")) {
            return Project.class.getDeclaredFields();
        } else if (type.equals("employee")) {
            return Employee.class.getDeclaredFields();
        } else {
            throw new IllegalArgumentException();
        }
    }

    public static boolean isInFields(String queryField, String type) {
        switch (type.toLowerCase()) {
            case "department":
                fields = Department.class.getDeclaredFields();
                break;
            case "dependent":
                fields = Dependent.class.getDeclaredFields();
                break;
            case "project":
                fields = Project.class.getDeclaredFields();
                break;
            case "employee":
                fields = Employee.class.getDeclaredFields();
                break;
            default:
                throw new IllegalArgumentException();
        }
        return queryFields(queryField);
    }

    private static boolean queryFields(String queryField) {
        for (Field field: fields) {
            if (queryField.equals(field.getName())) {
                return true;
            }
        }
        return false;
    }
}
