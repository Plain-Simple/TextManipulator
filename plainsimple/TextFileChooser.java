package plainsimple;

import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

/* class for creating and displaying filechoosers */
public class TextFileChooser {
    private FileChooser file_chooser = new FileChooser();
    private Button import_button;
    private TextFile chosen_file;
    public TextFileChooser(String title, File directory, Button import_button) {
        file_chooser.setTitle(title);
        file_chooser.setInitialDirectory(directory);
        this.import_button = import_button;
        file_chooser.getExtensionFilters().addAll( /* add textfile filter */
                new FileChooser.ExtensionFilter("Text Files", "*.txt")
        );
    }
    /* displays filechooser, returns whether user selected valid textfile */
    public boolean display() {
        import_button.setDisable(true); /* disable import button while chooser is open */
        try {
            File imported_file = file_chooser.showOpenDialog(new Stage());
            import_button.setDisable(false);
            TextFile text_file = new TextFile(imported_file);
            if(text_file.isValid()) {
                chosen_file = text_file;
                return true;
            } else
                return false;
        } catch(NullPointerException|IllegalArgumentException e) {
            return false; /* no file selected */
          }
    }
    public TextFile getFile() {
        return chosen_file;
    }
}
