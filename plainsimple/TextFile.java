package plainsimple;

import plainsimple.util.ManipulateText;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

public class
  TextFile {
  private String file_name = "";
  private String file_text = "";
  private Path file_path;
  public String getPath() {
        return file_path.toString();
    }
  public String getFileText() {
        return file_text;
    }
  public String getFileName() {
        return file_name;
    }
  /* constructs textfile using path as a String */
  public TextFile(String path) {
    file_path = Paths.get(path).toAbsolutePath();
    file_name = file_path.getFileName().toString();
    if(isValid())
        readFile();
  }
  /* constructs textfile using path */ // todo: constructor should create the file
  public TextFile(Path path) {
    file_path = path.toAbsolutePath();
    file_name = file_path.getFileName().toString();
    if(isValid())
        readFile();
  }
  /* constructs textfile using path and WRITES text to it */
  public TextFile(String path, String text) {
      file_path = Paths.get(path).toAbsolutePath();
      file_name = file_path.getFileName().toString();
      file_text = text;
      if(isValid())
          writeFile();
  }
  /* constructs textfile from file */
  public TextFile(File file) {
      file_name = file.getName();
      file_path = Paths.get(file.getPath()).toAbsolutePath();
      if(isValid())
        readFile();
  }
  /* sets text in textfile and rewrites */
  public void setText(String[] text) {
    file_text = "";
    for(int i = 0; i < text.length; i++)
        file_text += text[i];
    writeFile();
  }
  /* sets text in textfile and rewrites */
  public void setText(String text) {
    file_text = text;
    writeFile();
  }
  /* appends text to textfile and rewrites */
  public void appendText(String append) {
    file_text += append;
    writeFile();
  }
  /* removes all text from file */
  public boolean clear() {
    file_text = "";
    return writeFile();
  }
  /* returns whether this file is a valid text file that exists and can be accessed */
  public boolean isValid() {
    try {
        Files.newBufferedReader(file_path.toRealPath());
        return true; /* exists */
    } catch(IOException|SecurityException e) {
      return false;
    }
  }
  /* reads file and returns whether it was read successfully */
  public boolean readFile() {
    String file = "";
    try (BufferedReader reader = Files.newBufferedReader(file_path)) {
      String line;
      int line_counter = 0;
      while ((line = reader.readLine()) != null) {
        if(line_counter == 0)
            file = line;
        else
            file += "\n" + line;
        line_counter++;
      }
      file_text = file;
      return true;
    } catch (IOException e) {
      return false;
    }
  }
  /* writes file_text to file */
  public boolean writeFile() {
      System.out.println("File contents to write:\n" + file_text);
    try (BufferedWriter writer = Files.newBufferedWriter(file_path)) {
      writer.write(file_text, 0, file_text.length());
      return true;
    } catch (IOException e) {
      return false;
    }
  }
  /* deletes file */
  public boolean delete() {
    File file_to_delete = new File(file_path.toString());
    boolean delete_success = file_to_delete.delete();
    return delete_success;
  }
  /* returns text of data file in an arraylist of lines */
  public ArrayList<String> getLines() {
      readFile();
      return new ArrayList<>(Arrays.asList(file_text.split("\\r?\\n")));
  }
  /* pastes clipboard contents into file */
  public boolean pasteIntoFile() { // Todo: fix bug where linebreaks are lost
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        /* get contents from clipboard (stored in a Transferable, which manages data transfer) */
        Transferable contents = clipboard.getContents(null);
        /* if contents are transferable, the Transferable will not be null and will be the
        correct DataFlavor (String). DataFlavor refers to the type of object something is */
        boolean hasTransferableText =
                (contents != null) && contents.isDataFlavorSupported(DataFlavor.stringFlavor);
        if (hasTransferableText) {
            try {
                file_text = (String)contents.getTransferData(DataFlavor.stringFlavor);
                return writeFile(); /* returns true or false for file writing */
            } catch (UnsupportedFlavorException|IOException e) {
                System.out.println("Error: Could not access clipboard contents.");
                return false;
            }
        } else {
            System.out.println("Error: Clipboard is empty or does not contain writable text.");
            return false;
        }
    }
    /* prints contents of file in a presentable fashion */
    public void printFile() {
        System.out.println("File contents: ");
        System.out.println("---------------------------------------------------------");
        System.out.println(file_text);
        System.out.println("---------------------------------------------------------");
    }
    /* prints contents of file with numbered lines */
    public void printLines() {
        ManipulateText manip = new ManipulateText();
        System.out.println("Lines: ");
        System.out.println("---------------------------------------------------------");
        System.out.println(manip.numberObjects(manip.splitIntoLines(file_text).get(1), "", ". "));
        System.out.println("---------------------------------------------------------");
    }
    /* prints contents of file with words numbered and on separate lines */
    public void printWords() {
        ManipulateText manip = new ManipulateText();
        System.out.println("Words: ");
        System.out.println("---------------------------------------------------------");
        System.out.println(manip.numberObjects(manip.splitIntoWords(file_text).get(1), "", ". "));
        System.out.println("---------------------------------------------------------");
    }
    /* prints contents of file with chars numbered and on separate lines */
    public void printChars() { // todo: improve this function
        ManipulateText manip = new ManipulateText();
        System.out.println("Chars: ");
        System.out.println("---------------------------------------------------------");
        System.out.println(manip.numberObjects(manip.splitIntoChars(file_text).get(1), "", ". "));
        System.out.println("---------------------------------------------------------");
    }
}
