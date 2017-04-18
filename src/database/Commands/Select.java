package database.Commands;

import database.utilities.ClassesContainer;

import java.util.Arrays;

/**
 * Created by Kevin on 3/25/2017.
 */
public class Select implements Command{
    private String searchKey;
    private String fromKey;
    private ClassesContainer container;

    public Select(ClassesContainer container) {
        this.container = container;
    }

    public Select() {

    }
    @Override
    public void executeCommand() {

    }

    @Override
    public void returnResults() {

    }

    @Override
    public void setParameters(String[] fields, String[] types, String[] conditions) {

    }

    public void setParameters(String userInput) {
        String[] values = userInput.split("Get");
        System.out.println(Arrays.toString(values));
    }


}
