package database;

import database.Classes.Department;
import database.Commands.Add;
import database.Commands.Command;
import database.utilities.ClassesContainer;
import database.utilities.Parser;
import database.utilities.XMLSax;
import org.jdom2.JDOMException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.util.HashMap;


public class Main {

    public static void main(String[] args) throws ParserConfigurationException, JDOMException, IOException, IllegalAccessException, TransformerException {
        Thread xmlThread = new XMLSax();



        String query = "Add Project With location=here,number=2,name=AProject";
        Command cmd = Parser.getCommand(query);
        cmd.executeCommand();
        cmd.returnResults();

        xmlThread.start();


        String query3 = "Add Project With location=there,number=1,name=NewProject";
        cmd = Parser.getCommand(query3);
        cmd.executeCommand();
        cmd.returnResults();


        System.out.println();



        String query1 = "Add Department With location=here,name=ADepartment,number=2";
        cmd = Parser.getCommand(query1);
        cmd.executeCommand();
        cmd.returnResults();

    }
}
