package plainsimple.textmanipulator;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;
import javax.swing.undo.UndoManager;

class GUI extends javax.swing.JFrame {
  public GUI() {
    initComponents();
    text_input.requestFocusInWindow(); /* set focus at start */
    addDocumentListener(text_input);
    addUndoableEventListener(text_input);
    text_input.addCaretListener(new CaretListener() {
      @Override
      public void caretUpdate(CaretEvent e) {
        int dot = e.getDot(); /* caret position where cursor started */
        int mark = e.getMark(); /* caret position where cursor ended */
        if (dot == mark) { /* no specific selection */
          selection = "";
          updateTable(text_analysis_table, getText());
        } else if (mark > dot) { /* user selected from left to right */
          selection = getText().substring(dot, mark);
          updateTable(text_analysis_table, selection);
        } else { /* user selected from right to left */
          selection = getText().substring(mark, dot);
          updateTable(text_analysis_table, selection);
        }
      }
    });
  }
  // <editor-fold defaultstate="collapsed" desc="Generated Code">
  private void initComponents() {
    settings.loadSettings("TextManipulator_Settings");
    javax.swing.JLabel jLabel1 = new javax.swing.JLabel();
    javax.swing.JTabbedPane jTabbedPane1 = new javax.swing.JTabbedPane();
    javax.swing.JPanel jPanel3 = new javax.swing.JPanel();
    javax.swing.JPanel jPanel1 = new javax.swing.JPanel();
    javax.swing.JPanel jPanel2 = new javax.swing.JPanel();
    javax.swing.JPanel jPanel4 = new javax.swing.JPanel();
    javax.swing.JPanel jPanel5 = new javax.swing.JPanel();
    // may need to make accent_button and button variables private
    javax.swing.JButton accent_button_1 = new javax.swing.JButton();
    final AccentButton button_1 = new AccentButton(settings.getSettings().get(0));
    accent_button_1.setText(button_1.getAccentString());
    javax.swing.JButton accent_button_2 = new javax.swing.JButton();
    AccentButton button_2 = new AccentButton(settings.getSettings().get(1));
    accent_button_2.setText(button_2.getAccentString());
    javax.swing.JButton accent_button_3 = new javax.swing.JButton();
    AccentButton button_3 = new AccentButton(settings.getSettings().get(2));
    accent_button_3.setText(button_3.getAccentString());
    javax.swing.JButton accent_button_4 = new javax.swing.JButton();
    AccentButton button_4 = new AccentButton(settings.getSettings().get(3));
    accent_button_4.setText(button_4.getAccentString());
    javax.swing.JButton accent_button_5 = new javax.swing.JButton();
    AccentButton button_5 = new AccentButton(settings.getSettings().get(4));
    accent_button_5.setText(button_5.getAccentString());
    javax.swing.JButton accent_button_6 = new javax.swing.JButton();
    AccentButton button_6 = new AccentButton(settings.getSettings().get(5));
    accent_button_6.setText(button_6.getAccentString());
    javax.swing.JButton accent_button_7 = new javax.swing.JButton();
    AccentButton button_7 = new AccentButton(settings.getSettings().get(6));
    accent_button_7.setText(button_7.getAccentString());
    javax.swing.JButton accent_button_8 = new javax.swing.JButton();
    AccentButton button_8 = new AccentButton(settings.getSettings().get(7));
    accent_button_8.setText(button_8.getAccentString());
    javax.swing.JScrollPane jScrollPane3 = new javax.swing.JScrollPane();
    text_input = new javax.swing.JEditorPane();
    javax.swing.JScrollPane jScrollPane1 = new javax.swing.JScrollPane();
    text_analysis_table = new javax.swing.JTable();
    javax.swing.JLabel jLabel2 = new javax.swing.JLabel();
    javax.swing.JMenuBar jMenuBar1 = new javax.swing.JMenuBar();
    javax.swing.JMenu jMenu1 = new javax.swing.JMenu();
    javax.swing.JMenuItem jMenuItem1 = new javax.swing.JMenuItem();
    javax.swing.JMenu jMenu2 = new javax.swing.JMenu();
    undo_menu_item = new javax.swing.JMenuItem();
    javax.swing.JMenuItem redo_menu_item = new javax.swing.JMenuItem();
    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    //setTitle("Plain+Simple TextManipulator");
    setTitle(i18n.messages.getString("program_full_name"));
    jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
    jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    jLabel1.setText(i18n.messages.getString("program_name"));
    jTabbedPane1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
    jTabbedPane1.setTabPlacement(javax.swing.JTabbedPane.LEFT);
    javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
    jPanel3.setLayout(jPanel3Layout);
    jPanel3Layout.setHorizontalGroup(
      jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGap(0, 222, Short.MAX_VALUE)
    );
    jPanel3Layout.setVerticalGroup(
      jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGap(0, 307, Short.MAX_VALUE)
    );
    jTabbedPane1.addTab(i18n.messages.getString("basic_tools"), jPanel3);
    javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
    jPanel1.setLayout(jPanel1Layout);
    jPanel1Layout.setHorizontalGroup(
      jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGap(0, 222, Short.MAX_VALUE)
    );
    jPanel1Layout.setVerticalGroup(
      jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGap(0, 307, Short.MAX_VALUE)
    );
    jTabbedPane1.addTab(i18n.messages.getString("line_functions"), jPanel1);
    javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
    jPanel2.setLayout(jPanel2Layout);
    jPanel2Layout.setHorizontalGroup(
      jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGap(0, 222, Short.MAX_VALUE)
    );
    jPanel2Layout.setVerticalGroup(
      jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGap(0, 307, Short.MAX_VALUE)
    );
    jTabbedPane1.addTab(i18n.messages.getString("word_functions"), jPanel2);
    javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
    jPanel4.setLayout(jPanel4Layout);
    jPanel4Layout.setHorizontalGroup(
      jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGap(0, 222, Short.MAX_VALUE)
    );
    jPanel4Layout.setVerticalGroup(
      jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGap(0, 307, Short.MAX_VALUE)
    );
    jTabbedPane1.addTab(i18n.messages.getString("find_replace"), jPanel4);
    javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
    jPanel5.setLayout(jPanel5Layout);
    jPanel5Layout.setHorizontalGroup(
      jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGap(0, 222, Short.MAX_VALUE)
    );
    jPanel5Layout.setVerticalGroup(
      jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGap(0, 307, Short.MAX_VALUE)
    );
    jTabbedPane1.addTab(i18n.messages.getString("settings"), jPanel5);
    ///TODO: 188nize
    jScrollPane3.setViewportView(text_input);
    text_analysis_table.setModel(new EditableTableModel());
    jScrollPane1.setViewportView(text_analysis_table);
    jLabel2.setText(i18n.messages.getString("author_notice"));
    jMenu1.setText(i18n.messages.getString("file_menu"));
    jMenuItem1.setText("Coming Soon!");
    jMenu1.add(jMenuItem1);
    jMenuBar1.add(jMenu1);
    jMenu2.setText(i18n.messages.getString("edit_menu"));
    undo_menu_item.setText(i18n.messages.getString("undo"));
    /* CTRL-Z shortcut */
    undo_menu_item.setAccelerator(KeyStroke.getKeyStroke('Z',
                                  Toolkit.getDefaultToolkit ().getMenuShortcutKeyMask()));
    /*
    undo_menu_item.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            undo_menu_itemActionPerformed(evt);
        }
    });
    */
    undo_menu_item.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        try {
          undo.undo();
        } catch (CannotUndoException ex) {
          ex.printStackTrace();
        }
      }
    });
    jMenu2.add(undo_menu_item);
    redo_menu_item.setText(i18n.messages.getString("redo"));
    redo_menu_item.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        try {
          undo.redo();
        } catch (CannotRedoException ex) {
          ex.printStackTrace();
        }
      }
    });
    redo_menu_item.setAccelerator(KeyStroke.getKeyStroke('Y',
                                  Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
    jMenu2.add(redo_menu_item);
    jMenuBar1.add(jMenu2);
    setJMenuBar(jMenuBar1);
    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                          .addGroup(layout.createSequentialGroup()
                                    .addContainerGap()
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 222,
                                        javax.swing.GroupLayout.PREFERRED_SIZE))
                          .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 322,
                                        javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                          .addGroup(
                            layout.createSequentialGroup() // will need to look at this and figure out if all of it is necessary
                            .addComponent(accent_button_1, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
                                          javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(accent_button_2, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
                                          javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(accent_button_3, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
                                          javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(accent_button_4, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
                                          javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(accent_button_5, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
                                          javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(accent_button_6, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
                                          javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(accent_button_7, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
                                          javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(accent_button_8, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
                                          javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                          .addGroup(layout.createSequentialGroup()
                                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 507,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0,
                                                Short.MAX_VALUE)
                                            .addContainerGap())
                                        .addComponent(jLabel2)))))
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                          .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 33,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                          .addComponent(accent_button_1, javax.swing.GroupLayout.PREFERRED_SIZE, 34,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                          .addComponent(accent_button_2, javax.swing.GroupLayout.PREFERRED_SIZE, 34,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                          .addComponent(accent_button_3, javax.swing.GroupLayout.PREFERRED_SIZE, 34,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                          .addComponent(accent_button_4, javax.swing.GroupLayout.PREFERRED_SIZE, 34,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                          .addComponent(accent_button_5, javax.swing.GroupLayout.PREFERRED_SIZE, 34,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                          .addComponent(accent_button_6, javax.swing.GroupLayout.PREFERRED_SIZE, 34,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                          .addComponent(accent_button_7, javax.swing.GroupLayout.PREFERRED_SIZE, 34,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                          .addComponent(accent_button_8, javax.swing.GroupLayout.PREFERRED_SIZE, 34,
                                        javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                          .addGroup(layout.createSequentialGroup()
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 92,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel2))
                          .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jTabbedPane1)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 233,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(0, 0, Short.MAX_VALUE)))
                                    .addContainerGap())))
    );
    accent_button_1.addActionListener(new
    ActionListener() { /* listens for button to be clicked */
      @Override // not sure if @Override is necessary
      public void actionPerformed(ActionEvent e) {
        int location =
          getText().length(); /* default location to insert accent is at end of text */
        if(selection != "") { /* if user has selected text */
          location = getText().indexOf(selection);
        }
        setText(button_1.insertAccent(selection, getText(), location));
      }
    });
    pack();
  }// </editor-fold>
  /* handles user clicking "Undo" in menu */ /*
  private void undo_menu_itemActionPerformed(java.awt.event.ActionEvent evt) {

  }
  /* returns text entered by user in the editor pane */
  public String getText() {
    return text_input.getText();
  }
  /* sets the editor pane using String passed */
  public void setText(String s) {
    text_input.setText(s);
  }
  /* updates text_analysis_table with latest data */
  void updateTable(javax.swing.JTable table, String text) {
    AnalyzeText analyze_text = new AnalyzeText();
    table.setValueAt(analyze_text.WordCount(text), 0, 1);
    table.setValueAt(analyze_text.CharCount(text), 1, 1);
    table.setValueAt(analyze_text.SentenceCount(text), 2, 1);
    table.setValueAt(analyze_text.LineCount(text), 3, 1);
  }
  /* adds a document listener to specified editor pane */
  void addDocumentListener(final javax.swing.JEditorPane text) {
    text.getDocument().addDocumentListener(new DocumentListener() {
      @Override
      public void insertUpdate(DocumentEvent
                               e) { /* fires when character(s) inserted */
        updateTable(text_analysis_table, text.getText()); /* update table */
      }
      @Override
      public void removeUpdate(DocumentEvent
                               e) { /* fires when character(s) removed */
        updateTable(text_analysis_table, text.getText()); /* update table */
      }
      @Override
      public void changedUpdate(DocumentEvent
                                e) {} /* only used in styled components */
    });
  }
  /* adds an UndoableEventListener to specified editor pane */
  void addUndoableEventListener(javax.swing.JEditorPane text) {
    text.getDocument().addUndoableEditListener(new UndoableEditListener() {
      @Override
      public void undoableEditHappened(UndoableEditEvent e) {
        undo.addEdit(e.getEdit());
        // undoAction.updateUndoState(); /* used for menus(?) */
        // redoAction.updateRedoState();
      }
    });
  }


  public void startGUI() {
    /* Set look and feel to system look and feel */
    try {
      UIManager.setLookAndFeel(
        UIManager.getSystemLookAndFeelClassName());
    } catch (ClassNotFoundException ex) {
      java.util.logging.Logger.getLogger(GUI.class.getName()).log(
        java.util.logging.Level.SEVERE, null, ex);
    } catch (InstantiationException ex) {
      java.util.logging.Logger.getLogger(GUI.class.getName()).log(
        java.util.logging.Level.SEVERE, null, ex);
    } catch (IllegalAccessException ex) {
      java.util.logging.Logger.getLogger(GUI.class.getName()).log(
        java.util.logging.Level.SEVERE, null, ex);
    } catch (javax.swing.UnsupportedLookAndFeelException ex) {
      java.util.logging.Logger.getLogger(GUI.class.getName()).log(
        java.util.logging.Level.SEVERE, null, ex);
    }
    /* Create and display the form */
    java.awt.EventQueue.invokeLater(new Runnable() {
      public void run() {
        new GUI().setVisible(true);
      }
    });
  }

  private javax.swing.JTable text_analysis_table;
  private javax.swing.JEditorPane text_input;
  private javax.swing.JMenuItem undo_menu_item;
  private String selection = new String();
  private Settings settings = new Settings();
  // End of variables declaration
  private final UndoManager undo = new
  UndoManager(); /* manager for undo/redo support */
}
