package plainsimple.textmanipulator;

import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Directory {
    private Path path;
    public Directory(Path path) {
        this.path = path;
    }
    public Path getPath() {return path;}
    public String getPathAsString() {return path.toString();}
    public boolean directoryExists() { // do we want a separate object for paths that we use?
        return new File(path.toString()).exists();
    }
    public Path getParent() {return path.getParent();}
    public void setAsParent() {path = path.getParent();}
    public ArrayList<TextFile> getFilesInDirectory() {
        ArrayList<TextFile> files_in_directory = new ArrayList<>();
        try {
            File[] matchingFiles = new File(path.toString()).listFiles(new FileFilter() {
                @Override public boolean accept(File dir) {
                    return dir.isFile();
                } // how to get only text files?
            });
            for (int i = 0; i < matchingFiles.length; i++) {
                files_in_directory.add(new TextFile(matchingFiles[i].toPath()));
            }
        } catch(SecurityException e) { // ToDo: better handling and more catch statements
            System.out.println("Error: Could not access files due to Security Manager.");
        }
        return files_in_directory;
    }
    /* attempts to change directory to new_directory. If the new directory is valid,
    it will copy the path and return true; otherwise, it will ignore the new directory
    and return false. */
    public boolean changeDirectory(String new_directory) {
        boolean change_success = false;
        Directory test_directory = new Directory(Paths.get(new_directory));
        if(test_directory.directoryExists()) {
            path = test_directory.getPath();
            return true;
        }
        return change_success;
    }
}
