package database.Commands;

import database.Classes.*;
import database.utilities.object_utilities.ClassesContainer;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by Kevin on 4/27/2017.
 */
public class Set extends Select {
    private String[] attributes;
    private String type;

    public Set(String[] attributes, String expression) {
        super(expression);
        setType(expression);
        this.attributes = attributes;
    }

    @Override
    public void executeCommand() {
        System.out.println("Performing set operation...");
        try {
            super.executeCommand();
            addAttributes();
        } catch (IllegalAccessException | InvocationTargetException e) {
            System.out.println("An error occurred while setting a value due to: " + e.getMessage());
        }
    }

    private void addAttributes() throws InvocationTargetException, IllegalAccessException {
        for (DatabaseClass databaseClass: classes) {
            for (String pair: attributes) {
                String[] temp = pair.split("=");
                Method method = getMethod(temp[0], databaseClass);

                //System.out.println("Invoking " + method.getName() + " with " + temp[1] + " as argument.");
                perform(method,databaseClass,temp);
                //System.out.println();
            }
        }

    }


    private void perform(Method method, DatabaseClass databaseClass, String temp[]) throws InvocationTargetException, IllegalAccessException {
        for (Class<?> param: method.getParameterTypes()){
            String name = param.getName();

            if (name.contains("Employee")) {
                method.invoke(databaseClass, (Employee) ClassesContainer.getDBObject("Employee", Integer.parseInt(temp[1])));
            } else if (name.contains("Dependent")) {
                method.invoke(databaseClass, (Dependent) ClassesContainer.getDBObject("Dependent", Integer.parseInt(temp[1])));
            } else if (name.contains("Department")) {
                method.invoke(databaseClass, (Department) ClassesContainer.getDBObject("Department", Integer.parseInt(temp[1])));
            } else if (name.contains("Project")) {
                method.invoke(databaseClass, (Project) ClassesContainer.getDBObject("Project", Integer.parseInt(temp[1])));
            } else {
                method.invoke(databaseClass, temp[1]);
            }
        }

    }


    private Method getMethod(String methodName, DatabaseClass dbClass) {
        try {
            for (Method method : dbClass.getClass().getMethods()) {
                if ((method.getName().contains("add") || method.getName().contains("set"))&& method.getName().toLowerCase().contains(methodName)) {
                    if ((method.getName().toLowerCase().equals("set" + methodName)) || (method.getName().toLowerCase().equals("add" + methodName))) {
                        return dbClass.getClass().getMethod(method.getName(), method.getParameterTypes());
                    }
                }
            }
        } catch (NoSuchMethodException e) {
            System.err.println("The method " + methodName + " does not exist for the class " + dbClass.getClass().getName());
        }
        return null;
    }

    private String getAttributeName(String dotExpression) {
        String[] temp = dotExpression.split("\\.");
        return temp[1];
    }

    @Override
    public void returnResults() {
        System.out.println("Attribute set successfully.");
    }

    private void setType(String expression) {
        this.type = expression.split("\\.")[0].trim();
    }
}
