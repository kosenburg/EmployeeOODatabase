package database.utilities;

import database.Commands.Add;
import database.Commands.Command;

/**
 * Created by Kevin on 3/30/2017.
 */
public class Parser {
    private static String commandName;
    private static String type;
    private static String attributes;
    public Parser() {}

    public static Command getCommand(String query) {
        parseQuery(query);

        if (commandName.equals("add")) {
            return new Add(attributes.split(","), type);
        } else if (commandName.equals("select")) {

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
            case "select":
                parseForSelect(arguments);
            case "remove":
                parseForRemove(arguments);
        }

    }

    private static void parseForRemove(String[] arguments) {

    }

    private static void parseForSelect(String[] arguments) {

    }

    private static void parseForAdd(String[] arguments) {
        type = arguments[1];
        attributes = arguments[3];
    }
}
