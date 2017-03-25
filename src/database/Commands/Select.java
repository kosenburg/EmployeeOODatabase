package database.Commands;

import database.utilities.ClassesContainer;

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

    @Override
    public void executeCommand() {

    }

    @Override
    public void returnResults() {

    }

    @Override
    public void setParameters(String... parameters) {
        searchKey = parameters[0];
        fromKey = parameters[1];

    }
}
