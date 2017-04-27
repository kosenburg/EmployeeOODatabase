package database.utilities;

import database.Classes.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

/**
 * Created by Kevin on 4/20/2017.
 */
public class OutputManager {
    public OutputManager() {

    }

    public void displayFullRecord(DatabaseClass record) throws InvocationTargetException, IllegalAccessException {
        if (record instanceof Dependent) {
            System.out.println("Dependent:");
            displayDependent((Dependent) record);
        } else if (record instanceof Employee) {
            displayEmployee((Employee) record);
        } else if (record instanceof Department) {
            displayDepartment((Department) record);
        } else if (record instanceof Project) {
            displayProject((Project) record);
        }
        System.out.println("OID = " + record.getOID());
    }

    private void displayDependent(Dependent record) throws InvocationTargetException, IllegalAccessException {

        for (Method method: record.getClass().getMethods()) {
            if (method.getName().contains("get")) {
                Object result = method.invoke(record);
                // invoke method and set equal to object, if string print if array list cycle and call back to display full record.
                if (result instanceof String) {
                    System.out.println(method.getName().split("get")[1] + ": " + result);
                } else if (result instanceof Employee) {
                    Employee relation = (Employee) result;
                    System.out.println("Related to: " + relation.getFirstName() + " " + relation.getLastName() + " (OID = " + relation.getOID() + ")");
                }
            }
        }
    }

    private void displayEmployee(Employee employee) {

    }

    private void displayDepartment (Department department) {

    }

    private void displayProject(Project record) {

    }
}
