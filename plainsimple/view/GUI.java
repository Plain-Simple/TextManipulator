package plainsimple.view;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import plainsimple.*;
import plainsimple.util.TextUtil;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GUI implements Initializable {
    private int caret_location = 0; /* cursor location in textarea */
    @FXML private TextField number_suffix;
    @FXML private TextField imported_filename;
    @FXML private TextField number_prefix;
    @FXML private TextField separator;
    @FXML private TextField to_replace;
    @FXML private TextField to_find;
    @FXML private TextField to_suffix;
    @FXML private TextField to_prefix;

    @FXML private TextArea text;

    @FXML private RadioButton exec_sep;
    @FXML private RadioButton exec_c;
    @FXML private RadioButton exec_l;
    @FXML private RadioButton alphabetic_sort;
    @FXML private RadioButton numeric_sort;
    @FXML private RadioButton exec_w;

    @FXML private CheckBox remove_delims;

    @FXML private TableView<ObjectFrequency> table = new TableView();
    @FXML private TableColumn col_2 = new TableColumn("");
    @FXML private TableColumn col_1 = new TableColumn("Analytics");

    @FXML private MenuItem menu_open;
    @FXML private MenuItem menu_save;
    @FXML private MenuItem menu_close;
    @FXML private MenuItem menu_cut;
    @FXML private MenuItem menu_copy;
    @FXML private MenuItem menu_paste;
    @FXML private MenuItem menu_findreplace;

    /* following 4 objects used in textanalysis table */
    private ObjectFrequency words_frequency = new ObjectFrequency("Words", 0);
    private ObjectFrequency lines_frequency = new ObjectFrequency("Lines", 0);
    private ObjectFrequency chars_frequency = new ObjectFrequency("Chars", 0);
    private ObservableList<ObjectFrequency> data = FXCollections.observableArrayList(
            words_frequency, lines_frequency, chars_frequency);
    /* this method is called by the FXMLLoader when initialization is complete */
    @Override public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
        /* initialize textanalysis table columns */
        /*data.addListener(new ListChangeListener<ObjectFrequency>() {
            @Override public void onChanged(Change<? extends ObjectFrequency> c) {
                System.out.println("hello");
            }
        });*/
        col_1.setCellValueFactory(new PropertyValueFactory<>("string"));
        col_2.setCellValueFactory(new PropertyValueFactory<>("int"));
        table.setItems(data);
        /* add change listener to textarea */
        text.textProperty().addListener(new ChangeListener<String>() {
                @Override public void changed(ObservableValue<? extends String> observable,
                                              String oldValue, String newValue) {
                caret_location = text.getCaretPosition(); // todo: improve. Caret location should be smart
                // todo: diffs and undo/redo?
                    // todo: should update table
                words_frequency.setInt(TextUtil.wordCount(newValue));
                lines_frequency.setInt(TextUtil.lineCount(newValue));
                chars_frequency.setInt(TextUtil.charCount(newValue));
            }
        });
        returnFocus();
    }
    /* returns text as a simple String (no processing) */
    private String[] getSimpleText() { return new String[] {getSelectedText()}; }
    /* special function to return selected text (returns all if no selection) */
    private String getSelectedText() {
        if(text.getSelectedText().equals(""))
            return text.getText(); /* no selection - return all */
        else
            return text.getSelectedText();
    }
    /* returns processed and split text */
    private ArrayList<String[]> getSplitText() {
        switch(getTarget()) {
            case "words":
                return TextUtil.splitIntoWords(getSelectedText());
            case "lines":
                return TextUtil.splitIntoLines(getSelectedText());
            case "chars":
                return TextUtil.splitIntoChars(getSelectedText());
            case "sep":
                return TextUtil.splitBySeparator(getSelectedText(), getSeparator());
            default:
                return TextUtil.getAsArray(getSelectedText());
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
    private String getReplace() { return to_replace.getText(); } // todo: look into pattern.quote to use user-defined tokens
    /* returns user-defined expression to find */
    private String getFind() { return toRegex(to_find.getText()); }
    /* takes user regex token and escapes any necessary characters */
    private String toRegex(String s) {
        /* negative look-behind of ?, */
        Pattern to_escape = Pattern.compile("(?<![\\?\\!\\^])"); // todo: fix
        Matcher matcher = to_escape.matcher(s);
        while(matcher.find()) {
            s = s.substring(0, matcher.start()) + "\\" + matcher.group(0) + s.substring(matcher.end());
        }
        System.out.println("escaped string: " + s);
        return s;
    }
    /* sets text in textarea from String */
    private void setText(String s) { text.setText(s); }
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
        ArrayList<Integer> locations = TextUtil.find(getSimpleText(), getFind());
        returnFocus();
    }
    @FXML private void replaceAction() {
        setText(TextUtil.findReplace(getSimpleText(), getFind(), getReplace()));
        returnFocus(); }
    @FXML private void uppercaseAction() {
        setText(TextUtil.forceUppercase(getSimpleText()));
        returnFocus();
    }
    @FXML private void lowercaseAction() {
        setText(TextUtil.forceLowercase(getSimpleText()));
        returnFocus();
    }
    @FXML private void prefixAction() {
        ArrayList<String[]> objects = getSplitText();
        objects.set(1, TextUtil.addPrefixSuffix(getSplitText().get(1), getPrefix(), ""));
        setText(TextUtil.mergeText(objects.get(0), objects.get(1)));
        returnFocus();
    }
    @FXML private void suffixAction() {
        ArrayList<String[]> objects = getSplitText();
        objects.set(1, TextUtil.addPrefixSuffix(getSplitText().get(1), "", getSuffix()));
        setText(TextUtil.mergeText(objects.get(0), objects.get(1)));
        returnFocus();
    }
    @FXML private void removePunctuationAction() {
        setText(TextUtil.removePunctuation(getSimpleText()));
        returnFocus();
    }
    @FXML private void removeAllWhitespaceAction() {
        setText(TextUtil.removeWhitespace(getSimpleText()));
        returnFocus();
    }
    /* remove extra whitespace */
    @FXML private void trimAction() {
        setText(TextUtil.removeExtraWhitespace(getSimpleText()));
        returnFocus();
    }
    @FXML private void scrambleAction() {
        returnFocus();
    }
    @FXML private void sortAction() {
        returnFocus();
    }
    @FXML private void numberAction() {
        returnFocus();
    }
    @FXML private void frequenciesAction() {
        returnFocus();
    }
    @FXML private void import_action() {
        //TextFileChooser chooser = new TextFileChooser("Open File",
        //        new File(System.getProperty("user.home")), import_file);
        //if(chooser.display()) {
         //   setText(chooser.getFile().getFileText());
         //   imported_filename.setText(chooser.getFile().getFileName());
        //}
        returnFocus();
    }
    @FXML private void cut_action() {
        setText((new ClipboardManager()).cut(text));
        returnFocus();
    }
    @FXML private void copy_action() {
        (new ClipboardManager()).copy(text);
        returnFocus();
    }
    @FXML private void paste_action() {
        setText((new ClipboardManager().paste(text)));
        returnFocus();
    }
    @FXML private void save_action() {

    }
    @FXML private void quit_action() {
        System.exit(0);
    }
    @FXML private void show_findreplacemenu() throws Exception {
        Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("FindReplacePopUp.fxml"));
            stage.setScene(new Scene(root));
            stage.setTitle("FindReplace");
            stage.initModality(Modality.APPLICATION_MODAL);
           // stage.initOwner(to_uppercase.getScene().getWindow());
            stage.show();
            //stage.showAndWait();

    }
}
