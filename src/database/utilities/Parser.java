package database.utilities;

import database.Commands.*;

import javax.sound.midi.SysexMessage;

/**
 * Created by Kevin on 3/30/2017.
 */
public class Parser {
    private static String commandName;
    private static String type;
    private static String attributes;
    private static String expression;
    public Parser() {}

    public static Command getCommand(String query) {
        parseQuery(query);

        if (commandName.equals("add")) {
            return new Add(attributes.split(","), type);
        } else if (commandName.equals("get")) {
            return new Select(attributes);
        } else if (commandName.equals("remove")) {
            return new Remove(attributes);
        } else if (commandName.equals("set")) {
            return new Set(attributes.split(","), expression);
        } else {
            throw new IllegalArgumentException();
        }
    }

    private static void parseQuery(String query) {
        String[] arguments = query.split(" ");
        commandName = arguments[0].toLowerCase();

        if (commandName.equals("add")) {
            parseForAdd(arguments);
        } else if (commandName.equals("get")) {
            parseForSelect(arguments);
        } else if (commandName.equals("remove")) {
            parseForRemove(arguments);
        } else if (commandName.equals("set")) {
            parseForSet(query);
        }

    }

    private static void parseForSet(String arguments) {
        attributes = arguments.split(" ")[1];

        expression = arguments.substring(arguments.indexOf("For") + 4);

        /*for (int i = 1; i < arguments.length; i++) {
            attributes += arguments[i] + " ";
        }*/
    }


    private static void parseForSelect(String[] arguments) {
        attributes = "";
        for (int i = 1; i < arguments.length; i++) {
            attributes += arguments[i] + " ";
        }
    }

    private static void parseForRemove(String[] arguments) {
        parseForSelect(arguments);
    }


    private static void parseForAdd(String[] arguments) {
        type = arguments[1];
        attributes = arguments[3];
    }
}
