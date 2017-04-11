package database.utilities;

import database.Classes.DatabaseClass;

import java.util.Stack;

/**
 * Created by Kevin on 4/10/2017.
 */
public class ExpressionEvaluator {
    private ExpressionTree tree;
    private String postFixExpression;
    private BinaryNode root;

    public ExpressionEvaluator(String expression) {
        convertToPostFix(expression);
        //setPostFixExpression(postFixExpression);
    }

    private void convertToPostFix(String expression) {
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
                                postFixExpression += operator + " ";
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
                            postFixExpression += local + " ";
                        }
                    }
              //      System.out.println("Pushing " + part);
                    localStack.push(part);
                }
            } else {
                postFixExpression += part + " ";
            }
        }

        while (localStack.size() > 0) {
            //System.out.println("Expression: " + postFixExpression);
            postFixExpression += localStack.pop();
        }

    }

    public boolean isMatch(DatabaseClass object) {
        // getCorrectList, for multiple objects can store in hashmap by name?
        // for each key in the list map
        //  get the list
        //      for chosen list pick a value
        //          run through tree with one from every other list and keep cycling
        // start evaluation
        // before each eval, check to make sure the name is a field
        // if not throw exception
        return false;
    }

    public void createTree() {
        Stack<BinaryNode> valueStack = new Stack<>();
        BinaryNode operator = null;
        System.out.println(postFixExpression);
        for (String thing:postFixExpression.split(" ")) {
            System.out.println("looking at " + thing);
            if (getPrecedence(thing) != -1) {
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
        root = operator;
        inorderTraversal(root);
    }

    private BinaryNode inorderTraversal(BinaryNode currentNode) {
        if (currentNode.getLeft() != null) {
            currentNode.setLeft(inorderTraversal(currentNode.getLeft()));
        }

        System.out.print(currentNode.getValue());

        if (currentNode.getRight() != null) {
            currentNode.setRight(inorderTraversal(currentNode.getRight()));
        }

        return currentNode;
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
