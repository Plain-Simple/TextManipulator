package plainsimple.textmanipulator;

import java.util.Locale;
import java.util.ResourceBundle;

@SuppressWarnings("HardCodedStringLiteral")
class i18n {
  static Locale currentLocale = new Locale ("en", "US");
  /* sets up all internationalization stuff */
  static void setUpi18n(String[] args) {
    /* change locale to user-provided one if it's valid */
    String language, country;
    if (args.length == 2) {
      /* if the program is given a 2-length locale, use that */
      language = args[0];
      country = args[1];
      currentLocale = new Locale(language, country);
    }
    /* makes a Locale from the language and country; needed later on */
  }
  static String getString(String string) {
    return ResourceBundle.getBundle("MessagesBundle",
                                    currentLocale).getString(string);
  }
}
