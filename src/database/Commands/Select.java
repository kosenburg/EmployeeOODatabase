package database.Commands;

import database.Classes.DatabaseClass;
import database.Classes.Dependent;
import database.utilities.ClassesContainer;
import database.utilities.ExpressionEvaluator;
import database.utilities.UIController;
import database.utilities.XMLReader;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.HashSet;

/**
 * Created by Kevin on 3/25/2017.
 */
public class Select implements Command{
    private String searchKey;
    private String fromKey;
    private String expression;
    private ClassesContainer container;
    private UIController uicontroller;
    private XMLReader reader;
    private String type;

    public Select(String expression, String type) throws ClassNotFoundException {
        setType(type);
        this.expression = expression;
        reader = new XMLReader(type);
        reader.run();
        ClassesContainer.getClassList(type);
    }


    public Select(ClassesContainer classContainer) {

    }


    @Override
    public void executeCommand() throws InvocationTargetException, IllegalAccessException {

        ExpressionEvaluator evaluator = new ExpressionEvaluator(expression);


        HashSet<DatabaseClass> classes = evaluator.getRecords();


    }

    @Override
    public void returnResults() {

    }

    @Override
    public void setParameters(String[] fields, String[] types, String[] conditions) {

    }

    private void setType(String type) {
        this.type = type;
    }

    @Override
    public void setController(UIController controller) {
        this.uicontroller = controller;
    }

    public void setParameters(String userInput) {
        String[] values = userInput.split("Get");
        System.out.println(Arrays.toString(values));
    }


}
