package plainsimple.textmanipulator;

import java.util.NoSuchElementException;
import java.util.Scanner;

class CLI {
  @SuppressWarnings("HardCodedStringLiteral")
  public void startCLI() {
    Scanner scanner = new Scanner(System.in);
    System.out.println(i18n.getString("cli_welcome") + "\n");
    String text = loadFile();
    /* this runs forever, because the cli keeps going until the user exits */
    while (true) {
      System.out.println(i18n.getString("cli_function_prompt") + " ");
      String userInput = scanner.nextLine();
      processCommand(text, userInput);
    }
  }

  @SuppressWarnings("HardCodedStringLiteral")
  String loadFile() {
    System.out.println(i18n.getString("cli_file_prompt"));
    Scanner scanner = new Scanner(System.in);
    String filePath = scanner.nextLine();
    String text = "";
    /* import file_path text file into string called text */
    try {
      text = new Scanner(filePath).useDelimiter("\\Z").next();
    } catch (NoSuchElementException e) {
      System.out.println(i18n.getString("invalid_file"));
    }
    return text;
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

  void processCommand(String text, String user_input) {
    ManipulateText manip = new ManipulateText();
    switch (user_input) {
    case "help":
      outputFunctionsList();
      break;
    case "exit":
      System.exit(0);
      break;
    case "addprefix":
      text = manip.addPrefixSuffix(text,
                                   getArgument(i18n.getString("prefix")), "");
      break;
    case "addsuffix":
      text = manip.addPrefixSuffix(text, "",
                                   getArgument(i18n.getString("suffix")));
      break;
    case "removeduplicatelines":
      text = manip.removeDuplicateLines(text);
      break;
    case "removelinescontaining":
      text = manip.removeLinesContaining(text,
          getArgument(i18n.getString("containing_what")));
      break;
    case "scramblelines":
      text = manip.scrambleLines(text);
      break;
    case "sortlinesalphabetically":
      text = manip.sortLinesAlphabetically(text);
      break;
    case "sortlinesbysize":
      text = manip.sortLinesBySize(text);
      break;
    case "numberlines":
      text = manip.numberLines(text,
                               getArgument(i18n.getString("number_prefix")),
                               getArgument(i18n.getString("number_suffix")));
      break;
    case "removeemptylines":
      text = manip.removeEmptyLines(text);
      break;
    case "mergetext":
      /* this isn't implemented yet because we need to figure out whether the
         user will type in the text or specify a filename
       */
      break;
    case "findreplace":
      text = manip.findReplace(text,
                               getArgument(i18n.getString("text_to_find")),
                               getArgument(i18n.getString("text_for_replace")));
      break;
    case "removeargument":
      text = manip.removeArgument(text,
          getArgument(i18n.getString("argument_to_remove")));
      break;
    case "commaseparatevalues":
      text = manip.commaSeparateValues(text);
      break;
    case "lineseparatevalues":
      /* we'll need to figure out how to get a char */
      break;
    case "splitsentences":
      text = manip.splitSentences(text);
      break;
    case "comparelines":
      /* that function isn't even written yet so I'll do this later */
      break;
    case "removepunctuation":
      text = manip.removePunctuation(text);
      break;
    case "uppercase":
      text = manip.forceUppercase(text);
      break;
    case "lowercase":
      text = manip.forceLowercase(text);
      break;
    case "print":
      /* we'll probably need a different function to work in the context of a CLI */
      break;
    default:
      System.out.println(i18n.getString("invalid_cli_option"));
      outputFunctionsList();
      break;
    }
  }
  String getArgument(String requested_argument) {
    Scanner scanner = new Scanner(System.in);
    System.out.println(i18n.getString("argument_prompt") + " " +
        requested_argument + ": ");
    return scanner.nextLine();
  }

}