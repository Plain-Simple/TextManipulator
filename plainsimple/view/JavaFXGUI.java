package plainsimple.view;


import c10n.C10N;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import plainsimple.Messages;

import java.util.Locale;


public class JavaFXGUI extends Application {
  private static final Messages messages = C10N.get(Messages.class, Locale.getDefault());
  /* code to start GUI: */
  @Override public void start(Stage primaryStage) throws Exception {
    Parent root = FXMLLoader.load(getClass().getResource("TextManipulator.fxml"));
    primaryStage.setTitle(messages.program_full_name());
    primaryStage.setScene(new Scene(root));
    primaryStage.show();
  }
}
