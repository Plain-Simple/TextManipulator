package plainsimple.textmanipulator;

import c10n.C10N;

import java.io.*;
import java.util.ArrayList;
import java.util.Locale;

/* Plain+Simple TextManipulator settings and global variable management */
class Settings {
    private final Messages messages = C10N.get(Messages.class, Locale.getDefault());
    private ArrayList<String> settings = new
            ArrayList<>(); // work in progress
    public ArrayList<String> getSettings() {
        return settings;
    }
    public void setDefaultSettings(String file_name) {
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
            System.out.println(messages.error_writing_default_settings());
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
    public boolean updateSettings(String file_name, String[] updated_settings) {
        boolean write_success = true;
        try {
            FileWriter file = new FileWriter(file_name);
            BufferedWriter write_settings = new BufferedWriter(file);
            write_settings.write("last-used file path: " + updated_settings[0]);
            write_settings.newLine();
            write_settings.write("last-used batch: " + updated_settings[1]);
            write_settings.newLine();
            write_settings.write("last-used directory: " + updated_settings[2]);
            write_settings.newLine();
            write_settings.write("directory prefix: " + updated_settings[3]);
            write_settings.close();
        } catch(IOException e) {
            write_success = false;
        }
        return write_success;
    }
}


