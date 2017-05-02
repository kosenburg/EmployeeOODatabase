package database.query_engine;

import database.Classes.DatabaseClass;

import java.util.ArrayList;

/**
 * Created by Kevin on 4/14/2017.
 */
public class Evaluator {

    public static boolean evaluate(String operand1, String operator, String operand2) {
        //System.out.println("Evaluating: " + operand1 + operator + operand2);
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

    public static int getPrecedence(String operator) {
        if (operator.equals("(") || operator.equals(")")) {
            return 1;
        } else if (operator.equals(">") || operator.equals("<") || operator.equals(">=") || operator.equals("<=")) {
            return 2;
        } else if (operator.equals("*") || operator.equals("/")) {
            return 3;
        } else if (operator.equals("+") || operator.equals("-")) {
            return 4;
        } else if (operator.equals("=") || operator.equals("")) {
            return 5;
        } else if (operator.equals("&&")) {
            return 6;
        } else if (operator.equals("||")) {
            return 7;
        } else {
            return -1;
        }
    }
}
