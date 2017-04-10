package database.utilities;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Kevin on 4/10/2017.
 */
public class ExpressionEvaluatorTest {
    @Test
    public void convertToPostFix() throws Exception {

        ExpressionEvaluator evaluator = new ExpressionEvaluator();

        System.out.println(evaluator.convertToPostFix("( ( x > 5 ) && ( y > 4 ) ) || ( z < 2 )"));
    }

}