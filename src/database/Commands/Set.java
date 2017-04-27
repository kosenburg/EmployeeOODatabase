package database.Commands;

import database.Classes.DatabaseClass;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

/**
 * Created by Kevin on 4/27/2017.
 */
public class Set extends Select {
    private String[] attributes;

    public Set(String[] attributes, String expression) {
        super(expression);
        this.attributes = attributes;
    }

    @Override
    public void executeCommand() {
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
                System.out.println("Invoking " + method.getName() + " with " + temp[1] + " as argument.");
                method.invoke(databaseClass,temp[1]);
            }

        }
    }


    private Method getMethod(String methodName, DatabaseClass dbClass) {
        try {
            for (Method method : dbClass.getClass().getMethods()) {
                if ((method.getName().contains("add") || method.getName().contains("set"))&& method.getName().toLowerCase().contains(methodName)) {
                    return dbClass.getClass().getMethod(method.getName(), method.getParameterTypes());
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
}
