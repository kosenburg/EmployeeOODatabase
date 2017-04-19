package database.utilities;

import database.Classes.*;
import database.DataStructures.Date;

import java.util.HashMap;

/**
 * Created by Kevin on 3/30/2017.
 */
public class ObjectFactory {
    public ObjectFactory() {}

    public static DatabaseClass getObject(String type, HashMap<String, String> attributes) {
        switch (type.toLowerCase()) {
            case "project":
                return createNewProject(attributes);
            case "employee":
                return  createNewEmployee(attributes);
            case "dependent":
                return createNewDependent(attributes);
            case "department":
                return createNewDepartment(attributes);
            default:
                throw new IllegalArgumentException();
        }
    }


    private static DatabaseClass createNewDepartment(HashMap<String, String> attributes) { // TODO set other object references
        return new Department(attributes.get("name"), attributes.get("number"),attributes.get("location"));
    }

    private static DatabaseClass createNewProject(HashMap<String, String> attributes) { //TODO set other object references
        return new Project(attributes.get("name"), Integer.parseInt(attributes.get("number")),attributes.get("location"));
    }

    private static DatabaseClass createNewEmployee(HashMap<String, String> attribute) { //TODO set other object references
        return new Employee(attribute.get("firstName"), attribute.get("lastName"), attribute.get("middleInitial"), attribute.get("ssn"), attribute.get("sex"));
    }

    private static DatabaseClass createNewDependent(HashMap<String, String> attribute) {
        return new Dependent(attribute.get("name"), new Date(attribute.get("birthDate")), attribute.get("sex"), (Employee) ClassesContainer.getDBObject("Employee", Integer.parseInt(attribute.get("employee"))));
    }




}
