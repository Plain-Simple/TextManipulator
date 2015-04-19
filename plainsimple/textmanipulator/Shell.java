package plainsimple.textmanipulator;

import org.apache.commons.cli.*;

import java.util.ArrayList;
import java.util.Arrays;

public class Shell {
    Options options = new Options();
    ManipulateText manip = new ManipulateText();
    Settings settings = new Settings();
    public Shell(String[] args) {
        // debugging only
        Println("Command entered is " + new ArrayList<String>(Arrays.asList(args)).toString());
        CommandLineParser parser = new BasicParser();
        setUpOptions();
        try {
            CommandLine cmd = parser.parse(options, args);
            executeCommand(cmd);
        } catch(ParseException e) {
            Println("Error parsing command.");
            Println(e.getMessage());
        }
        //HelpFormatter help = new HelpFormatter();
        //help.printHelp("TextManipulator", options);
        System.exit(0);
    }
    public void setUpOptions() {
        options.addOptionGroup(getFunctionGroup());
        options.addOptionGroup(getTextTypeGroup());
        options.addOptionGroup(getObjectGroup());
    }
    /* set up option group for main function */
    public OptionGroup getFunctionGroup() {
        OptionGroup function = new OptionGroup();
        function.setRequired(true);
        Option findreplace = new Option("findreplace", false, "regex find and replace");
        findreplace.setArgs(2);
        findreplace.setValueSeparator(' ');
        function.addOption(findreplace);
        Option remove = new Option("remove", false, "remove all instances of specified String");
        remove.setArgs(1);
        function.addOption(remove);
        Option merge = new Option("merge", false, "merge two text objects"); // todo: finish this
        merge.setArgs(2);
        merge.setValueSeparator(' ');
        function.addOption(merge);
        Option uppercase = new Option("uppercase", false, "upercase all characters");
        function.addOption(uppercase);
        Option lowercase = new Option("lowercase", false, "lowercase all characters");
        function.addOption(lowercase);
        Option print = new Option("print", false, "prints text object(s)");
        function.addOption(print);
        Option prefix = new Option("prefix", false, "adds specified prefix before each object");
        prefix.setArgs(1);
        function.addOption(prefix);
        Option suffix = new Option("suffix", false, "adds specified suffix after each object");
        suffix.setArgs(1);
        function.addOption(suffix);
        Option sort = new Option("sort", false, "sorts text objects alphabetically or by size");
        sort.setArgs(1);
        function.addOption(sort);
        Option scramble = new Option("scramble", false, "scrambles specified text objects");
        function.addOption(scramble);
        Option number = new Option("number", false, "numbers specified objects using user preferences");
        number.setArgs(2);
        function.addOption(number);
        return function;
    }
    /* set up option group to get batch or file name */
    private OptionGroup getTextTypeGroup() {
        OptionGroup object_type = new OptionGroup();
        object_type.setRequired(true);
        Option file = new Option("f", false, "file to manipulate");
        file.setArgs(1);
        object_type.addOption(file);
        Option batch = new Option("b", false, "batch to manipulate");
        batch.setArgs(1);
        object_type.addOption(batch);
        return object_type;
    }
    private OptionGroup getObjectGroup() {
        OptionGroup object_type = new OptionGroup();
        Option words = new Option("w", false, "split text into words before manipulation");
        object_type.addOption(words);
        Option lines = new Option("l", false, "split text into lines before manipulation");
        object_type.addOption(lines);
        Option chars = new Option("c", false, "split text into chars before manipulation");
        object_type.addOption(chars);
        Option sentences = new Option("s", false, "split text into sentences before manipulation");
        object_type.addOption(sentences);
        Option custom_separator = new Option("sep", false, "split text using user-specified separator");
        custom_separator.setArgs(1);
        object_type.addOption(sentences);
        return object_type;
    }
    private OptionGroup getBatchGroup() {
        OptionGroup batch_commands = new OptionGroup();
        Option new_batch = new Option("new", false, "create a new batch with specified name");
        new_batch.setArgs(1);
        batch_commands.addOption(new_batch);
        return batch_commands;
    }
    /* executes command using command line object */
    private void executeCommand(CommandLine cmd) {
        /* contains files to be manipulated */
        ArrayList<TextFile> files = new ArrayList<>();
        if(cmd.hasOption("f")) { /* read in file */
            TextFile read_file = new TextFile(cmd.getOptionValue("f"));
            if(read_file.isValid()) {
                files.add(read_file);
            } else {
                Println("Error: file does not exist or could not be accessed");
            }
        } else if(cmd.hasOption("b")) { /* read in batch */
            String batch_name = cmd.getOptionValue("b");
            if(settings.batchExists(batch_name)) {
                files = settings.getBatch("TextManipulator_Settings", batch_name).getFiles();
            } else
                Println("Error: batch does not exist or could not be accessed");
        } else { /* cannot execute function if file/batch is not specified */
            Println("Error: no file or batch specified");
        }
        /* text objects to be manipulated */

        for(int i = 0; i < files.size(); i++) {
            ArrayList<String[]> split_result;
            String[] manipulated_text;
            String[] delimiters = new String[] {""};
            String[] function_args;
            boolean text_split = true; /* true if user designated to split text */
            if (cmd.hasOption("w")) {
                split_result = manip.splitIntoWords(files.get(i).getFileText());
            } else if(cmd.hasOption("l")) {
                split_result = manip.splitIntoLines(files.get(i).getFileText());
            } else if(cmd.hasOption("c")) {
                split_result = manip.splitIntoChars(files.get(i).getFileText());
            } //else if(cmd.hasOption("s")) {
                //split_result = manip.splitIntoSentences(files.get(i).getFileText());
            //}
            //else if(cmd.hasOption("sep")) {

            //}
            else{
                split_result = manip.getAsArray(files.get(i).getFileText());
                text_split = false;
            }
            delimiters = split_result.get(0);
            manipulated_text = split_result.get(1);
            if (cmd.hasOption("findreplace")) {
                function_args = cmd.getOptionValues("findreplace");
                manipulated_text = manip.findReplace(manipulated_text, function_args[0], function_args[1]);
            } else if (cmd.hasOption("remove")) {
                function_args = cmd.getOptionValues("remove");
                switch(cmd.getOptionValue("remove")) {
                    case "puctuation":
                        break;
                    case "duplicates":

                        break;
                    case "containing":
                        break;
                    case "empty":
                        break;
                    default: /* user has specified a regex pattern */

                }
            } else if (cmd.hasOption("merge")) {
                function_args = cmd.getOptionValues("merge"); // todo: error handling, batch merge?
                TextFile merge_file = new TextFile(function_args[0]);
                if(merge_file.isValid()) {
                   // manipulated_text = manip.mergeText(manipulated_text, )
                }

            } else if (cmd.hasOption("uppercase")) {
                manipulated_text = manip.forceUppercase(manipulated_text);
            } else if (cmd.hasOption("lowercase")) {
                manipulated_text = manip.forceLowercase(manipulated_text);
            } else if (cmd.hasOption("print")) {

            } else if (cmd.hasOption("prefix")) {
                function_args = cmd.getOptionValues("prefix");
                manipulated_text = manip.addPrefixSuffix(manipulated_text, function_args[0], "");
            } else if (cmd.hasOption("suffix")) {
                function_args = cmd.getOptionValues("suffix");
                manipulated_text = manip.addPrefixSuffix(manipulated_text, "", function_args[0]);
            } else if (cmd.hasOption("sort")) {
                function_args = cmd.getOptionValues("sort");
                if(function_args[0].equals("123"))
                    manipulated_text = manip.sortObjectsBySize(manipulated_text);
                else if(function_args[0].equals("ABC"))
                    manipulated_text = manip.sortObjectsAlphabetically(manipulated_text);
            } else if (cmd.hasOption("scramble")) {
                manipulated_text = manip.scrambleObjects(manipulated_text);
            } else if (cmd.hasOption("number")) {
                function_args = cmd.getOptionValues("number");
                manipulated_text = manip.numberObjects(manipulated_text, function_args[0], function_args[1]);
            }
            manipulated_text = manip.mergeText(delimiters, manipulated_text);
            files.get(i).setText(manipulated_text);
            if(files.get(i).writeFile()) { /* file overwritten successfully */
                Println("Success!");
            } else {
                Println("An error occurred");
            }
        }

    }
    public void Println(String s) {
        System.out.println(s);
    }
}
