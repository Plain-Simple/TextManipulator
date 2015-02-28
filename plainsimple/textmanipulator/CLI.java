package plainsimple.textmanipulator;

import java.io.File;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

class CLI {
  private ManipulateText manip = new ManipulateText();
  private Directory current_directory;
  private TextFile loaded_file;
  private FileBatch loaded_batch;
  private CLISettings settings = new CLISettings();
  @SuppressWarnings("HardCodedStringLiteral")
  public void startCLI() {
    settings.loadSettings("TextManipulator_CLISettings");
    loaded_file = new TextFile(settings.getSettings().get(0));
    loaded_batch = new FileBatch(settings.getSettings().get(1));
    current_directory = new Directory(Paths.get(settings.getSettings().get(
                                        2)).toAbsolutePath());
    Scanner scanner = new Scanner(System.in);
    System.out.println(i18n.getString("cli_welcome"));
    if(loaded_file.fileExists()) { /* make sure loaded file is valid and has been read successfully */
      Println("Current file loaded: " + loaded_file.getFileName());
    }
    if(loaded_batch.batchExists()) { // something weird is going on here?
      Println("Current batch loaded: " + loaded_batch.getBatchName());
    }
    if(current_directory.directoryExists()) { // ToDo: make a directory class instead of using paths
      Println("Current directory: " + current_directory.getPathAsString());
    }
    /* this runs forever, because the cli keeps going until the user exits */
    while (true) {
      System.out.println(i18n.getString("cli_function_prompt") +
                         " "); // how do we get it to say: Please enter a command:\n >>
      try {
        String userInput = scanner.nextLine();
        userInput = manip.removeExtraWhitespace(userInput);
        processCommand(loaded_file.getFileText(), userInput);
        Println("");
        // need a better way to update settings
        String[] updated_settings = new String[] {loaded_file.getPath(), loaded_batch.getBatchName(), current_directory.getPathAsString(), settings.getSettings().get(3)};
        settings.updateSettings("TextManipulator_CLISettings", updated_settings);
      } catch(IndexOutOfBoundsException | NoSuchElementException
                e) { // handle it or just ignore it?
      }
    }
  }

  @SuppressWarnings("HardCodedStringLiteral")
  String loadFile() {
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
    printFormatted("file load", i18n.getString("file_load_description"));
    printFormatted("file mergetext", i18n.getString("mergetext_description"));
    printFormatted("file findreplace", i18n.getString("findreplace_description"));
    printFormatted("file removearg", i18n.getString("removeargument_description"));
    printFormatted("file commaseparate",
                   i18n.getString("commaseparatevalues_description"));
    printFormatted("file lineseparate",
                   i18n.getString("lineseparatevalues_description"));
    printFormatted("file splitsentences",
                   i18n.getString("splitsentences_description"));
    printFormatted("file removepunctuation",
                   i18n.getString("removepunctuation_description"));
    printFormatted("file uppercase", i18n.getString("uppercase_description"));
    printFormatted("file lowercase", i18n.getString("lowercase_description"));
    printFormatted("file print", i18n.getString("print_description"));
    printFormatted("file prefix", i18n.getString("addprefix_description"));
    printFormatted("file suffix", i18n.getString("addsuffix_description"));
    printFormatted("file removeduplicates",
                   i18n.getString("removeduplicatelines_description"));
    printFormatted("file removecontaining",
                   i18n.getString("removelinescontaining_description"));
    printFormatted("file scramble", i18n.getString("scramblelines_description"));
    printFormatted("file sortABC",
                   i18n.getString("sortlinesalphabetically_description"));
    printFormatted("file sortbysize",
                   i18n.getString("sortlinesbysize_description"));
    printFormatted("file number", i18n.getString("numberlines_description"));
    printFormatted("file removeempty",
                   i18n.getString("removeemptylines_description"));
    printFormatted("file compare", i18n.getString("comparelines_description"));
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
    ArrayList<String> arguments = getArguments(user_input);
    if(arguments.get(0).equals("file")) { /* switch case all file commands */
      processFileCommand(arguments, text);
    } else if(arguments.get(0).equals("batch")) {
      processBatchCommand(arguments);
    } else if(arguments.get(0).equals("help")) { // expand into a full help function
      outputFunctionsList();
    } else if(arguments.get(0).equals("cd")) {
      if(arguments.get(1).equals("..")) {
        current_directory.setAsParent(); // do we need error-checking?
        Println("Directory successfully changed to \"" +
                current_directory.getPathAsString() + "\".");
      } else {
        boolean change_success = current_directory.changeDirectory(arguments.get(1));
        if(change_success) {
          Println("Directory changed to \"" + current_directory.getPathAsString() +
                  "\".");
        } else {
          Println("Error changing directory to \"" + arguments.get(
                    1) + "\": Directory does not exist.");
        }
      }
    } else if(arguments.get(
                0).equals("dir")) { // ToDo: Clean-up and better error-checking
      boolean get_files_success = false;
      ArrayList<TextFile> files_in_directory = new ArrayList<>();
      if(arguments.size() == 1) { /* one parameter - dir in current directory */
        files_in_directory = current_directory.getFilesInDirectory();
        get_files_success = true;
      } else {
        get_files_success = current_directory.changeDirectory(arguments.get(1));
        files_in_directory = current_directory.getFilesInDirectory();
      }
      if(get_files_success) {
        Println("Files in directory \"" + current_directory.getPathAsString() + "\":");
        for (int i = 0; i < files_in_directory.size(); i++) {
          Println(files_in_directory.get(i).getFileName());
        }
      } else {
        Println("Could not access \"" + arguments.get(1) + "\"");
      }
    } else if(arguments.get(0).equals("exit")) {
      System.exit(0);
    } else { // lookup arguments.get(0) in file directories and existing batches
      // also try forcing first arg to lowercase and checking again?
    }
  }
  public void processFileCommand(ArrayList<String> parameters, String file_text) {
    switch(parameters.get(1)) {
    case("load"):
      /* try creating a new file using current_directory + file_name */
      try {
        TextFile test_file = new TextFile(current_directory.toString() + "\\" +
                                          parameters.get(2));
        if (test_file.fileExists()) { /* file exists; load it */
          loaded_file = test_file;
          System.out.println("File \"" + loaded_file.getFileName() +
                             "\" loaded successfully.");
          break;
        }
      } catch(InvalidPathException e) { // unhandled
      }
      try {/* try again in the case user entered full path */
        TextFile test_file = new TextFile(parameters.get(2));
        if (test_file.fileExists()) {
          loaded_file = test_file;
          System.out.println("File \"" + loaded_file.getFileName() +
                             "\" loaded successfully.");
        } else {
          System.out.println("Could not load file.");
        }
      } catch(InvalidPathException e) {
        Println("Invalid path");
      }
      break;
    case("mergetext"):
      Println("This command hasn't been implemented yet");
      break;
    case("findreplace"):
      break;
    case("removearg"):
      break;
    case("commaseparate"):
      break;
    case("lineseparate"):
      break;
    case("splitsentences"):
      break;
        case("split"):
            manip.splitBySeparator(file_text, parameters.get(2));
            break;
    case("removepunctuation"):
      break;
    case("uppercase"):
      break;
    case("lowercase"):
      break;
    case("print"):
      Println("File Text:\n----------" + loaded_file.getFileText() + "\n---------");
      break;
    case("prefix"):
      break;
    case("suffix"):
      break;
    case("removeduplicates"):
      break;
    case("removecontaining"):
      break;
    case("scramble"):
      break;
    case("sortbysize"):
      break;
    case("number"):
      break;
    case("removeempty"):
      break;
    case("compare"):
      break;
    }
  }
  public void processBatchCommand(ArrayList<String> parameters) {
  }
  public ArrayList<String> getArguments(String
                                        user_command) { // ToDo: remove parameter num_arguments
    ArrayList<String> arguments = new ArrayList<String>();
    int location = 0;
    while(location < user_command.length()) {
      int next_space = user_command.indexOf(" ", location);
      if(next_space == -1) { /* couldn't find another space. Last argument */
        arguments.add(user_command.substring(location));
        location = user_command.length();
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
