package plainsimple.textmanipulator;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TextFile {
    private String file_name;
    private String file_text;
    private Path file_path;
    public TextFile(String path) { // requires full path
        file_path = Paths.get(path).toAbsolutePath();
        file_name = file_path.getFileName().toString();
    }
    public TextFile(Path path) {
        file_path = path;
        file_name = file_path.getFileName().toString();
    }
    public TextFile(String name, String path) {
        file_name = name;
        file_path = Paths.get(path).toAbsolutePath();
    }
    public boolean fileExists() {
        try {
            file_path.toRealPath();
        } catch(IOException e) { // NoSuchFileException may be redundant if IOException is in use
            return false;
        }
        return true;
    }
    public String getPath() {
        return file_path.toString();
    }
    public String readFile() {
        String file = "";
        try (BufferedReader reader = Files.newBufferedReader(file_path)) {
            String line = "";
            while ((line = reader.readLine()) != null) {
                file += line;
            }
            return file;
        } catch (IOException x) {
            return "Error reading file";
        }
    }
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
}
