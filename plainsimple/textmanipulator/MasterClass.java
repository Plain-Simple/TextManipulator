package plainsimple.textmanipulator;

class MasterClass {
  public static void main(String args[]) {
    i18n.setUpi18n(args);
    /* start the actual program: */
    boolean RUN_GUI = true;
    /* set this to false to run in CLI mode. There will be a better way
     * to do this in the future */
    if (RUN_GUI) {
      GUI gui = new GUI();
      gui.startGUI();
    }
    CLI cli = new CLI();
    cli.startCLI();
  }
}
