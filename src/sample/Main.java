package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
///

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Button button01 = new Button();
        button01.setText("Write Hallo World");
        button01.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {
                System.out.println("Hallo World!");
            }
        });
        // ---------------------------------------------------------
        // Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        // root.getChildrenUnmodifiable().add(button01);
        StackPane root = new StackPane();
        root.getChildren().add(button01);
        // -----------------------------------------------------------------
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
