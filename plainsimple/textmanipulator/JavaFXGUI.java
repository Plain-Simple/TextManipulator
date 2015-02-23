package plainsimple.textmanipulator;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class JavaFXGUI extends Application {
  @Override
  public void start(Stage primaryStage) throws Exception {
    Parent root = FXMLLoader.load(getClass().getResource("TextManipulator.fxml"));
    primaryStage.setTitle(i18n.getString("program_full_name"));
    primaryStage.setScene(new Scene(root));
    primaryStage.show();
  }
}
