package database.utilities.object_utilities;

/**
 * Created by Kevin on 3/25/2017.
 */
public class IdGenerator {
    private static int id = 0;

    public synchronized static int getID() {
        id++;
        return id;
    }
}
