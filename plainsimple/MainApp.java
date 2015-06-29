package plainsimple;

import java.io.File;
import java.io.IOException;

import javafx.application.Application;
import javafx.beans.binding.StringExpression;
import javafx.beans.value.ObservableStringValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import plainsimple.util.DataHandler;
import plainsimple.view.RootLayoutController;

/* This class starts the JavaFX Application using MainScreen.fxml
 * as the root stage.
 * A note on textData: I implemented an ObservableList<String> to hold
 * textData because I couldn't find any other way to make sure the textData
 * could be updated and accessed throughout all classes of the program.
 * textData should only have one element.*/
public class MainApp extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;

    private ObservableList<String> textData =
            FXCollections.observableArrayList();

    /* Returns textData as a String */
    public String getTextData() { return textData.get(0); }

    /* Sets textData */
    public void setTextData(String textData) { this.textData.set(0, textData); }

    /* Constructor */
    public MainApp() {
    }

    /* Starts program, setting up the root layout and displaying the main screen */
    @Override public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("TextManipulator");

        initRootLayout();

        showMainScreen();

        /* Access file containing persisting data using path found in Preferences */
        File file = DataHandler.getTextFilePath();
        if(file != null) {
            System.out.println("File location: " + file.getPath());
            textData.add(DataHandler.loadTextFromFile(file));
        } else {
            textData.add("");
        }

    }

    /* Initializes the root layout */
    public void initRootLayout() {
        try {
            /* Load root layout from fxml file */
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/RootLayout.fxml"));
            rootLayout = loader.load();

            /* Show the scene containing the root layout */
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();

            /* Give controller access to mainApp and textData */
            RootLayoutController controller = loader.getController();
            controller.setMainApp(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /* Shows the main screen inside the root layout */
    public void showMainScreen() {
        try {
            /* Load MainsScreen.fxml */
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/MainScreen.fxml"));
            BorderPane mainScreen = loader.load();

            /* Set MainScreen into center of root layout */
            rootLayout.setCenter(mainScreen);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns the main stage.
     * @return
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }
}