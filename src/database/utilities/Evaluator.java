package database.utilities;

import database.Classes.DatabaseClass;

import java.util.ArrayList;

/**
 * Created by Kevin on 4/14/2017.
 */
public class Evaluator {

    public static boolean evaluate(String operand1, String operator, String operand2) {
        System.out.println("Evaluating: " + operand1 + operator + operand2);
        switch (operator) {
            case "<":
                return Integer.parseInt(operand1) < Integer.parseInt(operand2);
            case ">":
                return Integer.parseInt(operand1) > Integer.parseInt(operand2);
            case ">=":
                return Integer.parseInt(operand1) >= Integer.parseInt(operand2);
            case "<=":
                return Integer.parseInt(operand1) <= Integer.parseInt(operand2);
            case "&&":
                return getBool(operand1) && getBool(operand2);
            case "||":
                return getBool(operand1) || getBool(operand2);
            case "=":
                return operand1.equals(operand2);
            default:
                throw new IllegalArgumentException();
        }
    }

    public static boolean evaluate(ArrayList<DatabaseClass> operand1, String operator, String operand2) {
        return false;
    }

    public static boolean evaluate(String operand1, String operator, ArrayList<DatabaseClass> operand2) {
        return false;
    }

    public static boolean evaluate(ArrayList<DatabaseClass> operand1, String operator, ArrayList<DatabaseClass> operand2) {
        return false;
    }

    private static boolean getBool(String operand2) {
        return operand2.equals("true");
    }

}
