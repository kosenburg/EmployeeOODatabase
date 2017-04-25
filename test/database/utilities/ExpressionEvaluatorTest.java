package database.utilities;

import database.Classes.DatabaseClass;
import database.Classes.Dependent;
import database.Classes.Employee;
import org.junit.Test;

import java.util.HashSet;

/**
 * Created by Kevin on 4/10/2017.
 */
public class ExpressionEvaluatorTest {
    @Test

    public void convertToPostFix() throws Exception {
        //ExpressionEvaluator evaluator = new ExpressionEvaluator();
        //evaluator.convertToPostFix("dependent.name = John || dependent.name = Jim");

        ClassesContainer container = new ClassesContainer();
        Dependent dependent = new Dependent("John", "F", new Employee(attribute.get("firstName"), attribute.get("lastName"), attribute.get("middleInitial"), attribute.get("ssn"), attribute.get("sex")));
        System.out.println("first dependent ID: " + dependent.getOID());
        ClassesContainer.addClass(dependent);
        ClassesContainer.addClass(new Dependent("Jim", "F", new Employee(attribute.get("firstName"), attribute.get("lastName"), attribute.get("middleInitial"), attribute.get("ssn"), attribute.get("sex"))));
        ClassesContainer.addClass(new Dependent("John", "M", new Employee(attribute.get("firstName"), attribute.get("lastName"), attribute.get("middleInitial"), attribute.get("ssn"), attribute.get("sex"))));
        ClassesContainer.addClass(new Dependent("Tim", "M", new Employee(attribute.get("firstName"), attribute.get("lastName"), attribute.get("middleInitial"), attribute.get("ssn"), attribute.get("sex"))));

        ExpressionEvaluator evaluator = new ExpressionEvaluator("dependent.name = John || dependent.name = Jim");


        HashSet<DatabaseClass> classes = evaluator.getRecords();
        for (DatabaseClass dbClass: classes) {
            dependent = (Dependent) dbClass;
            System.out.println(((Dependent) dbClass).getName() + " id: " + dependent.getOID());
        }
    }

}