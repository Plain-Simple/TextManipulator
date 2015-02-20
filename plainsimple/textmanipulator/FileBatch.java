package plainsimple.textmanipulator;
import java.io.File;
import java.io.FilenameFilter;
import java.nio.file.Path;
import java.util.ArrayList;

public class FileBatch {
    private ArrayList<TextFile> files = new ArrayList<TextFile>();
    private String batch_name = "";

    public FileBatch(String name) {
        batch_name = name;
    }
    public ArrayList<TextFile> getFiles() {
        return files;
    }
    public ArrayList<TextFile> getFilesInDirectory(Path file_path) {
        ArrayList<TextFile> files_in_directory = new ArrayList<>();
        File[] matchingFiles = new File(file_path.toString()).listFiles(new FilenameFilter() {
            @Override public boolean accept(File dir, String file_name) {
                return file_name.endsWith(".txt");
            }
        });
        for(int i = 0; i < matchingFiles.length; i++) {
            files_in_directory.add(new TextFile(matchingFiles[i].toPath()));
        }
        return files_in_directory;
    }
    public void addFile(Path file_path) { // ToDo: Error checking
        // File file_to_add = new File(file_path);
        files.add(new TextFile(file_path));
    }



}
