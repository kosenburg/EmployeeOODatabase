package database.utilities;

/**
 * Created by Kevin on 4/10/2017.
 */
public class TestWhere {
    public static void main(String[] args) {
        /*Stack<BinaryNode> valueStack = new Stack<>();

        BST tree = new BST();


        String equation = "1 2 < 3 4 > && 3 4 = 5 5 = || ||";

        BinaryNode operator = null;
        for (String thing:equation.split(" ")) {
            if (isOperator(thing)) {
                //System.out.println("Operator: " + thing);
                BinaryNode[] nodes = new BinaryNode[2];
                for (int i = 0; i < 2; i++) {
                    nodes[i] = valueStack.pop();
                }
                operator = new BinaryNode(thing, nodes[1],nodes[0]);
                valueStack.push(operator);
            } else {
                //System.out.println("Value: " + thing);
                valueStack.push(new BinaryNode(thing, null, null));
            }
        }

        System.out.println(operator.getLeft().getValue());

        inorderTraversal(operator);

    }

    private static String inorderTraversal(BinaryNode operator) {
        String result1 = "";
        if (operator.getLeft() != null) {
            result1 = inorderTraversal(operator.getLeft());
        }

        //System.out.print(operator.getValue());

        String result2 = "";
        if (operator.getRight() != null) {
            result2 = inorderTraversal(operator.getRight());
        }

        if (operator.getLeft() != null && operator.getRight() != null) {
            System.out.println("************************************");
            boolean value = evaluate(result1,operator.getValue(),result2);

            if (value) {
                System.out.println("Result: true");
                return "true";
            } else {
                System.out.println("Result: false");
                return "false";
            }
        }
        return operator.getValue();
    }

    private static boolean isOperator(String thing) {
        switch (thing) {
            case "<":
                return true;
            case ">":
                return true;
            case "&&":
                return true;
            case "||":
                return true;
            case "=":
                return true;
            default:
                return false;
        }
    }

    private static boolean evaluate(String operand1, String operator, String operand2) {
        System.out.println("Evaluating: " + operand1 + operator + operand2);
        switch (operator) {
            case "<":
                return Integer.parseInt(operand1) < Integer.parseInt(operand2);
            case ">":
                return Integer.parseInt(operand1) > Integer.parseInt(operand2);
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

    private static boolean getBool(String operand2) {
        return operand2.equals("true");
        */
    }
}
