package database.Commands;

import database.utilities.ClassesContainer;
import org.jdom2.JDOMException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;

/**
 * Created by Kevin on 3/25/2017.
 */
public interface Command {
    void executeCommand();
    void returnResults();
    void setParameters(String[] fields, String[] types, String[] conditions);
}
