package plainsimple.util;

import plainsimple.MainApp;
import plainsimple.TextFile;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.prefs.Preferences;

/* Methods for accessing and setting program preferences */
public class DataHandler {

    /* Reads system registry and returns the "lastFilePath" preference,
     * i.e. the file that was last opened. Returns null if no such
     * preference can be found.
     * @return last opened textfile, null if none */
    public static File getTextFilePath() {
        Preferences prefs = Preferences.userNodeForPackage(MainApp.class);
        String filePath = prefs.get("lastFilePath", null);
        if (filePath != null) {
            return new File(filePath);
        } else {
            return null;
        }
    }

    /* Accesses system registry and sets the "lastFilePath" preference to the
     * specified path. The path is persisted in memory
     * @param file the path to be persisted or null to clear the path */
    public static void setTextFilePath(File file) {
        Preferences prefs = Preferences.userNodeForPackage(MainApp.class);
        if (file != null) {
            prefs.put("lastFilePath", file.getPath()); // todo: update stage title "TextManipulator - FileName"
        } else {
            prefs.remove("lastFilePath");
            // todo: update stage title "TextManipulator"
        }
    }

    /* Loads text data (i.e. String) from the specified file. If data
     * could be loaded, text data will be replaced. Otherwise, it will
     * not be changed.
     * @param file textfile to read text from
     * @param text the current text data */
    public static String loadTextFromFile(File file, String text) {
        TextFile load_text = new TextFile(file);

        /* File was read successfully */
        if(load_text.isValid()) {
            text = load_text.getFileText();
            /* Save the file path to the registry */
            setTextFilePath(file);
            return text;
        } else {
            return text; // todo: set text to ""?
        }
    }

    /* Saves the current text to the specified file
     * @param file textfile to write text to
     * @param text String to write to textfile */
    public static void saveTextToFile(File file, String text) {
        TextFile save_text = new TextFile(file);
        if(save_text.isValid()) {
            save_text.setText(text);
            save_text.writeFile();
            /* Save the file path to the registry */
            setTextFilePath(file);
        } else { // todo: could not save file exception
        }
    }
}
