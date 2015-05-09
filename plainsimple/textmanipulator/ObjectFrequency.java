package plainsimple.textmanipulator;

import javafx.beans.property.SimpleStringProperty;

/* extremely simple class that holds a String and an integer.
Meant to be used with JavaFX tableviews and textanalysis functions */
public class ObjectFrequency {
    private SimpleStringProperty frequency;
    private SimpleStringProperty string;
    public ObjectFrequency(String s, int f) {
        frequency = new SimpleStringProperty(Integer.toString(f));
        string = new SimpleStringProperty(s);
    }
    public void setInt(int i) { frequency.set(Integer.toString(i)); }
    public String getIntAsString() { return frequency.get(); }
    public int getInt() { return Integer.parseInt(frequency.get()); }
    public void setString(String s) { string.set(s); }
    public String getString() { return string.get(); }
}
