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
import java.util.ResourceBundle;

public class GUI implements Initializable {
    private ManipulateText manip = new ManipulateText();
    private int caret_location = 0; /* cursor location in textarea */
    @FXML private Button prefix;
    @FXML private Button replace;
    @FXML private Button accent_9;
    @FXML private TextField number_suffix;
    @FXML private CheckBox exec_sep;
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
    @FXML private CheckBox exec_c;
    @FXML private TextField to_remove;
    @FXML private TextArea text;
    @FXML private Button accent_11;
    @FXML private Button accent_10;
    @FXML private CheckBox exec_l;
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
    @FXML private CheckBox exec_w;
    @FXML private Button accent_2;
    @FXML private Button accent_1;
    @FXML private Button accent_4;
    @FXML private Button accent_3;
    @FXML private Button configure_accents;
    @FXML private Button frequencies;
    @FXML private TextField to_prefix;
    @FXML void ff0808(ActionEvent event) {}

    @Override // This method is called by the FXMLLoader when initialization is complete
    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
        assert prefix != null : "fx:id=\"prefix\" was not injected: check your FXML file 'simple.fxml'.";
        /* set up target checkboxes to be mutually exclusive */
        exec_w.selectedProperty().addListener(new ChangeListener<Boolean>() { // todo: fix!
            @Override public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                exec_c.setSelected(false);
                exec_sep.setSelected(false);
                exec_l.setSelected(false);
            }
        });
        exec_c.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                exec_w.setSelected(false);
                exec_sep.setSelected(false);
                exec_l.setSelected(false);
            }
        });
        exec_l.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                exec_c.setSelected(false);
                exec_sep.setSelected(false);
                exec_w.setSelected(false);
            }
        });
        exec_sep.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                exec_c.setSelected(false);
                exec_w.setSelected(false);
                exec_l.setSelected(false);
            }
        });
    }
    /* returns text as a simple String (no processing) */
    private String[] getSimpleText() { return new String[] {text.getText()}; }
    /* returns processed and split text */
    private ArrayList<String[]> getText() {
        String[] manipulated_text;
        String[] delimiters;
        ArrayList<String[]> split_result;
        switch(getTarget()) {
            case "words":
                split_result = manip.splitIntoWords(text.getText());
                break;
            case "lines":
                split_result = manip.splitIntoLines(text.getText());
                break;
            case "chars":
                split_result = manip.splitIntoChars(text.getText());
                break;
            case "sep":
                split_result = manip.splitBySeparator(text.getText(), getSeparator());
                break;
            default:
                split_result = manip.getAsArray(text.getText());
        }
        return split_result;
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
    /* sets text in textarea */
    private void setText(String s) { text.setText(s); }
    /* sets text in textarea from String array */
    private void setText(String[] s) {
        String result = "";
        for(int i = 0; i < s.length; i++)
            result += s[i];
        text.setText(result);
    }
    /* returns focus to textarea and puts caret in proper place */
    private void returnFocus() { text.requestFocus(); text.positionCaret(caret_location); }
    /* find */
    @FXML private void findAction() {
        returnFocus();
    }
    @FXML private void replaceAction() {
        returnFocus();
    }
    @FXML private void removeAction() {
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
    @FXML private void prefixAction() { // todo: get this working
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
