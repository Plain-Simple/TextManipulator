package plainsimple.textmanipulator;

class MasterClass {
  public static void main(String args[]) {
    i18n.setUpi18n(args);
    /* 0 = CLI, 1 = JavaFX, 2 = Swing

     * do not rely on Swing, it is no
     * longer in development and will
     * be discontinued by us soon  */

    int RUN_INTERFACE = 0;
    if (RUN_INTERFACE == 0) {
      CLI cli = new CLI();
      cli.startCLI();
    } if (RUN_INTERFACE ==1) {
      JavaFXGUI.launch(JavaFXGUI.class, args);
    } if (RUN_INTERFACE == 2) {
      GUI gui = new GUI();
      gui.startGUI();
    }
  }
}
