package database.treecomponents;

import database.utilities.Evaluator;

import java.util.Stack;

/**
 * Created by Kevin on 4/18/2017.
 */
public class PostFixConverter {
    private static String postFixExpression = "";
    private String infixExpression;

    public PostFixConverter(String infixExpression) {
        this.infixExpression = infixExpression;
    }

    public String convert() {
        Stack<String> localStack = new Stack<>();
//        System.out.println("Converting to postfix...");

        for (String part: infixExpression.split(" ")) {
            //          System.out.println("Working on part: " + part);
            int precedence = Evaluator.getPrecedence(part);
            if (precedence != -1) {
                if (localStack.size() == 0) {
                    //                System.out.println("pushing " + part);
                    localStack.push(part);
                } else {
                    if (part.equals(")")) {
                        clearToClosingTag(localStack);
                        continue;
                    } else if (part.equals("(")) {
                        localStack.push(part);
                        continue;
                    } else if (precedence >= Evaluator.getPrecedence(localStack.peek())) {
                        clearToLowerPrec(localStack, precedence);
                    }
                    //          System.out.println("Pushing " + part);
                    localStack.push(part);
                }
            } else {
                postFixExpression += part + " ";
            }
        }

        while (localStack.size() > 0) {
            //System.out.println("Expression: " + postFixExpression);
            postFixExpression += localStack.pop() + " ";
        }
        System.out.println("Internally generated postFixExpression: " + postFixExpression);
        return postFixExpression;
    }

    private Stack<String> clearToClosingTag(Stack<String> stack) {
        String operator;
        while (!(operator = stack.pop()).equals("(")) {
            if (!operator.equals("(") && !operator.equals(")")) {
                postFixExpression += operator + " ";
            }
        }
        return stack;
    }

    private Stack<String> clearToLowerPrec(Stack<String> stack, int precedence) {
        while (stack.size() > 0 && precedence >= Evaluator.getPrecedence(stack.peek())) {
            String local = stack.pop();
            postFixExpression += local + " ";
        }
        return stack;
    }
}
