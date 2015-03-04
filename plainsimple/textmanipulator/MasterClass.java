package plainsimple.textmanipulator;

import c10n.C10N;
import c10n.annotations.DefaultC10NAnnotations;

class MasterClass {
  public static void main(String args[]) {
    /* required for i18n */
    C10N.configure(new DefaultC10NAnnotations());
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
      GUI_Old guiOld = new GUI_Old();
      guiOld.startGUI();
    }
  }
}
