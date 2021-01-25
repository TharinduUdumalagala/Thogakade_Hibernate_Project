package lk.ijse.thogakade;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AppInitializer extends Application {

    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("view/DashBoardForm.fxml"))));
        primaryStage.setTitle("Thogakade");
        primaryStage.centerOnScreen();
        primaryStage.show();

    }
}
