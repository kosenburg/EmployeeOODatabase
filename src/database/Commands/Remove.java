package database.Commands;

import database.utilities.UIController;

/**
 * Created by blakejoynes on 3/30/17.
 */
public class Remove implements Command {

    private UIController uicontroller;

    @Override
    public void executeCommand() {

    }

    @Override
    public void returnResults() {

    }

    @Override
    public void setParameters(String[] fields, String[] types, String[] conditions) {

    }

    @Override
    public void setController(UIController controller) {
        this.uicontroller = controller;
    }
}
