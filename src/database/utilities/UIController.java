package database.utilities;

import database.Commands.Command;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.jdom2.JDOMException;

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


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    private void handleSubmitAction(ActionEvent event) throws JDOMException, IllegalAccessException, IOException, ClassNotFoundException, InvocationTargetException {
       String query = queryTxtField.getText();



        Command cmd = Parser.getCommand(query);
        cmd.setController(this);

        String commandType = cmd.getClass().toString().substring(24);


        textArea.appendText(commandType + " command created!\n");

        statusLbl.setText(commandType + " command is being executed.");
        cmd.executeCommand();


        cmd.returnResults();

        queryTxtField.setText("");
    }

    public void setTextArea(String s) {
       textArea.appendText(s + "\n");
    }

    public void setStatusLbl(String s){
        statusLbl.setText(s);
    }

}
