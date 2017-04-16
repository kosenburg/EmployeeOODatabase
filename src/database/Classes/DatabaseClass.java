package database.Classes;

import database.utilities.IdGenerator;

/**
 * Created by Kevin on 4/15/2017.
 */
public class DatabaseClass {
    private long oid;

    public DatabaseClass() {
        setOID();
    }

    private void setOID() {
        oid = IdGenerator.getID();
    }

    public long getOID() {
        return oid;
    }

}
