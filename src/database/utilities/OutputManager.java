package database.utilities;

import database.Classes.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

/**
 * Created by Kevin on 4/20/2017.
 */
public class OutputManager {
    private UIController uicontroller;

    public OutputManager() {

    }

    public void displayFullRecord(DatabaseClass record) throws InvocationTargetException, IllegalAccessException {
        if (record instanceof Dependent) {
            System.out.println("Dependent:");
            uicontroller.setTextArea("Dependent:");
            uicontroller.setTextArea(displayDependent((Dependent) record));
        } else if (record instanceof Employee) {
            System.out.println("Employee:");
            uicontroller.setTextArea("Employee:");
            uicontroller.setTextArea(displayEmployee((Employee) record));
        } else if (record instanceof Department) {
            System.out.println("Department:");
            uicontroller.setTextArea("Department:");
            uicontroller.setTextArea(displayDepartment((Department) record));
        } else if (record instanceof Project) {
            System.out.println("Project:");
            uicontroller.setTextArea("Project:");
            uicontroller.setTextArea(displayProject((Project) record));
        }

    }

    private String displayDependent(Dependent record) throws InvocationTargetException, IllegalAccessException {
        String output = "";
        for (Method method: record.getClass().getMethods()) {
            if (method.getName().contains("get")) {
                Object result = method.invoke(record);
                // invoke method and set equal to object, if string print if array list cycle and call back to display full record.
                if (result instanceof String) {
                    output += method.getName().split("get")[1] + ": " + result + "\n";
                    System.out.println(method.getName().split("get")[1] + ": " + result);
                } else if (result instanceof ArrayList<?>) {
                    ArrayList<DatabaseClass> list = (ArrayList<DatabaseClass>) result;
                    for (DatabaseClass object: list) {
                        if (object instanceof Employee) {
                            output += "Related to: " + ((Employee) object).getFirstName() + " " + ((Employee) object).getLastName() + "(OID = " + object.getOID() + ")";
                            System.out.println("Related to: " + ((Employee) object).getFirstName() + " " + ((Employee) object).getLastName() + "(OID = " + object.getOID() + ")");
                        }
                    }
                }
            }
        }
        return output;
    }

    private String displayEmployee(Employee employee) throws InvocationTargetException, IllegalAccessException {
       String output = "";
        for (Method method: employee.getClass().getMethods()) {
            if (method.getName().contains("get")) {
                Object result = method.invoke(employee);
                // invoke method and set equal to object, if string print if array list cycle and call back to display full record.
                if (result instanceof String) {
                    output += method.getName().split("get")[1] + ": " + result + "\n";
                    System.out.println(method.getName().split("get")[1] + ": " + result);
                } else if (result instanceof ArrayList<?>) {
                    ArrayList<DatabaseClass> list = (ArrayList<DatabaseClass>) result;
                    for (DatabaseClass object: list) {
                        if (object instanceof Dependent) {
                            output += "Related to: " + ((Dependent) object).getName() + " (OID = " + object.getOID() + ")\n";
                            System.out.println("Related to: " + ((Dependent) object).getName() + " (OID = " + object.getOID() + ")");
                        }

                        if (object instanceof Project) {
                            output += "Works on: " + ((Project) object).getName() + " " + ((Project) object).getNumber() + "\n";
                            System.out.println("Works on: " + ((Project) object).getName() + " " + ((Project) object).getNumber());
                        }

                        if (object instanceof Employee) {
                            output += "Supervises (ssn): " + ((Employee) object).getSsn() + "\n";
                            System.out.println("Supervises (ssn): " + ((Employee) object).getSsn());
                        }
                    }
                } else if (result instanceof Department) {
                    output += "Works in: " + ((Department) result).getName() + " " + ((Department) result).getNumber() + "\n";
                    System.out.println("Works in: " + ((Department) result).getName() + " " + ((Department) result).getNumber());
                }
            }
        }
        return output;
    }

    private String displayDepartment (Department department) throws InvocationTargetException, IllegalAccessException {
        String output = "";
        for (Method method: department.getClass().getMethods()) {
            if (method.getName().contains("get")) {
                Object result = method.invoke(department);
                // invoke method and set equal to object, if string print if array list cycle and call back to display full record.
                if (result instanceof String) {
                    output += method.getName().split("get")[1] + ": " + result + "\n";
                    System.out.println(method.getName().split("get")[1] + ": " + result);
                } else if (result instanceof ArrayList<?>) {
                    ArrayList<DatabaseClass> list = (ArrayList<DatabaseClass>) result;
                    for (DatabaseClass object: list) {
                        if (object instanceof Employee) {
                            output += "In department: " + ((Employee) object).getFirstName() + " " + ((Employee) object).getLastName() + "(OID = " + object.getOID() + ")\n";
                            System.out.println("In department: " + ((Employee) object).getFirstName() + " " + ((Employee) object).getLastName() + "(OID = " + object.getOID() + ")");
                        }

                        if (object instanceof Project) {
                            output += "Maintains project: " + ((Project) object).getName() + " " + ((Project) object).getNumber() + "\n";
                            System.out.println("Maintains project: " + ((Project) object).getName() + " " + ((Project) object).getNumber());
                        }
                    }
                }
            }
        }
        return output;
    }

    private String displayProject(Project record) throws InvocationTargetException, IllegalAccessException {
        String output = "";
        for (Method method: record.getClass().getMethods()) {
            if (method.getName().contains("get")) {
                Object result = method.invoke(record);
                // invoke method and set equal to object, if string print if array list cycle and call back to display full record.
                if (result instanceof String) {
                    output += method.getName().split("get")[1] + ": " + result + "\n";
                    System.out.println(method.getName().split("get")[1] + ": " + result);
                } else if (result instanceof ArrayList<?>) {
                    ArrayList<DatabaseClass> list = (ArrayList<DatabaseClass>) result;
                    for (DatabaseClass object: list) {
                        if (object instanceof Employee) {
                            output += "Contributors: " + ((Employee) object).getFirstName() + " " + ((Employee) object).getLastName() + "(OID = " + object.getOID() + ")\n";
                            System.out.println("Contributors: " + ((Employee) object).getFirstName() + " " + ((Employee) object).getLastName() + "(OID = " + object.getOID() + ")");
                        }
                    }
                }
            }
        }
return output;
    }

    public void setController(UIController uicontroller) {
        this.uicontroller = uicontroller;
    }
}
