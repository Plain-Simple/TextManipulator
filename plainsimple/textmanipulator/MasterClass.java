package plainsimple.textmanipulator;

import java.util.Locale;
import java.util.ResourceBundle;
class MasterClass {
    public static void main(String args[]) {
       /* take care of locale stuff */
        /* default to US English in case nothing is found*/
        String language = "en";
        String country = "US";

        if (args.length == 2) {
            /* if the program is given a 2-length locale, use that */
            language = args[0];
            country = args[1];
        }
        /* makes a Locale from the language and country; needed later on */
        Locale currentLocale = new Locale(language, country);
        /* the ResourceBundle is located in i18n so that it can be easily referenced without
         * typing out MasterClass every time you need a string */
        i18n.messages = ResourceBundle.getBundle("MessagesBundle",currentLocale);

        /* start the actual program: */
        boolean RunGUI = true;
        /* set this to false to run in CLI mode. There will be a better way
         * to do this in the future */
        if (RunGUI) {
            GUI gui = new GUI();
            gui.StartGUI();
        }
        CLI cli = new CLI();
        cli.StartCLI();

    }
}
