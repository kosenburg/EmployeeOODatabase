package database.utilities.xml_utilities;

import database.Classes.DatabaseClass;
import database.utilities.object_utilities.ClassesContainer;
import database.utilities.object_utilities.IdGenerator;
import database.utilities.object_utilities.ObjectFactory;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import java.io.File;
import java.io.FileWriter;
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

            List<Element> childrenList = root.getChildren();

            Set<String> keys = attributes.keySet();

            for(int i = 0; i < root.getChildren().size();i++) {

                Element currentchild = childrenList.get(i);

                if (currentchild.getAttribute("oid").getName() == "oid" && currentchild.getAttribute("oid") != null) {
                    System.out.println(currentchild.getAttributeValue("oid"));
                    attributes.put("oid",currentchild.getAttributeValue("oid"));
                }

                for (String key : keys) {
                   if (currentchild.getChild(key) != null) {
                        attributes.replace(key, currentchild.getChild(key).getValue());
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

    public void remove(DatabaseClass dbClass,String fieldName) throws IllegalAccessException, JDOMException, IOException {

        oid = dbClass.getOID();

        Document doc = (Document) builder.build(file);

        Element root = doc.getRootElement();

        List<Element> childrenList  = root.getChildren();

        for(Element child: childrenList){
           if(Integer.parseInt(child.getAttributeValue("oid")) == oid){
            root.removeContent(child);
           }

        }

        if(childrenList.size() == 0){
         file.delete();
         System.out.println(file + " is deleted!");
        }else {


            XMLOutputter xmlOutput = new XMLOutputter();

            // display nice nice
            xmlOutput.setFormat(Format.getPrettyFormat());
            xmlOutput.output(doc, new FileWriter(file));

        }
    }
}
