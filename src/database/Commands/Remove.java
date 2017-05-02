package database.Commands;

import database.Classes.DatabaseClass;
import database.utilities.object_utilities.ClassesContainer;

/**
 * Created by Kevin on 4/27/2017.
 */
public class Remove extends Select {
    Command command;

    public Remove(String expression) {
        super(expression);
    }

    @Override
    public void executeCommand() {
        super.executeCommand();
        removeClasses();
    }

    private void removeClasses() {
        if (classes != null) {
            for (DatabaseClass dbClass: classes) {
                ClassesContainer.removeClass(dbClass);
            }
        }
    }

    @Override
    public void returnResults() {
        System.out.println("Classes removed.");
    }
}
