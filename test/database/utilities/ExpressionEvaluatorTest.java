package database.utilities;

import database.Classes.Dependent;
import database.Classes.Employee;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Kevin on 4/10/2017.
 */
public class ExpressionEvaluatorTest {
    @Test

    public void convertToPostFix() throws Exception {
        //ExpressionEvaluator evaluator = new ExpressionEvaluator();
        //evaluator.convertToPostFix("dependent.name = John || dependent.name = Jim");

        ClassesContainer container = new ClassesContainer();
        ClassesContainer.addClass(new Dependent("John", "F", new Employee()));
        ClassesContainer.addClass(new Dependent("Jim", "F", new Employee()));
        ClassesContainer.addClass(new Dependent("John", "M", new Employee()));
        ClassesContainer.addClass(new Dependent("Tim", "M", new Employee()));

        ExpressionEvaluator evaluator = new ExpressionEvaluator("dependent.name = John || dependent.name = Jim");


        evaluator.createTree();
    }

}