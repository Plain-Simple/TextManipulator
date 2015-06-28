package plainsimple.view;

import javafx.fxml.FXML;
import javafx.stage.FileChooser;
import plainsimple.MainApp;

import java.io.File;

/**
 * The controller for the root layout. The root layout provides the basic
 * application layout containing a menu bar and space where other JavaFX
 * elements can be placed. */
public class RootLayoutController {

    /* Reference to the main application */
    private MainApp mainApp;

    /* Sets the mainApp field */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    /* Empties textarea and clears last-used filepath */
    @FXML private void handleNew() {
        mainApp.getPersonData().clear();
        mainApp.setPersonFilePath(null);
    }

    /* Opens a FileChooser to let the user select a file to load */
    @FXML private void handleOpen() {
        FileChooser fileChooser = new FileChooser();

        /* Set extension filter */
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Text Files", "*.txt"));

        /* Show file dialog */
        File file = fileChooser.showOpenDialog(mainApp.getPrimaryStage());

        if (file != null) { /* User opened a file */
            mainApp.loadPersonDataFromFile(file);
        }
    }

    /* Saves the file to the textfile that is currently open. If there is no
     * open file, the "save as" dialog is shown */
    @FXML private void handleSave() {
        File personFile = mainApp.getPersonFilePath();
        if (personFile != null) {
            mainApp.savePersonDataToFile(personFile);
        } else {
            handleSaveAs();
        }
    }

    /* Opens a FileChooser to let the user select a file to save to */
    @FXML private void handleSaveAs() {
        FileChooser fileChooser = new FileChooser();

        /* Set extension filter */
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Text Files", "*.txt"));

        /* Show dialog */
        File file = fileChooser.showSaveDialog(mainApp.getPrimaryStage());

        if (file != null) {
            /* Ensure proper extension (.txt) */
            if (!file.getPath().endsWith(".txt")) {
                file = new File(file.getPath() + ".txt");
            }
            mainApp.savePersonDataToFile(file);
        }
    }

    /**
     * Opens an about dialog.
     */
    @FXML
    private void handleAbout() {
    }

    /* Closes the program */
    @FXML
    private void handleExit() {
        System.exit(0);
    }
}