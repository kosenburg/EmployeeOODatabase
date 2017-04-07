package database.utilities;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
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
public class XMLSax extends Thread {

    static HashMap<String,String> attributes;

    public static void writeXML(String type, HashMap<String,String> attributes) {
      String fileName = type + ".xml";

      File file = new File(fileName);



        if (!file.exists()) {
            try {
                Element root = new Element(type);

                Document doc = new Document(root);


                Element rootChild = new Element(type.toLowerCase());


                for (String key : attributes.keySet()) {
                    if (key.equals("number") || key.equals("ssn")) {
                        rootChild.setAttribute(new Attribute(key, attributes.get(key)));

                    } else {
                        rootChild.addContent(new Element(key).setText(attributes.get(key)));
                    }
                }

                doc.getRootElement().addContent(rootChild);

                XMLOutputter xmlOutput = new XMLOutputter();

                // display nice nice
                xmlOutput.setFormat(Format.getPrettyFormat());

                xmlOutput.output(doc, new FileWriter(type + ".xml"));

                System.out.println("File Saved!");

            } catch (IOException e) {
                e.printStackTrace();
            }


        }else{
            appendToXML(file,type,attributes);
        }

    }

    public static void appendToXML(File file,String type, HashMap<String,String> attributes){
        SAXBuilder builder = new SAXBuilder();

        try {
            Document document = (Document) builder.build(file);


            for (String key : attributes.keySet()) {
                if (key.equals("number") || key.equals("ssn")) {
                    document.getRootElement().getChild(type.toLowerCase()).setAttribute(new Attribute(key, attributes.get(key)));

                } else {
                    document.getRootElement().addContent(new Element(key).setText(attributes.get(key)));
                }
            }

            XMLOutputter outputter =
                    new XMLOutputter(Format.getPrettyFormat());
            outputter.output(document, System.out);


        } catch (JDOMException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    public static void readXMLFile(String type) {

        SAXBuilder builder = new SAXBuilder();
        File file = new File(type + ".xml");

        try {

            Document document;
            document = (Document) builder.build(file);
            Element rootNode = document.getRootElement();



        } catch (JDOMException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


        @Override
    public void run() {

    }
}
