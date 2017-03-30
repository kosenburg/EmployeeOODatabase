package database.Commands;

import database.utilities.ClassesContainer;

/**
 * Created by Kevin on 3/25/2017.
 */
public interface Command {
    void executeCommand();
    void returnResults();
    void setParameters(String[] fields, String[] types, String[] conditions);
}
