package plainsimple.textmanipulator;

import java.util.Scanner;

class CLI {
  public void startCLI() {
    Scanner scanner = new Scanner(System.in);
    System.out.println(i18n.messages.getString("cli_welcome") + "\n\n");
    /* we need a way of catching errors if loadFile fails) */
    loadFile();
      /* this runs forever, because the cli keeps going until the user exits */
      while (true) {
        System.out.println(i18n.messages.getString("function_prompt") + "\n");
        String userInput = scanner.nextLine();
        switch (userInput) {
          case "help":
            outputFunctionsList();
            break;
          case "exit":
            System.exit(0);
            break;
          default:
            System.out.println(i18n.messages.getString("invalid_cli_option"));
            outputFunctionsList();
            break;
        }
      }
  }
  void loadFile() {
    System.out.println(i18n.messages.getString("cli_file_prompt"));
    Scanner scanner = new Scanner(System.in);
    String filePath = scanner.nextLine();
    /* import file_path text file into string called text */
    String text = new Scanner(filePath).useDelimiter("\\Z").next();
  }
  @SuppressWarnings("HardCodedStringLiteral")
  void outputFunctionsList() {
    System.out.println("\naddprefix" +
                       i18n.messages.getString("addprefix_description"));
    System.out.println("\naddsuffix" +
                       i18n.messages.getString("addsuffix_description"));
    System.out.println("\nremoveduplicatelines" +
                       i18n.messages.getString("removeduplicatelines_description"));
    System.out.println("\nremovelinescontaining" +
                       i18n.messages.getString("removelinescontaining_description"));
    System.out.println("\nscramblelines" +
                       i18n.messages.getString("scramblelines_description"));
    System.out.println("\nsortlinesalphabetically" +
                       i18n.messages.getString("sortlinesalphabetically_description"));
    System.out.println("\nsortlinesbysize" +
                       i18n.messages.getString("sortlinesbysize_description"));
    System.out.println("\nnumberlines" +
                       i18n.messages.getString("numberlines_description"));
    System.out.println("\nremoveemptylines" +
                       i18n.messages.getString("removeemptylines_description"));
    System.out.println("\nmergetext" +
                       i18n.messages.getString("mergetext_description"));
    System.out.println("\nfindreplace" +
                       i18n.messages.getString("findreplace_description"));
    System.out.println("\nremoveargument" +
                       i18n.messages.getString("removeargument_description"));
    System.out.println("\ncommaseparatevalues" +
                       i18n.messages.getString("commaseparatevalues_description"));
    System.out.println("\nlineseparatevalues" +
                       i18n.messages.getString("lineseparatevalues_description"));
    System.out.println("\nsplitsentences" +
                       i18n.messages.getString("splitsentences_description"));
    System.out.println("\ncomparelines" +
                       i18n.messages.getString("comparelines_description"));
    System.out.println("\nremovepunctuation" +
                       i18n.messages.getString("removepunctuation_description"));
    System.out.println("\nuppercase" +
                       i18n.messages.getString("uppercase_description"));
    System.out.println("\nlowercase" +
                       i18n.messages.getString("lowercase_description"));
    System.out.println("\nprint" + i18n.messages.getString("print_description"));
  }
  public void modifySettings() {
  }
}
