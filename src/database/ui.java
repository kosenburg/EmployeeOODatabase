package database; /**
 * Created by blakejoynes on 4/20/17.
 */

import database.utilities.UIController;
import database.utilities.object_utilities.IdGenerator;
import database.utilities.xml_utilities.XMLWriter;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ui extends Application {

    private UIController uicontroller;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("view/mainUI.fxml"));

            Scene scene = new Scene(root);

            stage.setTitle("Employee Object Oriented Database");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void stop() throws IOException {
        System.out.println("Stage is closing");
        // Save ID
         XMLWriter iDWriter = new XMLWriter();
         iDWriter.saveID();

    }


    public void setController(UIController controller) {
        this.uicontroller = controller;
    }


}
