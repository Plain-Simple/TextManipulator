package plainsimple.util;

import plainsimple.MainApp;

import java.io.File;
import java.util.prefs.Preferences;

/* Methods for accessing and setting program preferences */
public class DataHandler {

    /* Reads system registry and returns the "lastFilePath" preference,
     * i.e. the file that was last opened. Returns null if no such
     * preference can be found.
     * @return last opened textfile, null if none */
    public static File getPersonFilePath() {
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
    public static void setPersonFilePath(File file) {
        Preferences prefs = Preferences.userNodeForPackage(MainApp.class);
        if (file != null) {
            prefs.put("lastFilePath", file.getPath()); // todo: update stage title "TextManipulator - FileName"
        } else {
            prefs.remove("lastFilePath");
            // todo: update stage title "TextManipulator"
        }
    }
}
