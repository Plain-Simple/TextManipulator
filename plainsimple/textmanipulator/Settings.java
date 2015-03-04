package plainsimple.textmanipulator;

import c10n.C10N;

import java.io.*;
import java.util.ArrayList;
import java.util.Locale;

/* Plain+Simple TextManipulator settings and global variable management */
class Settings {
  private static final Messages messages = C10N.get(Messages.class, Locale.getDefault());
  private ArrayList<Integer> settings = new
  ArrayList<>(); // work in progress
  public ArrayList<Integer> getSettings() {
    return settings;
  }
  void setDefaultSettings(String file_name) {
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
      System.out.println(messages.error_writing_default_settings());
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
      /*System.out.println(messages.file") + " \"" + file_name + "\" "
                         + messages.file_loaded"));
      System.out.println(settings.toString()); */
    } catch (IOException e) {
      //noinspection HardCodedStringLiteral
      System.out.println(messages.error_reading_settings() + "\n");
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
    return c != '0';
  }
  /* returns '0' if false, '1' if true. Used for transferring variables from program to "TextManipulator_Settings" */
  public char BooleanToChar(boolean b) {
    if(!b) {
      return '0';
    } else {
      return '1';
    }
  }
}

