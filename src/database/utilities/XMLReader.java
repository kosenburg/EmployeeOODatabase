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
    private String [] typeArray;
    private  File file;
    private SAXBuilder builder;
    private HashMap<String, String> attributes;
    DatabaseClass dbClass;
    ClassesContainer container;
    private int oid;

    public XMLReader(String type) throws ClassNotFoundException {
        this.type = type.substring(0,1).toUpperCase() + type.substring(1);
        setAttributes();
        file = new File(type + ".xml");
        builder = new SAXBuilder();
    }

    public XMLReader() throws JDOMException, IOException {
        oid = 0;
        file = new File("id.xml");
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


    public void setID() throws JDOMException, IOException {
        Document doc = (Document) builder.build(file);

        Element root = doc.getRootElement();
        oid = Integer.parseInt(root.getChild("oid").getValue());
        IdGenerator.setId(oid);
    }

    @Override
    public void run() {
        try{
            Document doc = (Document) builder.build(file);



            Element root = doc.getRootElement();

            String oid = root.getChild(type.toLowerCase()).getAttributeValue("oid");

            Set<String> keys = attributes.keySet();

            for(int i = 0; i < root.getChildren().size();i++) {

                if (root.getChild(type.toLowerCase()).getAttribute("oid").getName() == "oid" && root.getChild(type.toLowerCase()).getAttribute("oid") != null) {
                    System.out.println(root.getChild(type.toLowerCase()).getAttributeValue("oid"));
                    attributes.put("oid",root.getChild(type.toLowerCase()).getAttributeValue("oid"));
                }

                for (String key : keys) {
                   if (root.getChild(type.toLowerCase()).getChild(key) != null) {
                        attributes.replace(key, root.getChild(type.toLowerCase()).getChild(key).getValue());
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
