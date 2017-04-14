package database.utilities;

import database.Classes.DatabaseClass;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Stack;

/**
 * Created by Kevin on 4/10/2017.
 */
public class ExpressionEvaluator {
    private ExpressionTree tree;
    private String postFixExpression;
    private BinaryNode root;
    private ArrayList<DatabaseClass> results;

    public ExpressionEvaluator(String expression) {
        convertToPostFix(expression);
        //setPostFixExpression(postFixExpression);
    }

    private void convertToPostFix(String expression) {
        Stack<String> localStack = new Stack<>();
        postFixExpression = "";
        System.out.println("Converting to postfix...");

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
            postFixExpression += localStack.pop() + " ";
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
        System.out.println("Creating tree...");
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
        //inorderTraversal(root);
        System.out.println("Beginning evalulation..");
        inorderEval(root);
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

    private BinaryNode inorderEval(BinaryNode currentNode) {
        BinaryNode left = null, right = null, result = null;

        System.out.println("Current node is: " + currentNode.getValue());
        if (currentNode.getLeft() != null) {
            System.out.println("Going to left child: " + currentNode.getLeft().getValue() + " of " + currentNode.getValue());
            left = inorderEval(currentNode.getLeft());
        }


        //System.out.print(currentNode.getValue());

        if (currentNode.getRight() != null) {
            System.out.println("Going to right child: " + currentNode.getLeft().getValue() + " of " + currentNode.getValue());
            right = inorderEval((currentNode.getRight()));
        }

        if (currentNode.getLeft() != null && currentNode.getRight() != null) {
            // calculate equation part
            // result = evaluate(left, currentNode.getValue(), right);
            System.out.println("Evaluating for children of " + currentNode.getValue());
            currentNode.setClasses(test(left,currentNode,right));
        } else {
            if (isList(currentNode.getValue())) {
                System.out.println("Node is a leaf and a class.");
                String[] parts = currentNode.getValue().split("\\.");
                System.out.println("Adding to nodes classes list");
                currentNode.setClasses(ClassesContainer.getClassList(parts[0]));
            }
        }
        return currentNode;
    }


    private ArrayList<DatabaseClass> test(BinaryNode left, BinaryNode operator, BinaryNode right) {
        ArrayList<DatabaseClass> results = null;
        if ((left.getClasses() != null) && (right.getClasses()) != null) {
            System.out.println("Not implemented");
            //results = evaluate(left, operator.getValue(), right);
        } else if (left.getClasses() != null) {
            results = evaluate(left, operator.getValue(),right.getValue());
        } else if (right.getClasses() != null) {
            System.out.println("Not implemented");
            //results = evaluate(left.getValue(), operator.getValue(), right.getClasses());
        } else {
            throw new IllegalArgumentException();
        }
        return results;
    }


    private ArrayList<DatabaseClass> evaluate(BinaryNode left, String value, String value1) {
        ArrayList<DatabaseClass> results = new ArrayList<>();
        String[] parts = left.getValue().split("\\.");
        System.out.println("Evaluating with class: " + parts[0] + " for method: " + parts[1]);
        for (DatabaseClass dbClass: left.getClasses()) {
            boolean result = solve(parts[1], dbClass, value, value1);
            if (result) {
                System.out.println("Result is a match!");
                results.add(dbClass);
            }
        }
        return results;
    }

    private boolean solve(String methodName, DatabaseClass dbClass, String operator, String operand) {
        try {
            System.out.println("Getting method name");
            Method method = getMethod(methodName, dbClass);
            System.out.println("Solving..");
            return findResults(method.invoke(dbClass), operator, operand);
        } catch (InvocationTargetException | IllegalAccessException e) {
            System.out.println("An error occured while invoking selected method due to: " + e.getMessage());
        }
        return false;
    }

    private boolean findResults(Object invoke, String operator, String operand2) {
        String operand1 = (String) invoke;
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
    }

    private Method getMethod(String methodName, DatabaseClass dbClass) {
        try {
            for (Method method : dbClass.getClass().getMethods()) {
                if (method.getName().contains("get") && method.getName().contains(methodName)) {
                    return dbClass.getClass().getMethod(method.getName(), method.getParameterTypes());
                }
            }
        } catch (NoSuchMethodException e) {
            System.err.println("The method " + methodName + " does not exist for the class" + dbClass.getClass().getName());
        }
        return null;
    }



    private boolean isList(String pair) {
        return (pair.contains("\\.") && (ClassesContainer.getClassList(pair.split("\\.")[0])) != null);
    }

    public static int getPrecedence(String operator) {
        switch (operator) {
            case "(":
            case ")":
                return 1;
            case ">":
            case "<":
            case ">=":
            case "<=":
                return 2;
            case "*":
            case "/":
                return 3;
            case "+":
            case "-":
                return 4;
            case "=":
            case "":
                return 5;
            case "&&":
                return 6;
            case "||":
                return 7;
            default:
                return -1;
        }
    }

    public void setPostFixExpression(String postFixExpression) {
        this.postFixExpression = postFixExpression;
    }

}
