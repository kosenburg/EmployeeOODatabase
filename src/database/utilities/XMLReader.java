package database.utilities;

import database.Classes.DatabaseClass;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.*;

/**
 * Created by bjoynes on 4/25/2017.
 */
public class XMLReader implements Runnable{

    private String type;
    private  File file;
    private SAXBuilder builder;
    private HashMap<String, String> attributes;
    DatabaseClass dbClass;
    ClassesContainer container;

    public XMLReader(String type) throws ClassNotFoundException {
        this.type = type.substring(0,1).toUpperCase() + type.substring(1);
        setAttributes();
        file = new File(type + ".xml");
        builder = new SAXBuilder();
    }

    private void setAttributes() throws ClassNotFoundException {
        attributes = new HashMap<String,String>();
        Class<DatabaseClass> clazz = (Class<DatabaseClass>) Class.forName("database.Classes."+ type);

        Field [] fields = clazz.getDeclaredFields();

         for(Field field: fields){
             attributes.put(field.getName(),"");

         }
    }

    @Override
    public void run() {
        try{
            Document doc = (Document) builder.build(file);



            Element root = doc.getRootElement();


            Set<String> keys = attributes.keySet();

            for(int i = 0; i < root.getChildren().size();i++) {

                for (String key : keys) {

                    if (key.equals("number") && root.getChild(type.toLowerCase()).getAttribute(key) != null) {
                        System.out.println(root.getChild(type.toLowerCase()).getAttributeValue(key));
                        attributes.replace(key, root.getChild(type.toLowerCase()).getAttributeValue(key));
                    } else if (key.equals("ssn") && root.getChild(type.toLowerCase()).getAttribute(key) != null) {
                        System.out.println(root.getChild(type).getAttributeValue(key));
                        attributes.replace(key, root.getChild(type.toLowerCase()).getAttributeValue(key));
                    } else if (root.getChild(type.toLowerCase()).getChild(key) != null) {
                        attributes.replace(key, root.getChild(type.toLowerCase()).getChild(key).getValue());
                    } else {
                        break;
                    }

                }

                dbClass = ObjectFactory.getObject(type,attributes);
                ClassesContainer.addClass(dbClass);
            }









            } catch (JDOMException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
