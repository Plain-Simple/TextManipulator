package plainsimple.textmanipulator;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;
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
            caret_location = dot;
          updateTable(text_analysis_table, getText());
        } else if (mark > dot) { /* user selected from left to right */
            caret_location = dot;
            selection = getText().substring(caret_location, mark);
          updateTable(text_analysis_table, selection);
        } else { /* user selected from right to left */
            caret_location = mark;
            selection = getText().substring(mark, dot);
          updateTable(text_analysis_table, selection);
        }
      }
    });
  }
  // <editor-fold defaultstate="collapsed" desc="Generated Code">
  private void initComponents() {
    settings.loadSettings("TextManipulator_Settings");
    JLabel jLabel1 = new JLabel();
    JTabbedPane function_panel = new JTabbedPane();
    JPanel basic_tools_panel = new JPanel();
    JPanel line_functions_panel = new JPanel();
    JPanel word_functions_panel = new JPanel();
    JPanel find_replace_panel = new JPanel();
    JPanel settings_panel = new JPanel();
      settings_label_1 = new JLabel("Accents to Display: ");
      display_accents = new JTextField();
      configure_accents_button = new JButton("Configure");

    // may need to make accent_button and button variables private
    accent_button_1 = new AccentButton(settings.getSettings().get(0));
    accent_button_2 = new AccentButton(settings.getSettings().get(1));
    accent_button_3 = new AccentButton(settings.getSettings().get(2));
    accent_button_4 = new AccentButton(settings.getSettings().get(3));
    accent_button_5 = new AccentButton(settings.getSettings().get(4));
      accent_button_6 = new AccentButton(settings.getSettings().get(5));
    accent_button_7 = new AccentButton(settings.getSettings().get(6));
    accent_button_8 = new AccentButton(settings.getSettings().get(7));

    JScrollPane jScrollPane3 = new JScrollPane();
    text_input = new JEditorPane();
    JScrollPane jScrollPane1 = new JScrollPane();
    text_analysis_table = new JTable();
    JLabel jLabel2 = new JLabel();
    JMenuBar jMenuBar1 = new JMenuBar();
    JMenu jMenu1 = new JMenu();
    JMenuItem jMenuItem1 = new JMenuItem();
    JMenu jMenu2 = new JMenu();
    JMenuItem undo_menu_item = new JMenuItem();
    JMenuItem redo_menu_item = new JMenuItem();
      JMenuItem copy_menu_item = new JMenuItem();
      JMenuItem cut_menu_item = new JMenuItem();
      JMenuItem paste_menu_item = new JMenuItem();
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    //setTitle("Plain+Simple TextManipulator");
    setTitle(i18n.messages.getString("program_full_name"));
    jLabel1.setFont(new Font("Tahoma", 1, 24)); // NOI18N
    jLabel1.setHorizontalAlignment(SwingConstants.CENTER);
    jLabel1.setText(i18n.messages.getString("program_name"));
    function_panel.setBorder(BorderFactory.createEtchedBorder());
    function_panel.setTabPlacement(JTabbedPane.LEFT);
    GroupLayout jPanel3Layout = new GroupLayout(basic_tools_panel);
    basic_tools_panel.setLayout(jPanel3Layout);
    jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGap(0, 222, Short.MAX_VALUE)
    );
    jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGap(0, 307, Short.MAX_VALUE)
    );
    function_panel.addTab(i18n.messages.getString("basic_tools"), basic_tools_panel);
    GroupLayout jPanel1Layout = new GroupLayout(line_functions_panel);
    line_functions_panel.setLayout(jPanel1Layout);
    jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGap(0, 222, Short.MAX_VALUE)
    );
    jPanel1Layout.setVerticalGroup(
      jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
      .addGap(0, 307, Short.MAX_VALUE)
    );

      /* adds "line functions tab to tabbed pane */
    function_panel.addTab(i18n.messages.getString("line_functions"), line_functions_panel);
    GroupLayout jPanel2Layout = new GroupLayout(word_functions_panel);
    word_functions_panel.setLayout(jPanel2Layout);
    jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGap(0, 222, Short.MAX_VALUE)
    );
    jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGap(0, 307, Short.MAX_VALUE)
    );
    function_panel.addTab(i18n.messages.getString("word_functions"), word_functions_panel);
    GroupLayout jPanel4Layout = new GroupLayout(find_replace_panel);
    find_replace_panel.setLayout(jPanel4Layout);
    jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGap(0, 222, Short.MAX_VALUE)
    );
    jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGap(0, 307, Short.MAX_VALUE)
    );
    function_panel.addTab(i18n.messages.getString("find_replace"), find_replace_panel);

      GroupLayout settings_panel_layout = new GroupLayout(settings_panel);
    settings_panel.setLayout(settings_panel_layout);
    settings_panel_layout.setHorizontalGroup(
            settings_panel_layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGap(0, 222, Short.MAX_VALUE)
    );
    settings_panel_layout.setVerticalGroup(
            settings_panel_layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGap(0, 307, Short.MAX_VALUE)
    );
    function_panel.addTab(i18n.messages.getString("settings"), settings_panel);
    ///TODO: 188nize
    jScrollPane3.setViewportView(text_input);
    text_analysis_table.setModel(new TextAnalysisTableModel());
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
                                  Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
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

      copy_menu_item.setText("Copy");
      copy_menu_item.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
              StringSelection stringSelection = new StringSelection (selection);
              Clipboard clpbrd = Toolkit.getDefaultToolkit().getSystemClipboard ();
              clpbrd.setContents(stringSelection, null);
          }
      });
      copy_menu_item.setAccelerator(KeyStroke.getKeyStroke('C', Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
      jMenu2.add(copy_menu_item);

      /*cut_menu_item.setText("Cut");
      cut_menu_item.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {

          }
      }); */
      
    jMenuBar1.add(jMenu2);
    setJMenuBar(jMenuBar1);

    GroupLayout layout = new GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                            .addContainerGap()
                                            .addComponent(jLabel1, GroupLayout.PREFERRED_SIZE, 222,
                                                    GroupLayout.PREFERRED_SIZE))
                                    .addComponent(function_panel, GroupLayout.PREFERRED_SIZE, 322,
                                            GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                    .addGroup(
                                            layout.createSequentialGroup() // will need to look at this and figure out if all of it is necessary
                                                    .addComponent(accent_button_1, GroupLayout.PREFERRED_SIZE, 40,
                                                            GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(accent_button_2, GroupLayout.PREFERRED_SIZE, 40,
                                                            GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(accent_button_3, GroupLayout.PREFERRED_SIZE, 40,
                                                            GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(accent_button_4, GroupLayout.PREFERRED_SIZE, 40,
                                                            GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(accent_button_5, GroupLayout.PREFERRED_SIZE, 40,
                                                            GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(accent_button_6, GroupLayout.PREFERRED_SIZE, 40,
                                                            GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(accent_button_7, GroupLayout.PREFERRED_SIZE, 40,
                                                            GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(accent_button_8, GroupLayout.PREFERRED_SIZE, 40,
                                                            GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED))
                                    .addGroup(layout.createSequentialGroup()
                                            .addComponent(jScrollPane3, GroupLayout.PREFERRED_SIZE, 507,
                                                    GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                    .addGroup(layout.createSequentialGroup()
                                                            .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 0,
                                                                    Short.MAX_VALUE)
                                                            .addContainerGap())
                                                    .addComponent(jLabel2)))))
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, GroupLayout.PREFERRED_SIZE, 33,
                        GroupLayout.PREFERRED_SIZE)
                    .addComponent(accent_button_1, GroupLayout.PREFERRED_SIZE, 34,
                        GroupLayout.PREFERRED_SIZE)
                    .addComponent(accent_button_2, GroupLayout.PREFERRED_SIZE, 34,
                        GroupLayout.PREFERRED_SIZE)
                    .addComponent(accent_button_3, GroupLayout.PREFERRED_SIZE, 34,
                        GroupLayout.PREFERRED_SIZE)
                    .addComponent(accent_button_4, GroupLayout.PREFERRED_SIZE, 34,
                        GroupLayout.PREFERRED_SIZE)
                    .addComponent(accent_button_5, GroupLayout.PREFERRED_SIZE, 34,
                        GroupLayout.PREFERRED_SIZE)
                    .addComponent(accent_button_6, GroupLayout.PREFERRED_SIZE, 34,
                        GroupLayout.PREFERRED_SIZE)
                    .addComponent(accent_button_7, GroupLayout.PREFERRED_SIZE, 34,
                        GroupLayout.PREFERRED_SIZE)
                    .addComponent(accent_button_8, GroupLayout.PREFERRED_SIZE, 34,
                        GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 92,
                            GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED,
                            GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(function_panel)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane3, GroupLayout.PREFERRED_SIZE, 233,
                                    GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())))
    );
      // maybe there's a more elegant way to do this?
      // I was thinking adding the action listener to the AccentButton's constructor
    accent_button_1.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            accentButtonPressed(accent_button_1);
        }
    });
      accent_button_2.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
              accentButtonPressed(accent_button_2);
          }
      });
      accent_button_3.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
              accentButtonPressed(accent_button_3);
          }
      });
      accent_button_4.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
              accentButtonPressed(accent_button_4);
          }
      });
      accent_button_5.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
              accentButtonPressed(accent_button_5);
          }
      });
      accent_button_6.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
              accentButtonPressed(accent_button_6);
          }
      });
      accent_button_7.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
              accentButtonPressed(accent_button_7);
          }
      });
      accent_button_8.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
              accentButtonPressed(accent_button_8);
          }
      });

    pack();
  }
    /* handles event that an AccentButton is pressed */
    public void accentButtonPressed(AccentButton accent_button) {
        setText(accent_button.insertAccent(selection, getText(), caret_location));
        text_input.requestFocusInWindow();
        if(selection == "")
            text_input.setCaretPosition(caret_location);
        else
            text_input.setCaretPosition(caret_location - selection.length());
    }

  /* returns text entered by user in the editor pane */
  String getText() {
    return text_input.getText();
  }
  /* sets the editor pane using String passed */
  void setText(String s) {
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
  private String selection = "";
  private Settings settings = new Settings();
    private AccentButton accent_button_1;
    private AccentButton accent_button_2;
    private AccentButton accent_button_3;
    private AccentButton accent_button_4;
    private AccentButton accent_button_5;
    private AccentButton accent_button_6;
    private AccentButton accent_button_7;
    private AccentButton accent_button_8;
    private javax.swing.JLabel settings_label_1;
    private javax.swing.JTextField display_accents;
    private javax.swing.JButton configure_accents_button;
  private int caret_location = 0;
  // End of variables declaration
  private final UndoManager undo = new
  UndoManager(); /* manager for undo/redo support */
}
