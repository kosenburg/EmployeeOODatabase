package database.treecomponents;

import database.utilities.Evaluator;

import java.util.Stack;

/**
 * Created by Kevin on 4/18/2017.
 */
public class PostFixConverter {
    private static String postFixExpression = "";
    private String infixExpression;
    private Stack<String> operatorStack;
    private int currentPrecendence;

    public PostFixConverter(String infixExpression) {
        this.infixExpression = infixExpression;
        this.operatorStack = new Stack<>();
    }

    private boolean isOperator(String value) {
        return (currentPrecendence != -1);
    }

    private boolean isStackEmpty() {
        return (operatorStack.size() == 0);
    }

    private boolean isClosingParenthese(String value) {
        return value.equals(")");
    }

    private boolean isOpeningParenthese(String value) {
        return value.equals("(");
    }

    private void pushOperatorToStack(String part) {
        if (isStackEmpty()) {
            operatorStack.push(part);
        } else {
            if (isClosingParenthese(part)) {
                clearToOpeningParenthese(operatorStack);
                return;
            } else if (isOpeningParenthese(part)) {
                operatorStack.push(part);
                return;
            } else if (currentPrecendence >= Evaluator.getPrecedence(operatorStack.peek())) {
                clearToLowerPrec(operatorStack, currentPrecendence);
            }
            operatorStack.push(part);
        }
    }

    public String convert() {
        for (String part: infixExpression.split(" ")) {
            setCurrentPrecedence(part);
            if (isOperator(part)) {
                pushOperatorToStack(part);
            } else {
                postFixExpression += part + " ";
            }
        }
        clearRestOfStack();

        System.out.println("Internally generated postFixExpression: " + postFixExpression);
        return postFixExpression;
    }

    private void clearRestOfStack() {
        while (operatorStack.size() > 0) {
            postFixExpression += operatorStack.pop() + " ";
        }
    }

    private void setCurrentPrecedence(String part) {
        this.currentPrecendence = Evaluator.getPrecedence(part);
    }

    private void clearToOpeningParenthese(Stack<String> stack) {
        String operator;
        while (!(operator = stack.pop()).equals("(")) {
            if (!operator.equals("(") && !operator.equals(")")) {
                postFixExpression += operator + " ";
            }
        }
    }

    private void clearToLowerPrec(Stack<String> stack, int precedence) {
        while (stack.size() > 0 && precedence >= Evaluator.getPrecedence(stack.peek())) {
            String local = stack.pop();
            postFixExpression += local + " ";
        }
    }
}
