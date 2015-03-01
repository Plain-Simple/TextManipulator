package plainsimple.textmanipulator;

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
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class
  TextFile { // note: class renamed 'TextFile' to allow for usage of java.nio.file class
  private String file_name;
  private String file_text;
  private Path file_path;
  public TextFile(String path) {
    file_path = Paths.get(path).toAbsolutePath();
    file_name = file_path.getFileName().toString();
  }
  public TextFile(Path path) {
    file_path = path.toAbsolutePath();
    file_name = file_path.getFileName().toString();
  }
  public TextFile(String name, String path) {
    file_name = name;
    file_path = Paths.get(path).toAbsolutePath();
  }
    /* tries to create a bufferedreader object using this file's path */
  public boolean fileExists() {
    try {
        BufferedReader test_reader = Files.newBufferedReader(file_path.toRealPath());
    } catch(IOException e) {
      return false;
    }
    return readFile(); /* attempt to read file */
  }
  public String getPath() {
    return file_path.toString();
  }
  public void setPath(String path) {
    file_path = Paths.get(path).toAbsolutePath();
  }
  public String getFileText() {
    return file_text;
  }
  public String getFileName() {
    return file_name;
  }
  public boolean readFile() {
    String file = "";
    try (BufferedReader reader = Files.newBufferedReader(file_path)) {
      String line = "";
      while ((line = reader.readLine()) != null) {
        file += "\n" + line;
      }
      file_text = file;
    } catch (IOException x) {
      return false;
    }
    return true;
  }
    /* writes file_text object to file */
  public boolean writeFile() {
    try (BufferedWriter writer = Files.newBufferedWriter(file_path)) {
      writer.write(file_text, 0, file_text.length());
      return true;
    } catch (IOException x) {
      return false;
    }
  }
  public boolean deleteFile() {
    File file_to_delete = new File(file_path.toString());
    boolean delete_success = file_to_delete.delete();
    return delete_success;
  }
    public boolean pasteIntoFile() {
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        /* get contents from clipboard (stored in a Transferable, which manages data transfer) */
        Transferable contents = clipboard.getContents(null);
        /* if contents are transferable, the Transferable will not be null and will be the
        correct DataFlavor (String). DataFlavor refers to the type of object something is */
        boolean hasTransferableText =
                (contents != null) && contents.isDataFlavorSupported(DataFlavor.stringFlavor);
        if (hasTransferableText) {
            try {
                String clipboard_contents = (String)contents.getTransferData(DataFlavor.stringFlavor);
                file_text = clipboard_contents;
                boolean write_success = writeFile();
                return write_success; /* returns true or false for whole operation */
            } catch (UnsupportedFlavorException | IOException ex) {
                System.out.println("Error: Could not paste contents into file. Operation aborted.");
                return false;
            }
        } else {
            System.out.println("Error: Clipboard is empty or does not contain writable text.");
            return false;
        }
    }
}
