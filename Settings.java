import java.io.*;

/* Plain+Simple TextManipulator settings and global variable management */
public class Settings {
    public static void DefaultSettings(boolean analyzetext_settings[]) {
        try {
            FileWriter file = new FileWriter("TextManipulator_Settings");
            BufferedWriter write_settings = new BufferedWriter(file);
            write_settings.write("#Line 1: AnalyzeText Settings: WordCount, CharCount, LineCount, SentenceCount, WordFrequency, CharFrequency\n");
            write_settings.write("1,1,1,1,1,1\n"); /* sets values on file to true by default */
            write_settings.close();
        } catch (IOException e) {
            //Print("Error writing default settings file\n");
        }
        for(int i = 0; i < 6; i++)
            analyzetext_settings[i] = true; /* set all values to true by default */
    }

    public static boolean LoadSettings(boolean analyzetext_settings[]) {
        boolean load = true;
        try {
            FileReader file = new FileReader("TextManipulator_Settings");
            BufferedReader read_settings = new BufferedReader(file);
            String line = "";
            int line_counter = 1;
            while((line = read_settings.readLine()) != null) {
                if(line_counter == 1) {/* line 1: load settings for AnalyzeText */
                    /* line is "1,1,1,1,1,1" so we can read it in easily. Each value is parsed from String to boolean for easy use */
                    analyzetext_settings[0] = CharToBoolean(line.charAt(0));
                    analyzetext_settings[1] = CharToBoolean(line.charAt(2));
                    analyzetext_settings[2] = CharToBoolean(line.charAt(4));
                    analyzetext_settings[3] = CharToBoolean(line.charAt(6));
                    analyzetext_settings[4] = CharToBoolean(line.charAt(8));
                    analyzetext_settings[5] = CharToBoolean(line.charAt(10));
                }
                line_counter++;
            }
        } catch (IOException e) {
            //Print("Error reading settings file\n");
            load = false;
        }
        return load; /* returns whether "TextManipulator_Settings" was accessed and read */
    }
    /* updates "TextManipulator_Settings" with new values from program - will be run every time the user changes settings */
    public static boolean UpdateSettings(boolean[] analyzetext_settings) {
        boolean success = true;
        try {
            FileWriter file = new FileWriter("TextManipulator_Settings");
            BufferedWriter write_settings = new BufferedWriter(file);
            /* write settings to file (in the correct order), first converting each value to a char and comma-separating them */
            write_settings.write(BooleanToChar(analyzetext_settings[0]) + "," + BooleanToChar(analyzetext_settings[1]) +
                    "," + BooleanToChar(analyzetext_settings[2]) + "," + BooleanToChar(analyzetext_settings[3]) + "," +
                    BooleanToChar(analyzetext_settings[4]) + "," + BooleanToChar(analyzetext_settings[5]));
            write_settings.newLine();
            write_settings.close();
        } catch(IOException e) {
            //Print("Error writing to file\n");
            success = false;
        }
        return success;
    }
    /* returns false if '0', true if anything else. Used for transferring variables from "TextManipulator_Settings to
 * the program */
    public static boolean CharToBoolean(char c) {
        if(c == '0')
            return false;
        else
            return true;
    }
    /* returns '0' if false, '1' if true. Used for transferring variables from program to "TextManipulator_Settings" */
    public static char BooleanToChar(boolean b) {
        if(b == false)
            return '0';
        else
            return '1';
    }
}
