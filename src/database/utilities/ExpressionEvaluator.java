package database.utilities;

import database.Classes.DatabaseClass;

import java.util.Stack;

/**
 * Created by Kevin on 4/10/2017.
 */
public class ExpressionEvaluator {
    private ExpressionTree tree;
    private String postFixExpression;

    public ExpressionEvaluator(){//String expression) {
        //convertToPostFix(expression);
        //setPostFixExpression(postFixExpression);
    }

    public String convertToPostFix(String expression) {
        Stack<String> localStack = new Stack<>();
        postFixExpression = "";

        for (String part: expression.split(" ")) {
            //System.out.println("Working on part: " + part);
            int precedence = getPrecedence(part);

            if (precedence != -1) {
                if (localStack.size() == 0) {
                  //  System.out.println("pushing " + part);
                    localStack.push(part);
                } else {
                    if (part.equals(")")) {
                        String operator;
                        while (!(operator = localStack.pop()).equals("(")) {
                            if (operator.equals("(") || operator.equals(")")) {

                            } else {
                //                System.out.println("popping " + operator);
                                postFixExpression += operator;
                            }
                        }
                        continue;
                    } else if (part.equals("(")) {
                        localStack.push(part);
                        continue;
                    } else if (precedence < getPrecedence(localStack.peek())) {
                        while (localStack.size() > 0 && precedence < getPrecedence(localStack.peek())) {
                            String local = localStack.pop();
                    //        System.out.println("Popping " + local);
                            postFixExpression += local;
                        }
                    }
              //      System.out.println("Pushing " + part);
                    localStack.push(part);
                }
            } else {
                postFixExpression += part;
            }
        }

        while (localStack.size() > 0) {
            //System.out.println("Expression: " + postFixExpression);
            postFixExpression += localStack.pop();
        }

        return postFixExpression;
    }

    public boolean isMatch(DatabaseClass object) {
        return false;
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

    public void setPostFixExpression(String postFixExpression) {
        this.postFixExpression = postFixExpression;
    }
}
