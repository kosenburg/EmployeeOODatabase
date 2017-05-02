package database.Commands;

import database.utilities.UIController;
import org.jdom2.JDOMException;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by Kevin on 3/25/2017.
 */
public interface Command {
    void executeCommand() throws JDOMException, IOException, IllegalAccessException, InvocationTargetException;
    void returnResults();
    void setParameters(String[] fields, String[] types, String[] conditions);
    void setController(UIController controller);
}
