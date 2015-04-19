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

    int RUN_INTERFACE = 3;
    if (RUN_INTERFACE ==1) {
      JavaFXGUI.launch(JavaFXGUI.class, args);
    } if (RUN_INTERFACE == 3) {
          Shell shell = new Shell(args);
      }
  }
}
