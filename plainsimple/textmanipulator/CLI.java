package plainsimple.textmanipulator;

import java.util.NoSuchElementException;
import java.util.Scanner;

class CLI {
  public void startCLI() {
    System.out.println("ŝanĝaŭ");
    Scanner scanner = new Scanner(System.in);
    System.out.println(i18n.getString("cli_welcome") + "\n\n");
    /* this runs forever, because the cli keeps going until the user exits */
    while (true) {
      System.out.println(i18n.getString("function_prompt") + "\n");
      String userInput = scanner.nextLine();
      switch (userInput) {
      case "help":
        outputFunctionsList();
        break;
      case "exit":
        System.exit(0);
        break;
      default:
        System.out.println(i18n.getString("invalid_cli_option"));
        outputFunctionsList();
        break;
      }
    }
  }
  void loadFile() {
    System.out.println(i18n.getString("cli_file_prompt"));
    Scanner scanner = new Scanner(System.in);
    String filePath = scanner.nextLine();
    /* import file_path text file into string called text */
    try {
      String text = new Scanner(filePath).useDelimiter("\\Z").next();
    } catch(NoSuchElementException e) {
      System.out.println(i18n.getString("invalid_file"));
    }
  }
  @SuppressWarnings("HardCodedStringLiteral")
  void outputFunctionsList() {
    System.out.println("\naddprefix: " +
                       i18n.getString("addprefix_description"));
    System.out.println("\naddsuffix: " +
                       i18n.getString("addsuffix_description"));
    System.out.println("\nremoveduplicatelines" +
                       i18n.getString("removeduplicatelines_description"));
    System.out.println("\nremovelinescontaining: " +
                       i18n.getString("removelinescontaining_description"));
    System.out.println("\nscramblelines: " +
                       i18n.getString("scramblelines_description"));
    System.out.println("\nsortlinesalphabetically" +
                       i18n.getString("sortlinesalphabetically_description"));
    System.out.println("\nsortlinesbysize: " +
                       i18n.getString("sortlinesbysize_description"));
    System.out.println("\nnumberlines: " +
                       i18n.getString("numberlines_description"));
    System.out.println("\nremoveemptylines: " +
                       i18n.getString("removeemptylines_description"));
    System.out.println("\nmergetext: " +
                       i18n.getString("mergetext_description"));
    System.out.println("\nfindreplace: " +
                       i18n.getString("findreplace_description"));
    System.out.println("\nremoveargument: " +
                       i18n.getString("removeargument_description"));
    System.out.println("\ncommaseparatevalues: " +
                       i18n.getString("commaseparatevalues_description"));
    System.out.println("\nlineseparatevalues: " +
                       i18n.getString("lineseparatevalues_description"));
    System.out.println("\nsplitsentences: " +
                       i18n.getString("splitsentences_description"));
    System.out.println("\ncomparelines: " +
                       i18n.getString("comparelines_description"));
    System.out.println("\nremovepunctuation: " +
                       i18n.getString("removepunctuation_description"));
    System.out.println("\nuppercase: " +
                       i18n.getString("uppercase_description"));
    System.out.println("\nlowercase: " +
                       i18n.getString("lowercase_description"));
    System.out.println("\nprint: " +
                       i18n.getString("print_description"));
  }
  public void modifySettings() {
  }
}
