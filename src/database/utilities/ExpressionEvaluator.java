package database.utilities;


import database.Classes.DatabaseClass;
import database.Classes.Dependent;

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

    public ExpressionEvaluator() {

    }

    public ExpressionEvaluator(String expression) {
        convertToPostFix(expression);
        //setPostFixExpression(postFixExpression);
    }

    public void convertToPostFix(String expression) {
        Stack<String> localStack = new Stack<>();
        postFixExpression = "";
//        System.out.println("Converting to postfix...");

        for (String part: expression.split(" ")) {
  //          System.out.println("Working on part: " + part);
            int precedence = getPrecedence(part);

            if (precedence != -1) {
                if (localStack.size() == 0) {
    //                System.out.println("pushing " + part);
                    localStack.push(part);
                } else {
                    if (part.equals(")")) {
                        String operator;
                        while (!(operator = localStack.pop()).equals("(")) {
                            if (operator.equals("(") || operator.equals(")")) {

                            } else {
      //                          System.out.println("popping " + operator);
                                postFixExpression += operator + " ";
                            }
                        }
                        continue;
                    } else if (part.equals("(")) {
                        localStack.push(part);
                        continue;
                    } else if (precedence >= getPrecedence(localStack.peek())) {
                        while (localStack.size() > 0 && precedence >= getPrecedence(localStack.peek())) {
                            String local = localStack.pop();
        //                    System.out.println("Popping " + local);
                            postFixExpression += local + " ";
                        }
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

        System.out.println(postFixExpression);
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
        //System.out.println(postFixExpression);
        for (String thing:postFixExpression.split(" ")) {
            //System.out.println("looking at " + thing);
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
        try {
            BinaryNode node = inorderEval(root);
            for (DatabaseClass dbClass: node.getRecords()) {
                Dependent dependent = (Dependent) dbClass;
                System.out.println(((Dependent) dbClass).getName());
            }
        } catch (InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
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

    private BinaryNode inorderEval(BinaryNode currentNode) throws InvocationTargetException, IllegalAccessException {
        BinaryNode left = null, right = null, result = null;

        System.out.println("Current node is: " + currentNode.getValue());
        if (currentNode.getLeft() != null) {
            System.out.println("Going to left child: " + currentNode.getLeft().getValue() + " of " + currentNode.getValue());
            left = inorderEval(currentNode.getLeft());
        }

        if (currentNode.getRight() != null) {
            System.out.println("Going to right child: " + currentNode.getRight().getValue() + " of " + currentNode.getValue());
            right = inorderEval((currentNode.getRight()));
        }

        if (currentNode.getLeft() == null && currentNode.getRight() == null) {
            String[] pair = currentNode.getValue().split("\\.");
            if (pair.length > 1) {
                System.out.println("Adding the classes for " + pair[0]);
                currentNode.addToRecords(ClassesContainer.getClassList(pair[0]));
            }
        }

        if (currentNode.getLeft() != null && currentNode.getRight() != null) {
            System.out.println("Evaluating for children of " + currentNode.getValue());
            currentNode = evaluate(left, currentNode, right);
        }

        return currentNode;
    }

    // TODO grab the methods before passing
    private BinaryNode evaluate(BinaryNode left, BinaryNode currentNode, BinaryNode right) throws InvocationTargetException, IllegalAccessException {
        if (currentNode.getValue().equals("||")) {
            for (DatabaseClass rightRecord: right.getRecords()) {
                currentNode.addToRecords(rightRecord);
            }

            for (DatabaseClass leftRecord: left.getRecords()) {
                currentNode.addToRecords(leftRecord);
            }

            return currentNode;
        } else if (currentNode.getValue().equals("&&")) {
            for (DatabaseClass rightRecord: right.getRecords()) {
                for (DatabaseClass leftRecord: left.getRecords()) {
                    if (rightRecord.getOID() == leftRecord.getOID()) {
                        currentNode.addToRecords(leftRecord);
                    }
                }
            }
            return currentNode;
        }


        if (left.getRecords().size() != 0 && right.getRecords().size() != 0) {
            System.out.println("Hitting case: (list, val, list)");
            for (DatabaseClass rightRecord: right.getRecords()) {
                for (DatabaseClass leftRecord: left.getRecords()) {
                    boolean isMatch = findResults(getMethod(left.getValue(), leftRecord), currentNode.getValue(), getMethod(right.getValue(), rightRecord));
                    if (isMatch) {
                        System.out.println("Match found.");
                        currentNode.addToRecords(leftRecord);
                        currentNode.addToRecords(rightRecord);
                    }
                }
            }
        } else if (left.getRecords().size() != 0 && right.getRecords().size() == 0) {
            System.out.println("Hitting case: (list, val, val)");
            for (DatabaseClass record: left.getRecords()) {
                boolean isMatch = findResults(getMethod(left.getValue(), record).invoke(record), currentNode.getValue(), right.getValue());
                if (isMatch) {
                    System.out.println("Match found.");
                    currentNode.addToRecords(record);
                }
            }
        } else if (left.getRecords().size() == 0) {
            System.out.println("Hitting case: (val, val, list");
            for (DatabaseClass rightRecord: right.getRecords()) {
                boolean result = findResults(left.getValue(), currentNode.getValue(), rightRecord);
            }
        }

        return currentNode;
    }

    private String getAttributeName(String dotExpression) {
        String[] temp = dotExpression.split("\\.");

        return temp[1];
    }


    private boolean findResults(Object operand1, String operator, Object operand2) {


        if ((operand1 instanceof String) && (operand2 instanceof String)) {
            return Evaluator.evaluate((String) operand1, operator, (String) operand2);
        } else if ((operand1 instanceof ArrayList<?>) && (operand2 instanceof ArrayList<?>)) {
            return Evaluator.evaluate((ArrayList<DatabaseClass>) operand1, operator, (ArrayList<DatabaseClass>) operand2);
        } else if (operand1 instanceof ArrayList<?>) {
            return Evaluator.evaluate((ArrayList<DatabaseClass>) operand1, operator, (String) operand2);
        } else {
            return Evaluator.evaluate((String) operand1, operator, (ArrayList<DatabaseClass>) operand2);
        }

    }

    private Method getMethod(String methodName, DatabaseClass dbClass) {
        methodName = getAttributeName(methodName);
        System.out.println("Requested method " + methodName);
        try {
            for (Method method : dbClass.getClass().getMethods()) {
                if (method.getName().contains("get") && method.getName().toLowerCase().contains(methodName)) {
                    Method innerMethod = dbClass.getClass().getMethod(method.getName(), method.getParameterTypes());
                    System.out.println("Getting method " + innerMethod.getName());
                    return innerMethod;
                }
            }
        } catch (NoSuchMethodException e) {
            System.err.println("The method " + methodName + " does not exist for the class " + dbClass.getClass().getName());
        }
        return null;
    }



    private boolean isList(String pair) {
        return (pair.contains("\\.") && (ClassesContainer.getClassList(pair.split("\\.")[0])) != null);
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
