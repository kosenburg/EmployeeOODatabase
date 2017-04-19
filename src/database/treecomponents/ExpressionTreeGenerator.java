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


    public ExpressionTreeGenerator(String expression) {
        setExpression(expression);
        setPostFixConverter();
        setPostFixExpression();
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
        Stack<BinaryNode> valueStack = new Stack<>();
        BinaryNode operator = null;

        //System.out.println(postFixExpression);
        for (String thing: postFixExpression.split(" ")) {
            //System.out.println("looking at " + thing);
            if (Evaluator.getPrecedence(thing) != -1) {
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
        System.out.println("Tree created.");
        return operator;
    }
}