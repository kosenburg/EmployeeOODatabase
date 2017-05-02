package database.utilities;

import database.Classes.DatabaseClass;
import database.Commands.Command;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.jdom2.JDOMException;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by blakejoynes on 4/20/17.
 */
public class UIController implements Initializable {

    @FXML private TextField queryTxtField;
    @FXML private TextArea textArea;
    @FXML private Label statusLbl;

    private XMLReader idReader;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //Read Previous ID
        readIDXML();

        //read all other XML
        readObjectXML();


    }

    @FXML
    private void handleSubmitAction(ActionEvent event) throws JDOMException, IllegalAccessException, IOException, ClassNotFoundException, InvocationTargetException {
       String query = queryTxtField.getText();



        Command cmd = Parser.getCommand(query);
        cmd.setController(this);

        String commandType = cmd.getClass().toString().substring(24);


        textArea.appendText(commandType + " command created!\n");

        cmd.executeCommand();


        cmd.returnResults();

        queryTxtField.setText("");
    }

    //on close save state of db (id)


    public void setTextArea(String s) {
       textArea.appendText(s + "\n");
    }

    public void setStatusLbl(String s){
        statusLbl.setText(s);
    }

    private void readIDXML(){
        try {
            idReader = new XMLReader();
            idReader.setID();
        } catch (JDOMException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readObjectXML(){



       String [] types = {"Department","Dependent","Employee","Project"};
        for(String type: types) {
            boolean check = new File(type + ".xml").exists();


            if(check == true){
                XMLReader objectReader = null;
                try {
                    objectReader = new XMLReader(type);
                } catch (ClassNotFoundException e) {
                }
                objectReader.run();
                this.setTextArea("XML loaded for object of type " + type + "! There are " + ClassesContainer.getClassList(type).size() + " objects.\n");
                System.out.println("XML for object of " + type + " found! There are " + ClassesContainer.getClassList(type).size() + " objects.");

            }else{
                this.setTextArea("There is no XML for the object of type " + type + ".\n");
                System.out.println("No XML for object of " + type + " found.");

            }
            }


    }


}
