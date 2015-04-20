package plainsimple.textmanipulator;

import c10n.C10N;

import java.io.*;
import java.util.ArrayList;
import java.util.Locale;

/* Plain+Simple TextManipulator settings and global variable management */
class Settings {
    private final Messages messages = C10N.get(Messages.class, Locale.getDefault());
    private ArrayList<String> settings = new ArrayList<>();
    /* name of file where data is stored */
    private String file_name = "TextManipulator_Settings";
    public ArrayList<String> getSettings() { return settings; }
    public Settings() { /* checks to see if data file exists */
        if(!new File(file_name).isFile())
            setDefaultSettings(); /* if not, creates default data file */
    }
    /* adds batch to data file */
    public void addBatch(FileBatch add) {
        TextFile data_file = new TextFile(file_name);
        if(data_file.isValid()) {
            data_file.appendText(add.toString());
        } else
            setDefaultSettings();
    }
    /* finds batch in data file and replaces it with updated batch */
    public boolean replaceBatch(FileBatch old, FileBatch updated) {
        /* read in text from file, ignoring text that has to do with old file */
        String to_keep = "";
        try {
            FileReader file = new FileReader(file_name);
            BufferedReader read_settings = new BufferedReader(file);
            String line;
            int line_counter = 0;
            boolean batch_found = false; /* batch to replace found */
            while((line = read_settings.readLine()) != null) {
                if (line.equals("----------")) /* signifies new batch */
                    line_counter = 0;
                if(line_counter == 1) { /* name of batch */
                    if (line.equals(old.getName()))
                        batch_found = true;
                    else {
                        batch_found = false;
                        to_keep += "\n----------"; /* only add line if batch has not been found */
                    }
                }
                if(!batch_found && !line.equals("----------"))
                    to_keep += line;
                line_counter++;
            }
            /* now need to write back to file */
            TextFile data_file = new TextFile(file_name, to_keep);
            addBatch(updated);
            return true;
        } catch (IOException e) {
            System.out.println(messages.error_writing_default_settings());
            return false;
        }
    }
    /* creates file with blank values */
    public void setDefaultSettings() {
        try {
            FileWriter file = new FileWriter(file_name);
            BufferedWriter write_settings = new BufferedWriter(file);
            write_settings.write("Stored Batches:");
            write_settings.close();
        } catch (IOException e) {
            System.out.println(messages.error_writing_default_settings());
        }
    }
    /* returns arraylist containing names of all stored batches */
    public ArrayList<String> getSavedBatches() {
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
            setDefaultSettings();
        }
        return batch_names;
    }
    /* returns whether specified batch exists */
    public boolean batchExists(String batch_name) {
        return
            getSavedBatches().contains(batch_name);
    }
    /* returns filebatch of specified name from storage */
    public FileBatch getBatch(String batch_name) {
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
                if (line_counter == 1) {
                    if (line.equals(batch_name))
                        batch_found = true;
                    else
                        batch_found = false;
                }
                if (batch_found)
                    constructor += line;
                line_counter++;
            }
            System.out.println(batch.constructBatch(constructor));
            return batch;
        } catch (IOException e) {
            setDefaultSettings();
            return new FileBatch();
        }
    }
}


