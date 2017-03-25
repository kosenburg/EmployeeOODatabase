package database.utilities;

/**
 * Created by Kevin on 3/25/2017.
 */
public class IdGenerator {
    private static long id = 0;

    public synchronized static long getID() {
        id++;
        return id;
    }
}
