package database;

import database.Classes.Department;
import database.Commands.Add;
import database.Commands.Command;
import database.utilities.ClassesContainer;
import database.utilities.Parser;

import java.lang.reflect.Constructor;
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

    }
}
