package database.treecomponents;

import database.Classes.DatabaseClass;
import database.utilities.Evaluator;

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

    public void addToRecords(ArrayList<DatabaseClass> records) {
        this.records.addAll(records);
    }

    @Override
    public int compareTo(Object o) {
        BinaryNode node = (BinaryNode) o;

        int thisPrecedence = Evaluator.getPrecedence(this.getValue());
        int thatPrecedence = Evaluator.getPrecedence(node.getValue());

        if (thisPrecedence > thatPrecedence) {
            return 1;
        } else if (thisPrecedence < thatPrecedence) {
            return -1;
        } else {
            return  0;
        }
    }

    public void addToRecords(DatabaseClass record) {
        records.add(record);
    }
}
