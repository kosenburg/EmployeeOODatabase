package database;

import database.Classes.Department;
import database.Classes.Dependent;
import database.Commands.Add;
import database.Commands.Command;
import database.utilities.ClassesContainer;
import database.utilities.Parser;

import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.HashMap;


public class Main {

    public static void main(String[] args) {


        String query = "Add Project With location=here,number=2,name=AProject";
        Command cmd = Parser.getCommand(query);
        cmd.executeCommand();
        cmd.returnResults();
        System.out.println();


        String query1 = "Add Department With location=here,name=ADepartment,number=2";
        cmd = Parser.getCommand(query1);
        cmd.executeCommand();
        cmd.returnResults();

        String query2 = "Add Employee With firstName=John,lastName=Smith,middleInitial=A,ssn=222-44-3333,M";
        cmd = Parser.getCommand(query2);
        cmd.executeCommand();
        cmd.returnResults();


        String query3 = "Add Dependent With name=Jim,birthDate=04/15/1980,sex=M,relationship=3";
        cmd = Parser.getCommand(query3);
        cmd.executeCommand();
        cmd.returnResults();


        String query4 = "Get dependent.name = Jim || dependent.name = John";
        cmd = Parser.getCommand(query4);
        cmd.executeCommand();
        cmd.returnResults();

        query = "Remove dependent.name = Jim";
        cmd = Parser.getCommand(query);
        cmd.executeCommand();
        cmd.returnResults();

        /*
        query = "Set employee=3 For department.num=2";
        cmd = Parser.getCommand(query);
        cmd.executeCommand();
        cmd.returnResults();
*/

        query = "Set employee=3 For department.num=2";
        System.out.println(query.indexOf("For"));
        System.out.println(query.charAt(query.indexOf("For") + 4));


        printLists();
    }

    private static void printLists() {
        System.out.println("Class list size:");
        System.out.println("Employee: " + ClassesContainer.getClassList("Employee").size());
        System.out.println("Department:" + ClassesContainer.getClassList("Department").size());
        System.out.println("Project: " + ClassesContainer.getClassList("Project").size());
        System.out.println("Dependent: " + ClassesContainer.getClassList("Dependent").size());

    }

}
