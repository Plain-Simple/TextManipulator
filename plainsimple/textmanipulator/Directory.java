package plainsimple.textmanipulator;

import java.io.File;
import java.nio.file.Path;

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
}
