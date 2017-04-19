package database.treecomponents;

import database.utilities.Evaluator;

import java.util.Stack;

/**
 * Created by Kevin on 4/18/2017.
 */
public class ExpressionTreeGenerator {
    private String expression;
    private String postFixExpression;
    private PostFixConverter postFixConverter;
    private Stack<BinaryNode> valueStack;
    private BinaryNode node;


    public ExpressionTreeGenerator(String expression) {
        setExpression(expression);
        setPostFixConverter();
        setPostFixExpression();
        setValueStack();
    }

    private void setValueStack() {
        valueStack = new Stack<>();
    }

    private void setExpression(String expression) {
        this.expression = expression;
    }

    private void setPostFixExpression() {
        postFixExpression = postFixConverter.convert();
    }

    private void setPostFixConverter() {
        postFixConverter = new PostFixConverter(expression);
    }

    public BinaryNode getExpressionTree() {
        System.out.println("Creating tree...");

        for (String value: postFixExpression.split(" ")) {
            if (isOperator(value)) {
                createSubTree(value);
                valueStack.push(node);
            } else {
                valueStack.push(new BinaryNode(value, null, null));
            }
        }
        System.out.println("Tree created.");
        return node;
    }

    private boolean isOperator(String value) {
        return (Evaluator.getPrecedence(value) != -1);
    }

    private void createSubTree(String value) {
        BinaryNode[] nodes = new BinaryNode[2];
        for (int i = 0; i < 2; i++) {
            nodes[i] = valueStack.pop();
        }
        node = new BinaryNode(value, nodes[1],nodes[0]);
    }
}