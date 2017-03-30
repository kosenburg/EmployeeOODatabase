package database.Commands;

import database.Classes.DatabaseClass;
import database.utilities.ClassesContainer;
import database.utilities.FieldContainer;
import database.utilities.ObjectFactory;

import java.util.HashMap;

public class Add implements Command {
    private String type;
    private HashMap<String,String> attributes;

    public Add(String[] fields, String type) {
        System.out.println("Creating Add command...");
        setAttributes();
        setType(type);
        collectFieldData(fields);
    }

    private void setAttributes() {
        attributes = new HashMap<>();
    }

    @Override
    public void executeCommand() {
        System.out.println("Command executed");
        DatabaseClass db = ObjectFactory.getObject(type,attributes);
        //System.out.println("Object created: " + db);
        if (db !=null) {
            ClassesContainer.addClass(db);
        }
    }

    @Override
    public void returnResults() {
        StringBuilder builder = new StringBuilder();
        builder.append("An " + type + " has been created with the following attributes: ");
        for (String key: attributes.keySet()) {
            builder.append(key + " = " + attributes.get(key) + ", ");
        }
        String output = builder.toString();
        System.out.println(output.substring(0,output.length() - 2));
    }

    @Override
    public void setParameters(String[] fields, String[] types, String[] conditions) {
        collectFieldData(fields);
    }

    private void collectFieldData(String[] fields) {
       // System.out.println("Collecting field data...");
        for (String field: fields) {
            String[] valuePair = field.split("=");
            if (FieldContainer.isInFields(valuePair[0], type)) {
                //System.out.println("Putting (" + valuePair[0] + ", " + valuePair[1] + ")");
                attributes.put(valuePair[0], valuePair[1]);
            }
        }
    }

    private void setType(String type) {
        //System.out.println("Type set to: " + type);
        this.type = type;
    }
}
