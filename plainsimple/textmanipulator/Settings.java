package plainsimple.textmanipulator;

import c10n.C10N;

import java.io.*;
import java.util.ArrayList;
import java.util.Locale;

/* Plain+Simple TextManipulator settings and global variable management */
class Settings {
    private final Messages messages = C10N.get(Messages.class, Locale.getDefault());
    private ArrayList<String> settings = new ArrayList<>();
    private ArrayList<FileBatch> batches = new ArrayList<>();
    public ArrayList<String> getSettings() { return settings; }
    /* adds batch to stored batches and updates settings file */
    public void addBatch(FileBatch add) {
        batches.add(add);
        updateSettings("TextManipulator_Settings");
    }
    /* creates file with blank values */
    public void setDefaultSettings(String file_name) {
        try {
            FileWriter file = new FileWriter(file_name);
            BufferedWriter write_settings = new BufferedWriter(file);
            write_settings.write("Stored Batches:");
            write_settings.close();
        } catch (IOException e) {
            System.out.println(messages.error_writing_default_settings());
        }
        settings.add("");
    }
    /* returns arraylist containing names of all stored batches */
    public ArrayList<String> getSavedBatches(String file_name) {
        ArrayList<String> batch_names = new ArrayList<>();
        try {
            FileReader file = new FileReader(file_name);
            BufferedReader read_settings = new BufferedReader(file);
            String line;
            int line_counter = 0;
            while((line = read_settings.readLine()) != null) {
                if (line.equals("----------")) { /* signifies new batch */
                    line_counter = 0;
                }
                if(line_counter == 1) /* name of batch */
                    batch_names.add(line);
                line_counter++;
            }
        } catch (IOException e) {
            setDefaultSettings(file_name);
        }
        return batch_names;
    }
    /* returns whether specified batch exists */
    public boolean batchExists(String batch_name) {
        return
            getSavedBatches("TextManipulator_Settings").contains(batch_name);
    }
    /* returns filebatch of specified name from storage */
    public FileBatch getBatch(String file_name, String batch_name) {
        try {
            FileReader file = new FileReader(file_name);
            BufferedReader read_settings = new BufferedReader(file);
            String line;
            String constructor = "";
            FileBatch batch = new FileBatch();
            boolean batch_found = false;
            int line_counter = 0;
            while((line = read_settings.readLine()) != null) { // todo: far from perfect
                if (line.equals("----------")) { /* signifies new batch */
                    line_counter = 0;
                }
                if (line_counter == 1)
                    if (line.equals(batch_name))
                        batch_found = true;
                if (batch_found)
                    constructor += line;
                line_counter++;
            }
            System.out.println(batch.constructBatch(constructor));
            return batch;
        } catch (IOException e) {
            setDefaultSettings(file_name);
            return new FileBatch();
        }
    }
    /* updates "TextManipulator_Settings" with new values from program - will be run every time the user changes settings */
    public boolean updateSettings(String file_name) {
        boolean write_success = true;
        try {
            FileWriter file = new FileWriter(file_name);
            BufferedWriter write_settings = new BufferedWriter(file);
            write_settings.write("Stored Batches:");
            write_settings.newLine();
            for(int i = 0; i < batches.size(); i++) {
                write_settings.write("----------");
                write_settings.newLine();
                write_settings.write(batches.get(i).toString());
            }
            write_settings.close();
        } catch(IOException e) {
            write_success = false;
        }
        return write_success;
    }
}


