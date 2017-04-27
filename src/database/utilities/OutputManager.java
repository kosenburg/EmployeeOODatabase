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
            System.out.println("Employee:");
            displayEmployee((Employee) record);
        } else if (record instanceof Department) {
            System.out.println("Department:");
            displayDepartment((Department) record);
        } else if (record instanceof Project) {
            System.out.println("Project:");
            displayProject((Project) record);
        }

    }

    private void displayDependent(Dependent record) throws InvocationTargetException, IllegalAccessException {

        for (Method method: record.getClass().getMethods()) {
            if (method.getName().contains("get")) {
                Object result = method.invoke(record);
                // invoke method and set equal to object, if string print if array list cycle and call back to display full record.
                if (result instanceof String) {
                    System.out.println(method.getName().split("get")[1] + ": " + result);
                } else if (result instanceof ArrayList<?>) {
                    ArrayList<DatabaseClass> list = (ArrayList<DatabaseClass>) result;
                    for (DatabaseClass object: list) {
                        if (object instanceof Employee) {
                            System.out.println("Related to: " + ((Employee) object).getFirstName() + " " + ((Employee) object).getLastName() + "(OID = " + object.getOID() + ")");
                        }
                    }
                }
            }
        }
    }

    private void displayEmployee(Employee employee) throws InvocationTargetException, IllegalAccessException {
        for (Method method: employee.getClass().getMethods()) {
            if (method.getName().contains("get")) {
                Object result = method.invoke(employee);
                // invoke method and set equal to object, if string print if array list cycle and call back to display full record.
                if (result instanceof String) {
                    System.out.println(method.getName().split("get")[1] + ": " + result);
                } else if (result instanceof ArrayList<?>) {
                    ArrayList<DatabaseClass> list = (ArrayList<DatabaseClass>) result;
                    for (DatabaseClass object: list) {
                        if (object instanceof Dependent) {
                            System.out.println("Related to: " + ((Dependent) object).getName() + " (OID = " + object.getOID() + ")");
                        }

                        if (object instanceof Project) {
                            System.out.println("Works on: " + ((Project) object).getName() + " " + ((Project) object).getNumber());
                        }

                        if (object instanceof Employee) {
                            System.out.println("Supervises (ssn): " + ((Employee) object).getSsn());
                        }
                    }
                } else if (result instanceof Department) {
                    System.out.println("Works in: " + ((Department) result).getName() + " " + ((Department) result).getNumber());
                }
            }
        }
    }

    private void displayDepartment (Department department) throws InvocationTargetException, IllegalAccessException {
        for (Method method: department.getClass().getMethods()) {
            if (method.getName().contains("get")) {
                Object result = method.invoke(department);
                // invoke method and set equal to object, if string print if array list cycle and call back to display full record.
                if (result instanceof String) {
                    System.out.println(method.getName().split("get")[1] + ": " + result);
                } else if (result instanceof ArrayList<?>) {
                    ArrayList<DatabaseClass> list = (ArrayList<DatabaseClass>) result;
                    for (DatabaseClass object: list) {
                        if (object instanceof Employee) {
                            System.out.println("In department: " + ((Employee) object).getFirstName() + " " + ((Employee) object).getLastName() + "(OID = " + object.getOID() + ")");
                        }

                        if (object instanceof Project) {
                            System.out.println("Maintains project: " + ((Project) object).getName() + " " + ((Project) object).getNumber());
                        }
                    }
                }
            }
        }

    }

    private void displayProject(Project record) throws InvocationTargetException, IllegalAccessException {
        for (Method method: record.getClass().getMethods()) {
            if (method.getName().contains("get")) {
                Object result = method.invoke(record);
                // invoke method and set equal to object, if string print if array list cycle and call back to display full record.
                if (result instanceof String) {
                    System.out.println(method.getName().split("get")[1] + ": " + result);
                } else if (result instanceof ArrayList<?>) {
                    ArrayList<DatabaseClass> list = (ArrayList<DatabaseClass>) result;
                    for (DatabaseClass object: list) {
                        if (object instanceof Employee) {
                            System.out.println("Contributors: " + ((Employee) object).getFirstName() + " " + ((Employee) object).getLastName() + "(OID = " + object.getOID() + ")");
                        }
                    }
                }
            }
        }

    }
}
