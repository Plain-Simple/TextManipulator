package plainsimple.textmanipulator;

import java.io.*;
import java.util.ArrayList;

/* Plain+Simple TextManipulator settings and global variable management */
public class Settings {
    private ArrayList<Integer> settings = new ArrayList<Integer>(); // work in progress
    public ArrayList<Integer> getSettings() {return settings;}
    public void setDefaultSettings(String file_name) {
        try {
            FileWriter file = new FileWriter(file_name);
            BufferedWriter write_settings = new BufferedWriter(file);
            write_settings.write("130");
            write_settings.newLine();
            write_settings.write("131");
            write_settings.newLine();
            write_settings.write("132");
            write_settings.newLine();
            write_settings.write("133");
            write_settings.newLine();
            write_settings.write("134");
            write_settings.newLine();
            write_settings.write("135");
            write_settings.newLine();
            write_settings.write("136");
            write_settings.newLine();
            write_settings.write("137");
            write_settings.newLine();
            write_settings.close();
        } catch (IOException e) {
            System.out.println("Error writing default settings");
        }
        settings.add(130);
        settings.add(131);
        settings.add(132);
        settings.add(133);
        settings.add(134);
        settings.add(135);
        settings.add(136);
        settings.add(137);
    }
    /* loads settings from file name. Creates default settings if an exception is found */
    public boolean loadSettings(String file_name) {
        boolean read_success = true;
        try {
            FileReader file = new FileReader(file_name);
            BufferedReader read_settings = new BufferedReader(file);
            String line = "";
            while((line = read_settings.readLine()) != null) {
                settings.add(Integer.parseInt(line));
            }
            System.out.println("File \"" + file_name + "\" loaded");
            System.out.println(settings.toString());
        } catch (IOException e) {
            System.out.println("Error reading settings file\n");
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
            for(int i = 0; i < settings.size(); i++) {
                write_settings.write(Integer.toString(settings.get(i)));
                write_settings.newLine();
            }
            write_settings.close();
        } catch(IOException e) {
            //Print("Error writing to file\n");
            write_success = false;
        }
        return write_success;
    }
    /* returns false if '0', true if anything else. Used for transferring variables from "TextManipulator_Settings to
 * the program */
    public boolean CharToBoolean(char c) {
        if(c == '0')
            return false;
        else
            return true;
    }
    /* returns '0' if false, '1' if true. Used for transferring variables from program to "TextManipulator_Settings" */
    public char BooleanToChar(boolean b) {
        if(b == false)
            return '0';
        else
            return '1';
    }
}

