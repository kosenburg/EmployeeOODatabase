package database.utilities;

import database.Commands.Add;
import database.Commands.Command;
import database.Commands.Select;

import java.util.Arrays;
import java.util.StringJoiner;

/**
 * Created by Kevin on 3/30/2017.
 */
public class Parser {
    private static String commandName;
    private static String type;
    private static String attributes;
    private static String expression;
    private static ClassesContainer classContainer;

    public Parser() {}

    public static Command getCommand(String query) throws ClassNotFoundException {
        parseQuery(query);

        if (commandName.equals("add")) {
            return new Add(attributes.split(","), type);
        } else if (commandName.equals("get")) {
            return new Select(expression,type);
        } else if (commandName.equals("remove")) {

        } else {
            throw new IllegalArgumentException();
        }
        return null;
    }

    private static void parseQuery(String query) {
        String[] arguments = query.split(" ");
        commandName = arguments[0].toLowerCase();

        switch (commandName) {
            case "add":
                parseForAdd(arguments);
                break;
            case "get":
                parseForSelect(arguments);
                break;
            case "remove":
                parseForRemove(arguments);
                break;
        }

    }

    private static void parseForRemove(String[] arguments) {

    }

    private static void parseForSelect(String[] arguments) {
        /* Turn the arguments into string for expression evaluator */

        StringJoiner joiner = new StringJoiner(" ");

        for(String s: arguments) {
            joiner.add(s);
        }

         expression = joiner.toString().substring(4);

         type = arguments[1].split("\\.")[0];

    }

    private static void parseForAdd(String[] arguments) {
        type = arguments[1];
        attributes = arguments[3];
    }
}
