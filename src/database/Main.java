package database;

import database.Commands.Command;
import database.utilities.object_utilities.ClassesContainer;
import database.utilities.Parser;
import org.jdom2.JDOMException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;


public class Main {

    public static void main(String[] args) throws ParserConfigurationException, IOException, IllegalAccessException, TransformerException, ClassNotFoundException, InvocationTargetException, JDOMException {

        String query = "Add Project With location=Baltimore,number=2,name=Project1";
        System.out.println("Executing: " + query);
        Command cmd = Parser.getCommand(query);
        cmd.executeCommand();
        cmd.returnResults();
        System.out.println();

        query = "Add Department With location=here,name=ADepartment,number=2";
        System.out.println("Executing: " + query);
        cmd = Parser.getCommand(query);
        cmd.executeCommand();
        cmd.returnResults();
        System.out.println();

        query = "Add Department With location=Towson,name=Cosc,number=4";
        System.out.println("Executing: " + query);
        cmd = Parser.getCommand(query);
        cmd.executeCommand();
        cmd.returnResults();
        System.out.println();

        query = "Add Employee With firstName=John,lastName=Smith,middleInitial=A,ssn=222-44-3333,sex=M";
        System.out.println("Executing: " + query);
        cmd = Parser.getCommand(query);
        cmd.executeCommand();
        cmd.returnResults();
        System.out.println();

        query = "Add Employee With firstName=Barnabas,lastName=Something,middleInitial=J,ssn=232-88-34363,sex=M";
        System.out.println("Executing: " + query);
        cmd = Parser.getCommand(query);
        cmd.executeCommand();
        cmd.returnResults();
        System.out.println();

        query = "Set manager=4 For department.number = 2";
        System.out.println("Executing: " + query);
        cmd = Parser.getCommand(query);
        cmd.executeCommand();
        cmd.returnResults();
        System.out.println();

        query = "Get department.name = ADepartment || project.name = AProject || department.number = 4";
        System.out.println("Executing: " + query);
        cmd = Parser.getCommand(query);
        cmd.executeCommand();
        System.out.println("\nResults: {");
        cmd.returnResults();
        System.out.println("}");
        System.out.println();

        query = "Set supervisee=5 For employee.firstname = John";
        System.out.println("Executing: " + query);
        cmd = Parser.getCommand(query);
        cmd.executeCommand();
        cmd.returnResults();
        System.out.println();

        query = "Get employee.firstname = Barnabas";
        System.out.println("Executing: " + query);
        cmd = Parser.getCommand(query);
        cmd.executeCommand();
        System.out.println("\nResults: {");
        cmd.returnResults();
        System.out.println("}");
        System.out.println();

        query = "Get employee.firstname = John";
        System.out.println("Executing: " + query);
        cmd = Parser.getCommand(query);
        cmd.executeCommand();
        System.out.println("\nResults: {");
        cmd.returnResults();
        System.out.println("}");
        System.out.println();

        printLists();
    }

    private static void printLists() {
        System.out.println("Objects in database:");
        System.out.println("Employee: " + ClassesContainer.getClassList("Employee").size());
        System.out.println("Department:" + ClassesContainer.getClassList("Department").size());
        System.out.println("Project: " + ClassesContainer.getClassList("Project").size());
        System.out.println("Dependent: " + ClassesContainer.getClassList("Dependent").size());

    }
}

