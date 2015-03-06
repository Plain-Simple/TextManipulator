package plainsimple.textmanipulator;


import c10n.C10N;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.Locale;


public class JavaFXGUI extends Application {
  private static final Messages messages = C10N.get(Messages.class, Locale.getDefault());
  /* code to start GUI: */
  @Override
  public void start(Stage primaryStage) throws Exception {
    Parent root = FXMLLoader.load(getClass().getResource("TextManipulator.fxml"));
    primaryStage.setTitle(messages.program_full_name());
        primaryStage.setScene(new Scene(root));
    primaryStage.show();
  }

  /* actual GUI logic: */

  @FXML private TextArea mainText;

  /* Text Analysis: */


  @FXML private TextField wordCount;
  @FXML private TextField charCount;
  @FXML private TextField lineCount;
  @FXML private TextField sentenceCount;
  AnalyzeText textStats = new AnalyzeText();
  @FXML private void updateTextAnalysis(ActionEvent e) {
    /* gets wordcount using AnalyzeText class, converts it to a string, and sets
       the text of the wordcount box to that string */
    /*wordCount.setText(Integer.toString(textStats.wordCount(mainText.getText())));
    charCount.setText(Integer.toString(textStats.CharCount(mainText.getText())));
    lineCount.setText(Integer.toString(textStats.lineCount(mainText.getText())));
    sentenceCount.setText(Integer.toString(textStats.SentenceCount(mainText.getText()))); */
  }

}
