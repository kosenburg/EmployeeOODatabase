package database.Commands;

import database.Classes.*;
import database.utilities.UIController;
import database.utilities.object_utilities.ClassesContainer;
import database.utilities.xml_utilities.XMLReader;
import database.utilities.xml_utilities.XMLWriter;
import org.jdom2.JDOMException;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by Kevin on 4/27/2017.
 */
public class Set extends Select {
    private String[] attributes;
    private String type;
    private UIController uicontroller;
    private XMLReader reader;
    private XMLWriter writer;

    public Set(String[] attributes, String expression) {
        super(expression);
        setType(expression);
        try {
            reader = new XMLReader(type);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        this.attributes = attributes;
    }

    @Override
    public void executeCommand() {
        System.out.println("Performing set operation...");
        uicontroller.setTextArea("Performing set operation...");
        try {
            super.setController(uicontroller);
            super.executeCommand();
            addAttributes();
        } catch (IllegalAccessException | InvocationTargetException e) {
            uicontroller.setTextArea("An error occurred while setting a value due to: " + e.getMessage()+ "\n");
            System.out.println("An error occurred while setting a value due to: " + e.getMessage());
        } catch (JDOMException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void addAttributes() throws InvocationTargetException, IllegalAccessException, JDOMException, IOException {
        for (DatabaseClass databaseClass: classes) {
            for (String pair: attributes) {
                reader.remove(databaseClass,type);
                String[] temp = pair.split("=");
                Method method = getMethod(temp[0], databaseClass);

                //System.out.println("Invoking " + method.getName() + " with " + temp[1] + " as argument.");
                perform(method,databaseClass,temp);
                //System.out.println();
                writer = new XMLWriter(databaseClass);
            }
            writer.run();
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
            uicontroller.setTextArea("The method " + methodName + " does not exist for the class " + dbClass.getClass().getName() + "\n");
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
        uicontroller.setTextArea("Attribute set successfully!");
        System.out.println("Attribute set successfully.");
    }

    private void setType(String expression) {
        this.type = expression.split("\\.")[0].trim();
    }

    @Override
    public void setController(UIController controller) {
        this.uicontroller = controller;
    }
}
