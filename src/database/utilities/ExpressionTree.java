package database.utilities;

import database.Classes.DatabaseClass;

/**
 * Created by Kevin on 4/10/2017.
 */
public class ExpressionTree {
    private BinaryNode root;


    public ExpressionTree(BinaryNode root) {
        this.root = root;
    }

    public void insert(String value) {
        if (value != null) {
            placeInTree(root, new BinaryNode(value));
        }
    }

    private BinaryNode placeInTree(BinaryNode currentNode, BinaryNode binaryNode) {
        int compareValue = currentNode.compareTo(binaryNode);

        if (compareValue < 0) {
            if (currentNode.getRight() != null) {
                currentNode.setRight(placeInTree(currentNode.getRight(), binaryNode));
            } else {
                currentNode.setRight(binaryNode);
            }
        } else if (compareValue > 0) {
            if (currentNode.getLeft() != null) {
                currentNode.setLeft(placeInTree(currentNode.getLeft(), binaryNode));
            } else {
                currentNode.setLeft(binaryNode);
            }
        } else {
            // its equal so do nothing
        }
        return currentNode;
    }

    public boolean isMatch(DatabaseClass dbClass) {

        return false;
    }
}
