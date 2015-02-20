package plainsimple.textmanipulator;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

class CLI {
  private ManipulateText manip = new ManipulateText();
    private String current_directory = "";
  @SuppressWarnings("HardCodedStringLiteral")
  public void startCLI() {
    Scanner scanner = new Scanner(System.in);
    System.out.println(i18n.getString("cli_welcome") + "\n");
    String text = loadFile();
    /* this runs forever, because the cli keeps going until the user exits */
    while (true) {
      System.out.println(i18n.getString("cli_function_prompt") + " ");
      String userInput = scanner.nextLine();
      userInput = manip.removeExtraWhitespace(userInput);
      processCommand(text, userInput);
      Println("");
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
  /* prints left-justified 25 characters and 70 characters, respectively.
   * Used to format stuff in outputFunctionsList() */
  public void printFormatted(String function, String description) {
    System.out.printf("\t%-25s %-70s %n", function, description);
  }
  @SuppressWarnings("HardCodedStringLiteral")
  void outputFunctionsList() {
    Println("Available Functions--------------------------------------------------------");
    printFormatted("mergetext", i18n.getString("mergetext_description"));
    printFormatted("findreplace", i18n.getString("findreplace_description"));
    printFormatted("removeargument", i18n.getString("removeargument_description"));
    printFormatted("commaseparatevalues",
                   i18n.getString("commaseparatevalues_description"));
    printFormatted("lineseparatevalues",
                   i18n.getString("lineseparatevalues_description"));
    printFormatted("splitsentences", i18n.getString("splitsentences_description"));
    printFormatted("removepunctuation",
                   i18n.getString("removepunctuation_description"));
    printFormatted("uppercase", i18n.getString("uppercase_description"));
    printFormatted("lowercase", i18n.getString("lowercase_description"));
    printFormatted("print", i18n.getString("print_description"));
      Println("\n\tLine Functions");
      printFormatted("prefix", i18n.getString("addprefix_description"));
      printFormatted("suffix", i18n.getString("addsuffix_description"));
      printFormatted("removeduplicates",
              i18n.getString("removeduplicatelines_description"));
      printFormatted("removecontaining",
              i18n.getString("removelinescontaining_description"));
      printFormatted("scramble", i18n.getString("scramblelines_description"));
      printFormatted("sortABC",
              i18n.getString("sortlinesalphabetically_description"));
      printFormatted("sortbysize",
              i18n.getString("sortlinesbysize_description"));
      printFormatted("number", i18n.getString("numberlines_description"));
      printFormatted("removeempty",
              i18n.getString("removeemptylines_description"));
      printFormatted("compare", i18n.getString("comparelines_description"));
    Println("-------------------------------------------------------------------------------------------");
  }
  /* for each command:
  check to see if it starts with the name of a command
  get an arraylist of arguments
  manipulate text using arguments
   */
  void processCommand(String text,
                      String user_input) { // ToDo: improve console output
    // e.g.: "findreplace: 5 instances replaced"
    ArrayList<String> arguments = new ArrayList<>();
    if(user_input.equals("help")) {
      outputFunctionsList();
    } else if(user_input.equals("exit")) {
      System.exit(0);
    } else if(user_input.startsWith("prefix ")) {
      /* user_input.substring(10) removes the "addprefix " at the beginning of the String */
      arguments = getArguments(1, user_input.substring(10));
      text = manip.addPrefixSuffix(text, arguments.get(0), "");
    } else if(user_input.startsWith("suffix ")) {
      arguments = getArguments(1, user_input.substring(10));
      text = manip.addPrefixSuffix(text, arguments.get(0), "");
    } else if(user_input.startsWith("removeduplicates")) {
      text = manip.removeDuplicateLines(text);
    } else if(user_input.startsWith("removecontaining ")) {
      arguments = getArguments(1, user_input.substring(22));
      text = manip.removeLinesContaining(text, arguments.get(0));
    } else if(user_input.startsWith("scramble")) {
      text = manip.scrambleLines(text);
    } else if(user_input.startsWith("sortABC")) {
      text = manip.sortLinesAlphabetically(text);
    } else if(user_input.startsWith("sortbysize")) {
      text = manip.sortLinesBySize(text);
    } else if(user_input.startsWith("number ")) {
      arguments = getArguments(2, user_input.substring(12));
      text = manip.numberLines(text, arguments.get(0), arguments.get(1));
    } else if(user_input.startsWith("removeempty")) {
      text = manip.removeEmptyLines(text);
    } else if(user_input.startsWith("mergetext")) {
      Println("Feature hasn't been implemented yet");
    } else if(user_input.startsWith("findreplace ")) {
      arguments = getArguments(2, user_input.substring(12));
      text = manip.findReplace(text, arguments.get(0), arguments.get(1));
    } else if(user_input.startsWith("removeargument ")) {
      arguments = getArguments(1, user_input.substring(15));
      text = manip.removeArgument(text, arguments.get(0));
    } else if(user_input.startsWith("commaseperatevalues")) {
      text = manip.commaSeparateValues(text);
    } else if(user_input.startsWith("lineseparatevalues ")) {
      arguments = getArguments(1,
                               user_input.substring(19)); // ToDo: better error-handling?
      text = manip.lineSeparateValues(text, arguments.get(0).charAt(0));
    } else if(user_input.startsWith("splitsentences")) {
      text = manip.splitSentences(text);
    } else if(user_input.startsWith("compare ")) {
      arguments = getArguments(2, user_input.substring(13));
      try {
        manip.compareLines(text, Integer.parseInt(arguments.get(0)),
                           Integer.parseInt(arguments.get(1)));
      } catch(NumberFormatException e) {
        Println("Invalid parameter entered. Must be an int"); // ToDo: better error messages
      }
    } else if(user_input.startsWith("removepunctuation")) {
      text = manip.removePunctuation(text);
    } else if(user_input.startsWith("uppercase")) {
      text = manip.forceUppercase(text);
    } else if(user_input.startsWith("lowercase")) {
      text = manip.forceLowercase(text);
    } else {
      int space = user_input.indexOf(" ");
      if(space == -1) {
        Println("Did not recognize command \"" + user_input + "\"");
      } else {
        Println("Did not recognize command \"" + user_input.substring(0,
                space) + "\"");
      }
    }
    Println("Debugging: Parameters were " + arguments.toString());
  }
  public ArrayList<String> getArguments(int num_arguments, String user_command) { // ToDo: remove parameter num_arguments
    ArrayList<String> arguments = new ArrayList<String>();
    int location = 0;
    while(arguments.size() < num_arguments) {
      int next_space = user_command.indexOf(" ", location);
      if(next_space == -1) {
        arguments.add(user_command.substring(location));
      } else {
        arguments.add(user_command.substring(location, next_space));
        location = next_space + 1;
      }
    }
    return arguments;
  }
  public void Println(String s) {
    System.out.println(s);
  }

}