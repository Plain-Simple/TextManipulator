package plainsimple;

import javafx.scene.control.*;
import javafx.scene.control.TextArea;

import java.awt.*;
import java.awt.datatransfer.*;
import java.io.IOException;

/* class for managing clipboard (cut, copy, paste functions) */
public class ClipboardManager {
    /* copies String to clipboard */
    private void copyToClipboard(String to_copy) {
        StringSelection stringSelection = new StringSelection(to_copy);
        Clipboard clpbrd = Toolkit.getDefaultToolkit().getSystemClipboard();
        clpbrd.setContents(stringSelection, null);
    }
    /* gets clipboard contents. Returns empty String if contents are inaccessible  */
    private String getClipboard() {
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        Transferable contents = clipboard.getContents(null);
        /* make sure clipboard contains String */
        if (contents != null && contents.isDataFlavorSupported(DataFlavor.stringFlavor)) {
            try {
                return (String)contents.getTransferData(DataFlavor.stringFlavor);
            } catch (UnsupportedFlavorException | IOException e) {
                return "";
            }
        } else
            return "";
    }
    /* copies selected text (if any) and removes it */
    public String cut(TextArea text_area) {
        String selected_text = text_area.getSelectedText();
        if(!selected_text.equals("")) {
            int location = text_area.getCaretPosition();
            copyToClipboard(text_area.getSelectedText());
            /* remove selected text */
            return text_area.getText().substring(0, location) +
                    text_area.getText().substring(location + selected_text.length());
        } else /* no selection - no change */
            return text_area.getText();
    }
    /* copies selected text (if any) */
    public void copy(TextArea text_area) {
        /* text must be selected to copy */
        if(!text_area.getSelectedText().equals(""))
            copyToClipboard(text_area.getSelectedText());
    }
    /* pastes clipboard contents into textarea */
    public String paste(TextArea text_area) {
        String clipboard_contents = getClipboard();
        if(!clipboard_contents.equals("")) { /* clipboard contains String */
            int location = text_area.getCaretPosition();
            String text = text_area.getText();
            if (location == text.length() - 1) /* caret at end of String - append clipboard */
                return text + clipboard_contents;
            else if (text.endsWith(text_area.getSelectedText()))
                return text.substring(0, location) + clipboard_contents;
            else
                return text.substring(0, location) + clipboard_contents +
                        text.substring(location);
        } else
            return text_area.getText();
    }
}
