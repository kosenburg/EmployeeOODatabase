package database.utilities.xml_utilities;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;

import database.Classes.DatabaseClass;
import org.jdom2.*;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;


/**
 * Created by blakejoynes on 3/30/17.
 */
public class XMLWriter implements Runnable{
    private String type;

    private static HashMap<String,String> attributes;

    public XMLWriter(DatabaseClass databaseClass) throws IllegalAccessException {

        attributes = new HashMap<>();

        Field[] fields = databaseClass.getClass().getDeclaredFields();

        type = databaseClass.getClass().toString().substring(23);

        for(Field field: fields){
            field.setAccessible(true);

            System.out.println(field.getName());
            Object value = field.get(databaseClass);

            if(field.getType().getName().toString().equals("java.lang.String")){
                attributes.put(field.getName(),(String) value);

            }else if(field.getType().getName().toString().equals("int")){
                attributes.put(field.getName(), Integer.toString((Integer) value));
            }else{

            }
            System.out.println(value);
        }

    }



    @Override
    public void run() {
        String fileName = type + ".xml";

        File file = new File(fileName);


        //if the file doesn't exist, create new file with type as filename.
        if (!file.exists()) {
            try {

                //set root element
                Element root = new Element(type);

                //create XML document with root element
                Document doc = new Document(root);

                Element rootChild = new Element(type.toLowerCase());

                //traverse through attributes list and append to rootChild
                for (String key : attributes.keySet()) {
                    if (key.equals("number") || key.equals("ssn")) {
                        rootChild.setAttribute(new Attribute(key, attributes.get(key)));

                    } else {
                        rootChild.addContent(new Element(key).setText(attributes.get(key)));
                    }
                }

                doc.getRootElement().addContent(rootChild);

                XMLOutputter xmlOutput = new XMLOutputter();

                xmlOutput.setFormat(Format.getPrettyFormat());

                xmlOutput.output(doc, new FileWriter(type + ".xml"));

                System.out.println("File Saved!");

            } catch (IOException e) {
                e.printStackTrace();
            }


        }else{
         //appends to document if already created

            SAXBuilder sbuilder = new SAXBuilder();
            Document document;

            try {
                document = sbuilder.build(file);

                Element rootElement = document.getRootElement();
                Element child = new Element(type.toLowerCase());



                for (String key : attributes.keySet()) {
                    if (key.equals("number") || key.equals("ssn")) {
                        child.setAttribute(new Attribute(key, attributes.get(key)));

                    } else {
                        child.addContent(new Element(key).setText(attributes.get(key)));
                    }
                }

                rootElement.addContent(child);

                XMLOutputter xmlOutput = new XMLOutputter();

                // display nice nice
                xmlOutput.setFormat(Format.getPrettyFormat());

                xmlOutput.output(document, new FileWriter(type + ".xml"));

                System.out.println("File Saved!");


            } catch (JDOMException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
