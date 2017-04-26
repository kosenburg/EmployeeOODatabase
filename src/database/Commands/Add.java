package database.Commands;

import database.Classes.DatabaseClass;
import database.utilities.*;
import org.jdom2.JDOMException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;



public class Add implements Command {
    private String type;
    private HashMap<String,String> attributes;
    private XMLWriter writer;
    private UIController uicontroller;

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
    public void executeCommand() throws JDOMException, IOException, IllegalAccessException {
        System.out.println("Command executed");

        uicontroller.setTextArea(this.getClass().toString().substring(24) + " command is being executed");
        DatabaseClass db = ObjectFactory.getObject(type,attributes);

        writer = new XMLWriter(db);

        //run after Class Object created



        /* Get the class values

        Field[] fields = classname.getDeclaredFields();
        for(Field field : fields) {
            field.setAccessible(true);
            String name = field.getName();
            Object value = field.get(db);
            System.out.println(value);
        }
  */
        //System.out.println("Object created: " + db);

        if (db !=null) {
            writer.run();
            uicontroller.setTextArea("Object stored in XML!");

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

        uicontroller.setTextArea(output.substring(0,output.length() - 2));
        System.out.println(output.substring(0,output.length() - 2));
    }

    @Override
    public void setParameters(String[] fields, String[] types, String[] conditions) {
        collectFieldData(fields);
    }

    @Override
    public void setController(UIController controller) {
        this.uicontroller = controller;
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
