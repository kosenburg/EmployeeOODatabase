package database.Classes;

import database.utilities.object_utilities.IdGenerator;

/**
 * Created by Kevin on 4/15/2017.
 */
public class DatabaseClass {
    private int oid;

    public DatabaseClass() {
        setOID();
    }


    private void setOID() {
        oid = IdGenerator.getID();
        System.out.println("Setting oid: " + oid);
    }

    public int getOID() {
        return oid;
    }

}
