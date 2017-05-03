package database.Commands;

import database.Classes.DatabaseClass;
import database.utilities.UIController;
import database.utilities.object_utilities.ClassesContainer;
import database.utilities.xml_utilities.XMLReader;
import database.utilities.xml_utilities.XMLWriter;
import org.jdom2.JDOMException;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Kevin on 4/27/2017.
 */
public class Remove extends Select {
    Command command;
    ArrayList<DatabaseClass> classList;
    XMLReader reader;
    String fieldName;
    String type;
    UIController uicontroller;

    public Remove(String expression) {
        super(expression);

        type = expression.split("\\.")[0];
        fieldName = expression.split("\\.")[1].split("\\s+")[0];
        classList = ClassesContainer.getClassList(type);
        try {
            reader = new XMLReader(type);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void executeCommand() {
        super.executeCommand();
        removeClasses();
    }

    private void removeClasses(){
        if (classes != null) {
            for (DatabaseClass dbClass: classes) {
                ClassesContainer.removeClass(dbClass);
                try {
                    reader.remove(dbClass,fieldName);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (JDOMException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    @Override
    public void returnResults() {

        System.out.println("Classes removed.");
    }

}
