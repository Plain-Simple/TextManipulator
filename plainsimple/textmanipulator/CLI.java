/* I think you could make this a lot easier on yourself if you create a loop. At the beginning of the loop is the
console prompt (function_prompt). Then a function to take user input, and another one to analyze the input. The
analyze function would determine the command entered, and then how many parameters to take. It could then call
the appropriate function and pass it those parameters. Any errors encountered could be handled locally or just break
the loop.

Like this: (abbreviated)
Prinln("cli_welcome");
do {
Println("function_prompt");
String input = GetInput();
AnalyzeInput(input);
ExecuteInput(parameters);
}while(userInput != "quit");
.... just an idea

possible commands:
load file - lf
 */


package plainsimple.textmanipulator;

import java.util.NoSuchElementException;
import java.util.Scanner;

class CLI {
  public void startCLI() {
    Scanner scanner = new Scanner(System.in);
    System.out.println(i18n.messages.getString("cli_welcome") + "\n\n");
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
    try {
      String text = new Scanner(filePath).useDelimiter("\\Z").next();
    } catch(NoSuchElementException e) {
      System.out.println(i18n.messages.getString("invalid_file"));
    }
  }
  @SuppressWarnings("HardCodedStringLiteral")
  void outputFunctionsList() {
    System.out.println("\naddprefix: " +
                       i18n.messages.getString("addprefix_description"));
    System.out.println("\naddsuffix: " +
                       i18n.messages.getString("addsuffix_description"));
    System.out.println("\nremoveduplicatelines" +
                       i18n.messages.getString("removeduplicatelines_description"));
    System.out.println("\nremovelinescontaining: " +
                       i18n.messages.getString("removelinescontaining_description"));
    System.out.println("\nscramblelines: " +
                       i18n.messages.getString("scramblelines_description"));
    System.out.println("\nsortlinesalphabetically" +
                       i18n.messages.getString("sortlinesalphabetically_description"));
    System.out.println("\nsortlinesbysize: " +
                       i18n.messages.getString("sortlinesbysize_description"));
    System.out.println("\nnumberlines: " +
                       i18n.messages.getString("numberlines_description"));
    System.out.println("\nremoveemptylines: " +
                       i18n.messages.getString("removeemptylines_description"));
    System.out.println("\nmergetext: " +
                       i18n.messages.getString("mergetext_description"));
    System.out.println("\nfindreplace: " +
                       i18n.messages.getString("findreplace_description"));
    System.out.println("\nremoveargument: " +
                       i18n.messages.getString("removeargument_description"));
    System.out.println("\ncommaseparatevalues: " +
                       i18n.messages.getString("commaseparatevalues_description"));
    System.out.println("\nlineseparatevalues: " +
                       i18n.messages.getString("lineseparatevalues_description"));
    System.out.println("\nsplitsentences: " +
                       i18n.messages.getString("splitsentences_description"));
    System.out.println("\ncomparelines: " +
                       i18n.messages.getString("comparelines_description"));
    System.out.println("\nremovepunctuation: " +
                       i18n.messages.getString("removepunctuation_description"));
    System.out.println("\nuppercase: " +
                       i18n.messages.getString("uppercase_description"));
    System.out.println("\nlowercase: " +
                       i18n.messages.getString("lowercase_description"));
    System.out.println("\nprint: " +
                       i18n.messages.getString("print_description"));
  }
  public void modifySettings() {
  }
}
