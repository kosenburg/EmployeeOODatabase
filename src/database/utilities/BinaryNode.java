package database.utilities;

import database.Classes.DatabaseClass;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * Created by Kevin on 4/11/2017.
 */
public class BinaryNode implements Comparable{
    private String value;
    private BinaryNode left;
    private BinaryNode right;
    private HashSet<DatabaseClass> records;

    public BinaryNode(String value, BinaryNode left, BinaryNode right) {
        records = new HashSet<>();
        setValue(value);
        setLeft(left);
        setRight(right);
    }

    public BinaryNode(String value) {
        this(value,null,null);
    }

    public BinaryNode getLeft() {
        return left;
    }

    public void setLeft(BinaryNode left) {
        this.left = left;
    }

    public BinaryNode getRight() {
        return right;
    }

    public void setRight(BinaryNode right) {
        this.right = right;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public HashSet<DatabaseClass> getRecords() {
        return records;
    }

    public void setRecords(HashSet<DatabaseClass> records) {
        this.records = records;
    }

    public void setRecords(ArrayList<DatabaseClass> records) {
        for (DatabaseClass dbClass: records) {
            records.add(dbClass);
        }
    }

    public void addClass(DatabaseClass aClass) {
        this.records.add(aClass);
    }

    public void setClasses(String name) {
    }

    @Override
    public int compareTo(Object o) {
        BinaryNode node = (BinaryNode) o;

        int thisPrecedence = ExpressionEvaluator.getPrecedence(this.getValue());
        int thatPrecedence = ExpressionEvaluator.getPrecedence(node.getValue());

        if (thisPrecedence > thatPrecedence) {
            return 1;
        } else if (thisPrecedence < thatPrecedence) {
            return -1;
        } else {
            return  0;
        }
    }
}
