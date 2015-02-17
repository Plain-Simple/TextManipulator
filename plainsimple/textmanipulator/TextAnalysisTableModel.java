/* Table model for TextAnalysis, Plain+Simple Text Manipulator. Code taken from Oracle JavaDocs */
package plainsimple.textmanipulator;
import javax.swing.table.AbstractTableModel;

class TextAnalysisTableModel extends AbstractTableModel {
  private final String[] columnNames = {i18n.getString("text_analysis"), ""};
  private final Object[][] data = {
    {i18n.getString("analysis_words"), 0},
    {i18n.getString("analysis_chars"), 0},
    {i18n.getString("analysis_sentences"), 0},
    {i18n.getString("analysis_lines"), 0}
  };

  public int getColumnCount() {
    return columnNames.length;
  }

  public int getRowCount() {
    return data.length;
  }

  public String getColumnName(int col) {
    return columnNames[col];
  }

  public Object getValueAt(int row, int col) {
    return data[row][col];
  }

  /*
   * JTable uses this method to determine the default renderer/
   * editor for each cell.  If we didn't implement this method,
   * then the last column would contain text ("true"/"false"),
   * rather than a check box.
   */
  public Class getColumnClass(int c) {
    return getValueAt(0, c).getClass();
  }

  /*
   * Don't need to implement this method unless your table's
   * editable.
   */
  public boolean isCellEditable(int row, int col) {
    //Note that the data/cell address is constant,
    //no matter where the cell appears onscreen.
    return col >= 2;
  }

  /*
   * Don't need to implement this method unless your table's
   * data can change.
   */
  public void setValueAt(Object value, int row, int col) {
    /*if (DEBUG) {
        System.out.println("Setting value at " + row + "," + col
                           + " to " + value
                           + " (an instance of "
                           + value.getClass() + ")");
    }*/
    data[row][col] = value;
    fireTableCellUpdated(row, col);
    /*if (DEBUG) {
        System.out.println("New value of data:");
        printDebugData();
    }*/
  }

  private void printDebugData() {
    int numRows = getRowCount();
    int numCols = getColumnCount();
    for (int i = 0; i < numRows; i++) {
      System.out.print("    row " + i + ":");
      for (int j = 0; j < numCols; j++) {
        System.out.print("  " + data[i][j]);
      }
      System.out.println();
    }
    System.out.println("--------------------------");
  }
}
