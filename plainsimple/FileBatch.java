package plainsimple;
import java.io.File;
import java.io.FilenameFilter;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class FileBatch {
  private ArrayList<TextFile> files = new ArrayList<>();
  private String name = "";
  public FileBatch() {

  }
  public FileBatch(String name) {
      this.name = name;
  }
  /* sets name to new value */
  public FileBatch rename(String new_name) { name = new_name; return this; }
  /* constructs batch from String and sets this batch equal to it */
  public String constructBatch(String constructor) { // todo: needs to be improved
    if(constructor.equals(""))
        return "Error: Batch does not exist";
    ArrayList<String> failed_paths = new ArrayList<>();
    String[] lines = constructor.split("\\n|\\r");
    name = lines[0];
    for(int i = 1; i < lines.length; i++) {
        try{
            addFile(Paths.get(lines[i]));
        } catch(InvalidPathException e) {
            failed_paths.add(lines[i]); /* invalid path */
        }
    }
    if(failed_paths.size() == 0)
        return "Batch " + name + " loaded successfully";
    else {
        String error_message = "The following file(s) could not be added:";
        for(int i = 0; i < failed_paths.size(); i++)
            error_message += "\n" + failed_paths.get(i);
        return error_message;
    }
  }
  /* returns arraylist of all textfiles in batch */
  public ArrayList<TextFile> getFiles() { return files; }
  /* returns name of batch */
  public String getName() { return name; }
  /* sets name of batch */
  public void setName(String new_name) { name = new_name; }
  /* returns arraylist of all textfiles found in specified path */
  public ArrayList<TextFile> getFilesInDirectory(Path file_path) {
    ArrayList<TextFile> files_in_directory = new ArrayList<>();
    File[] matchingFiles = new File(file_path.toString()).listFiles(
    new FilenameFilter() {
      @Override public boolean accept(File dir, String file_name) {
        return dir.isFile();
      }
    });
    for(int i = 0; i < matchingFiles.length; i++) {
      files_in_directory.add(new TextFile(matchingFiles[i].toPath()));
    }
    return files_in_directory;
  }
  /* attempts to add textfile to batch */
  public boolean addFile(Path file_path) {
    TextFile new_file = new TextFile(file_path);
    if(new_file.isValid()) {
        files.add(new_file);
        return true;
    } else
        return false;
  }
  /* attempts to add textfile to batch */
  public boolean addFile(TextFile add_file) {
      if(add_file.isValid()) {
          files.add(add_file);
          return true;
      } else
          return false;
  }
  /* removes all duplicate files in batch */
  public FileBatch removeDuplicates() {
      for(int i = 0; i < files.size(); i++) {
          for(int j = 1; j < files.size(); j++) {
              if (files.get(j).getPath().equals(files.get(i).getPath()))
                  files.remove(j);
          }
      }
      return this;
  }
  /* returns String representation of batch */
  @Override public String toString() {
      String batch = name;
      for(int i = 0; i < files.size(); i++) {
          batch += "\n" + files.get(i).getPath();
      }
      return batch;
  }
}