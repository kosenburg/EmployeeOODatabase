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
           if(attributes.get("oid") == null) {
               return new Department(attributes.get("name"), attributes.get("number"), attributes.get("location"));
           }else{
               return new Department(attributes.get("name"), attributes.get("number"), attributes.get("location"),Integer.parseInt(attributes.get("oid")));
           }
    }

    private static DatabaseClass createNewProject(HashMap<String, String> attributes) { //TODO set other object references
        if(attributes.get("oid") == null) {
            return new Project(attributes.get("name"), attributes.get("number"), attributes.get("location"));
        }else{
            return new Project(attributes.get("name"), attributes.get("number"),attributes.get("location"),Integer.parseInt(attributes.get("oid")));

        }
    }

    private static DatabaseClass createNewEmployee(HashMap<String, String> attribute) { //TODO set other object references
        if(attribute.get("oid") == null) {
            return new Employee(attribute.get("firstName"), attribute.get("lastName"), attribute.get("middleInitial"), attribute.get("ssn"), attribute.get("sex"));
        }else{
            return new Employee(attribute.get("firstName"), attribute.get("lastName"), attribute.get("middleInitial"), attribute.get("ssn"), attribute.get("sex"),Integer.parseInt(attribute.get("oid")));
        }
    }

    private static DatabaseClass createNewDependent(HashMap<String, String> attribute) {
        if(attribute.get("oid") == null) {
            return new Dependent(attribute.get("name"), new Date(attribute.get("birthDate")), attribute.get("sex"), (Employee) ClassesContainer.getDBObject("Employee", Integer.parseInt(attribute.get("relationships"))));
        }else{
            return new Dependent(attribute.get("name"), new Date(attribute.get("birthDate")), attribute.get("sex"), (Employee) ClassesContainer.getDBObject("Employee", Integer.parseInt(attribute.get("relationships"))), Integer.parseInt(attribute.get("oid")));

        }
    }




}
