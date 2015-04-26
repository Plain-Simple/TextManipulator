package plainsimple.textmanipulator;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class GUI implements Initializable {
    private ManipulateText manip = new ManipulateText();
    private int caret_location = 0; /* cursor location in textarea */
    @FXML private Button prefix;
    @FXML private Button replace;
    @FXML private Button accent_9;
    @FXML private TextField number_suffix;
    @FXML private RadioButton exec_sep;
    @FXML private Button accent_6;
    @FXML private Button suffix;
    @FXML private Button accent_5;
    @FXML private TextField number_prefix;
    @FXML private Button accent_8;
    @FXML private Button accent_7;
    @FXML private Button scramble;
    @FXML private Button accent_14;
    @FXML private Button number;
    @FXML private TableColumn<?, ?> col_2;
    @FXML private TableColumn<?, ?> col_1;
    @FXML private Button find;
    @FXML private RadioButton exec_c;
    @FXML private TextField to_remove;
    @FXML private TextArea text;
    @FXML private Button accent_11;
    @FXML private Button accent_10;
    @FXML private RadioButton exec_l;
    @FXML private Button accent_13;
    @FXML private RadioButton alphabetic_sort;
    @FXML private Button accent_12;
    @FXML private RadioButton sort_highlow;
    @FXML private CheckBox remove_delims;
    @FXML private RadioButton numeric_sort;
    @FXML private Button count;
    @FXML private Button remove_punctuation;
    @FXML private Button sort;
    @FXML private TextField separator;
    @FXML private Button to_uppercase;
    @FXML private Button remove_extra_spaces;
    @FXML private Button remove_whitespace;
    @FXML private Text title;
    @FXML private Button remove;
    @FXML private TextField to_replace;
    @FXML private RadioButton sort_lowhigh;
    @FXML private Button to_lowercase;
    @FXML private TextField to_find;
    @FXML private Button import_file;
    @FXML private TableView<?> table;
    @FXML private CheckBox wrap_text;
    @FXML private TextField to_suffix;
    @FXML private RadioButton exec_w;
    @FXML private Button accent_2;
    @FXML private Button accent_1;
    @FXML private Button accent_4;
    @FXML private Button accent_3;
    @FXML private Button configure_accents;
    @FXML private Button frequencies;
    @FXML private TextField to_prefix;
    @FXML void ff0808(ActionEvent event) {}
    /* This method is called by the FXMLLoader when initialization is complete */
    @Override public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
        assert prefix != null : "fx:id=\"prefix\" was not injected: check your FXML file 'simple.fxml'.";
        /* add change listener to textarea */
        text.textProperty().addListener(new ChangeListener<String>() {
                @Override public void changed(ObservableValue<? extends String> observable,
                                              String oldValue, String newValue) {
                caret_location = text.getCaretPosition(); // todo: improve. Caret location should be smart
                // todo: diffs and undo/redo?

            }
        });
    }
    /* returns text as a simple String (no processing) */
    private String[] getSimpleText() { return new String[] {getSelectedText()}; }
    /* basically the simple .getText() method except gets user selection */
    private String getSelectedText() {
        if(text.getSelectedText().equals(""))
            return text.getText(); /* no selection - return all */
        else
            return text.getSelectedText();
    }
    /* returns processed and split text */
    private ArrayList<String[]> getText() {
        switch(getTarget()) {
            case "words":
                return manip.splitIntoWords(getSelectedText());
            case "lines":
                return manip.splitIntoLines(getSelectedText());
            case "chars":
                return manip.splitIntoChars(getSelectedText());
            case "sep":
                return manip.splitBySeparator(getSelectedText(), getSeparator());
            default:
                return manip.getAsArray(getSelectedText());
        }
    }
    /* returns "target" of function (words, lines, chars, etc.) */
    private String getTarget() {
        if(exec_w.isSelected())
            return "words";
        else if(exec_l.isSelected())
            return "lines";
        else if(exec_c.isSelected())
            return "chars";
        else if(exec_sep.isSelected())
            return "sep";
        else
            return "all";
    }
    /* returns user-defined separator */
    private String getSeparator() { return separator.getText(); }
    /* returns user-defined prefix */
    private String getPrefix() { return to_prefix.getText(); }
    /* returns user-defined suffix */
    private String getSuffix() { return to_suffix.getText(); }
    /* returns user-defined expression to replace */
    private String getReplace() { return Pattern.quote(to_replace.getText()); } // todo: look into pattern.quote
    /* returns user-defined expression to find */
    private String getFind() { return Pattern.quote(to_find.getText()); }
    /* returns user-defined expression to remove */
    private String getRemove() { return Pattern.quote(to_remove.getText()); }
    /* sets text in textarea from String array */
    private void setText(String[] s) {
        StringBuilder builder = new StringBuilder();
        for(int i = 0; i < s.length; i++)
            builder.append(s[i]);
        text.setText(builder.toString());
    }
    /* returns focus to textarea and puts caret in proper place */
    private void returnFocus() { // todo: improve
        text.requestFocus();
        if(caret_location < text.getText().length())
            text.positionCaret(caret_location);
        else
            text.positionCaret(text.getText().length() - 1);
    }
    @FXML private void findAction() {
        String to_find = getFind();
        System.out.println("regex is " + to_find);
        ArrayList<Integer> locations = manip.find(getSimpleText(), getFind());
        System.out.println(locations.toString());
        returnFocus();
    }
    @FXML private void replaceAction() {returnFocus(); }
    @FXML private void removeAction() {
        setText(manip.remove(getSimpleText(), getRemove())); // todo: not working??
        returnFocus();
    }
    @FXML private void uppercaseAction() {
        setText(manip.forceUppercase(getSimpleText()));
        returnFocus();
    }
    @FXML private void lowercaseAction() {
        setText(manip.forceLowercase(getSimpleText()));
        returnFocus();
    }
    @FXML private void prefixAction() {
        ArrayList<String[]> objects = getText();
        objects.set(1, manip.addPrefixSuffix(getText().get(1), getPrefix(), ""));
        setText(manip.mergeText(objects.get(0), objects.get(1)));
        returnFocus();
    }
    @FXML private void suffixAction() {
        ArrayList<String[]> objects = getText();
        objects.set(1, manip.addPrefixSuffix(getText().get(1), "", getSuffix()));
        setText(manip.mergeText(objects.get(0), objects.get(1)));
        returnFocus();
    }
    @FXML private void removePunctuationAction() {
        setText(manip.removePunctuation(getSimpleText()));
        returnFocus();
    }
    @FXML private void removeAllWhitespaceAction() {
        setText(manip.removeWhitespace(getSimpleText()));
        returnFocus();
    }
    /* remove extra whitespace */
    @FXML private void trimAction() {
        setText(manip.removeExtraWhitespace(getSimpleText()));
        returnFocus();
    }
    @FXML private void scrambleAction() {
        returnFocus();
    }
    @FXML private void sortAction() {
        returnFocus();
    }
    @FXML private void countAction() {
        returnFocus();
    }
    @FXML private void numberAction() {
        returnFocus();
    }
    @FXML private void frequenciesAction() {
        returnFocus();
    }

}
