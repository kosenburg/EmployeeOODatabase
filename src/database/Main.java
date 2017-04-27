package database;

import database.Classes.Department;
import database.Commands.Add;
import database.Commands.Command;
import database.utilities.ClassesContainer;
import database.utilities.Parser;
import org.jdom2.JDOMException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;


public class Main {

    public static void main(String[] args) throws ParserConfigurationException, IOException, IllegalAccessException, TransformerException, ClassNotFoundException, InvocationTargetException, JDOMException {


        String query = "Add Project With location=here,number=2,name=AProject";
        Command cmd = Parser.getCommand(query);
        cmd.executeCommand();
        cmd.returnResults();
        System.out.println();

        String query1 = "Add Department With location=here,name=ADepartment,number=2";
        cmd = Parser.getCommand(query1);
        cmd.executeCommand();
        cmd.returnResults();
        System.out.println();

        String query2 = "Add Employee With firstName=John,lastName=Smith,middleInitial=A,ssn=222-44-3333,sex=M";
        cmd = Parser.getCommand(query2);
        cmd.executeCommand();
        cmd.returnResults();
        System.out.println();

        query = "Set manager=3 For department.number = 2";
        cmd = Parser.getCommand(query);
        cmd.executeCommand();
        cmd.returnResults();
        System.out.println();

        query = "Get department.name = ADepartment || project.name = AProject";
        cmd = Parser.getCommand(query);
        cmd.executeCommand();
        System.out.println("\nResults: {");
        cmd.returnResults();
        System.out.println("}");
        System.out.println();
/*
        String query3 = "Add Dependent With name=Jim,birthDate=04/15/1980,sex=M,relationships=3";
        cmd = Parser.getCommand(query3);
        cmd.executeCommand();
        cmd.returnResults();
        System.out.println();

        String query4 = "Get dependent.name = Jim || dependent.name = John";
        cmd = Parser.getCommand(query4);
        cmd.executeCommand();
        cmd.returnResults();
        System.out.println();

        query = "Remove dependent.name = Jim";
        cmd = Parser.getCommand(query);
        cmd.executeCommand();
        cmd.returnResults();
        System.out.println();

        query = "Set employee=3 For department.number = 2";
        cmd = Parser.getCommand(query);
        cmd.executeCommand();
        cmd.returnResults();
        System.out.println();
*/
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




/* old
        String query = "Add Project With location=here,number=2,name=AProject";
        Command cmd = Parser.getCommand(query);
        cmd.executeCommand();
        cmd.returnResults();



        String query3 = "Add Project With location=there,number=1,name=NewProject";
        cmd = Parser.getCommand(query3);
        cmd.executeCommand();
        cmd.returnResults();


        System.out.println();



        String query1 = "Add Department With location=here,name=ADepartment,number=2";
        cmd = Parser.getCommand(query1);
        cmd.executeCommand();
        cmd.returnResults();


        String query2 = "Get dependent.name = Jim || dependent.name = John";
        cmd = Parser.getCommand(query2);
*/

