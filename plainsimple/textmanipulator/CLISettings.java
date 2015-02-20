package plainsimple.textmanipulator;

import java.io.*;
import java.util.ArrayList;

public class CLISettings {
    private ArrayList<String> settings = new
            ArrayList<>(); // work in progress
    public ArrayList<String> getSettings() {
        return settings;
    }
    void setDefaultSettings(String file_name) {
        try {
            FileWriter file = new FileWriter(file_name);
            BufferedWriter write_settings = new BufferedWriter(file);
            write_settings.write("last-used file path:");
            write_settings.newLine();
            write_settings.write("last-used batch:");
            write_settings.newLine();
            write_settings.write("last-used directory:");
            write_settings.newLine();
            write_settings.write("directory prefix:");
            write_settings.close();
        } catch (IOException e) {
            System.out.println(i18n.getString("error_writing_default_settings"));
        }
        settings.add("");
        settings.add("");
        settings.add("");
        settings.add("");
    }
    /* loads settings from file name. Creates default settings if an exception is found */
    public boolean loadSettings(String file_name) {
        boolean read_success = true;
        try {
            FileReader file = new FileReader(file_name);
            BufferedReader read_settings = new BufferedReader(file);
            String line = "";
            while((line = read_settings.readLine()) != null) {
                try {
                    String setting = line.substring(line.indexOf(":") + 2); /* parse out
                    setting declaration (e.g. last-used file path: ) */
                    settings.add(setting);
                } catch(StringIndexOutOfBoundsException e) { /* no value defined */
                    settings.add("");
                }
            }
        } catch (IOException e) {
            //noinspection HardCodedStringLiteral
            read_success = false;
            setDefaultSettings(file_name);
        }
        return read_success; /* returns whether "TextManipulator_Settings" was accessed and read */
    }
    /* updates "TextManipulator_Settings" with new values from program - will be run every time the user changes settings */
    public boolean updateSettings(String file_name) {
        boolean write_success = true;
        try {
            FileWriter file = new FileWriter(file_name);
            BufferedWriter write_settings = new BufferedWriter(file);
            write_settings.write("last-used file path: " + settings.get(0));
            write_settings.newLine();
            write_settings.write("last-used batch: " + settings.get(1));
            write_settings.newLine();
            write_settings.write("last-used directory: " + settings.get(2));
            write_settings.newLine();
            write_settings.write("directory prefix: " + settings.get(3));
            write_settings.close();
        } catch(IOException e) {
            write_success = false;
        }
        return write_success;
    }

}
