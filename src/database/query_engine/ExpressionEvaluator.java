package database.query_engine;

import database.Classes.DatabaseClass;
import database.query_engine.treecomponents.BinaryNode;
import database.query_engine.treecomponents.ExpressionTreeGenerator;
import database.utilities.object_utilities.ClassesContainer;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashSet;

/**
 * Created by Kevin on 4/10/2017.
 */
public class ExpressionEvaluator {
    private BinaryNode root;


    public ExpressionEvaluator(String expression) {
        ExpressionTreeGenerator expressionTreeGenerator = new ExpressionTreeGenerator(expression);
        setRoot(expressionTreeGenerator.getExpressionTree());
    }

    private void setRoot(BinaryNode root) {
        //System.out.println("The root of the tree has been set to: " + root.getValue());
        this.root = root;
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

    public HashSet<DatabaseClass> getRecords() throws InvocationTargetException, IllegalAccessException {
        return inorderEval(root).getRecords();
    }

    private BinaryNode inorderEval(BinaryNode currentNode) throws InvocationTargetException, IllegalAccessException {
        BinaryNode left = null, right = null, result = null;

        //System.out.println("Current node is: " + currentNode.getValue());
        if (currentNode.getLeft() != null) {
            //System.out.println("Going to left child: " + currentNode.getLeft().getValue() + " of " + currentNode.getValue());
            left = inorderEval(currentNode.getLeft());
        }

        if (currentNode.getRight() != null) {
            //System.out.println("Going to right child: " + currentNode.getRight().getValue() + " of " + currentNode.getValue());
            right = inorderEval((currentNode.getRight()));
        }

        if (currentNode.getLeft() == null && currentNode.getRight() == null) {
            String[] pair = currentNode.getValue().split("\\.");
            if (pair.length > 1) {
            //    System.out.println("Adding the classes for " + pair[0]);
                currentNode.addToRecords(ClassesContainer.getClassList(pair[0]));
            }
        }

        if (currentNode.getLeft() != null && currentNode.getRight() != null) {
            //System.out.println("Evaluating for children of " + currentNode.getValue());
            currentNode = evaluate(left, currentNode, right);
        } else {
            addDefaultClassListToNode(currentNode);
        }

        return currentNode;
    }

    private void addDefaultClassListToNode(BinaryNode currentNode) {
        String[] pair = currentNode.getValue().split("\\.");
        if (pair.length > 1) {
            currentNode.addToRecords(ClassesContainer.getClassList(pair[0]));
        }
    }

    private String getDBObjectName(String dotStatement) {
        String[] pair = dotStatement.split("\\.");
        return pair[0];
    }

    private boolean isOR(String value) {
        return value.equals("||");
    }

    private boolean isAND(String value) {
        return value.equals("&&");
    }

    private boolean isDBObject(HashSet<DatabaseClass> records) {
        return (records.size() != 0);
    }

    private BinaryNode executeANDCondition(BinaryNode left, BinaryNode currentNode, BinaryNode right) {
        for (DatabaseClass rightRecord: right.getRecords()) {
            for (DatabaseClass leftRecord: left.getRecords()) {
                if (rightRecord.getOID() == leftRecord.getOID()) {
                    currentNode.addToRecords(leftRecord);
                }
            }
        }
        return currentNode;
    }

    private BinaryNode executeORCondition(BinaryNode left, BinaryNode currentNode, BinaryNode right) {
        for (DatabaseClass rightRecord: right.getRecords()) {
            currentNode.addToRecords(rightRecord);
        }
        for (DatabaseClass leftRecord: left.getRecords()) {
            currentNode.addToRecords(leftRecord);
        }
        return currentNode;

    }

    private BinaryNode evaluate(BinaryNode left, BinaryNode currentNode, BinaryNode right) throws InvocationTargetException, IllegalAccessException {
        if (isConditional(currentNode.getValue())) {
            return evaluateConditional(left, currentNode, right);
        } else {
            return evaluateExpression(left, currentNode,right);
        }
    }

    private boolean isConditional(String value) {
        return (value.equals("||") || value.equals("&&"));
    }

    private BinaryNode evaluateExpression(BinaryNode left, BinaryNode currentNode, BinaryNode right) throws InvocationTargetException, IllegalAccessException {
        if (isDBObject(left.getRecords()) && isDBObject(right.getRecords())) {
            return executeTwoDBObjectCase(left, currentNode, right);
        } else if (isDBObject(left.getRecords()) && !isDBObject(right.getRecords())) {
            return executeOneDBObjectCase(left, currentNode, right);
        } else {
            System.err.println("Invalid argument supplied. Must be DBObject operator operand");
        }
        return null;
    }

    private BinaryNode executeOneDBObjectCase(BinaryNode left, BinaryNode currentNode, BinaryNode right) throws InvocationTargetException, IllegalAccessException {
        for (DatabaseClass record: left.getRecords()) {
            boolean match = findResults(getMethod(left.getValue(), record).invoke(record), currentNode.getValue(), right.getValue());
            if (match) {
                currentNode.addToRecords(record);
            }
        }
        return currentNode;
    }

    private BinaryNode executeTwoDBObjectCase(BinaryNode left, BinaryNode currentNode, BinaryNode right) throws InvocationTargetException, IllegalAccessException {
        for (DatabaseClass rightRecord: right.getRecords()) {
            for (DatabaseClass leftRecord: left.getRecords()) {
                boolean match = findResults(getMethod(left.getValue(), leftRecord), currentNode.getValue(), getMethod(right.getValue(), rightRecord));
                if (match) {
                    System.out.println("Match found.");
                    currentNode.addToRecords(leftRecord);
                    currentNode.addToRecords(rightRecord);
                }
            }
        }
        return currentNode;
    }

    private BinaryNode evaluateConditional(BinaryNode left, BinaryNode currentNode, BinaryNode right) {
        if (isOR(currentNode.getValue())) {
            return executeORCondition(left,currentNode,right);
        } else if (isAND(currentNode.getValue())) {
            return executeANDCondition(left, currentNode, right);
        } else {
            return null;
        }
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
        try {
            for (Method method : dbClass.getClass().getMethods()) {
                if (method.getName().contains("get") && method.getName().toLowerCase().contains(methodName)) {
                    if (method.getName().toLowerCase().equals("get" + methodName)) {
                        return dbClass.getClass().getMethod(method.getName(), method.getParameterTypes());
                    }
                }
            }
        } catch (NoSuchMethodException e) {
            System.err.println("The method " + methodName + " does not exist for the class " + dbClass.getClass().getName());
        }
        return null;
    }
}
