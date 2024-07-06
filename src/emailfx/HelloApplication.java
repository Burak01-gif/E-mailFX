/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package emailfx;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;
import static javafx.application.Application.launch;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Parent table1=FXMLLoader.load(Objects.requireNonNull(getClass().getResource("hello-view.fxml")));
        Scene scene = new Scene(table1, 350, 200);
        stage.setTitle("Enter IP Address of host!");
        stage.setScene(scene);
        stage.setMinHeight(200);
        stage.setMinWidth(350);
        stage.show();
    }
    @Override
    public void stop() throws Exception {
        System.exit(0);
    }


    public static void main(String[] args) {
        launch();
    }
}