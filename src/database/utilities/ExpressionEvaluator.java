package database.utilities;

/**
 * Created by Kevin on 4/10/2017.
 */
public class ExpressionEvaluator {
    private ExpressionTree tree;

    public ExpressionEvaluator() {

    }

    public static int getPrecedence(String operator) {
        switch (operator) {
            case "(":
                return 1;
            case ")":
                return 1;
            case "*":
                return 2;
            case "/":
                return 2;
            case "+":
                return 3;
            case "-":
                return 3;
            case "=":
                return 4;
            case "!=":
                return 4;
            default:
                throw new IllegalArgumentException();
        }
    }
}
