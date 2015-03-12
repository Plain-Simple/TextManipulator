package plainsimple.textmanipulator;

import org.apache.commons.cli.*;

import java.util.ArrayList;

public class Shell {
    Options options = new Options();
    ManipulateText manip = new ManipulateText();
    public Shell(String[] args) {
        CommandLineParser parser = new BasicParser(); // todo: try implementing defaultparser
        setUpOptions();
        try {
            CommandLine cmd = parser.parse(options, args);
            executeCommand(cmd);
        } catch(ParseException e) {
            Println("Error parsing command.");
            Println(e.getMessage());
        }
        HelpFormatter help = new HelpFormatter();
        help.printHelp("TextManipulator", options);
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
        ArrayList<TextFile> files = new ArrayList<>();
        /* need to know if function is executed on file or batch */
        if(cmd.hasOption("f")) { /* read in the file */
            TextFile read_file = new TextFile(cmd.getOptionValue("f"));
            if(read_file.fileExists()) {
                files.add(read_file);
            } else {
                Println("Error: file does not exist or could not be accessed");
            }
        } else if(cmd.hasOption("b")) { /* load batch */

        } else { /* cannot execute function if file/batch is not specified */
            Println("Error: no file or batch specified");
        }
        /* text objects to be manipulated */

        for(int i = 0; i < files.size(); i++) {
            String[] manipulated_text;
            if (cmd.hasOption("w")) {
                manipulated_text = manip.splitIntoWords(files.get(i).getFileText());
            } else if(cmd.hasOption("l")) {
                manipulated_text = manip.splitIntoLines(files.get(i).getFileText());
            } else if(cmd.hasOption("c")) {
                manipulated_text = manip.splitIntoChars(files.get(i).getFileText());
            } else if(cmd.hasOption("s")) {
                manipulated_text = manip.splitIntoSentences(files.get(i).getFileText());
            }
            if (cmd.hasOption("findreplace")) {
                String[] arguments = cmd.getOptionValues("findreplace");
                Println("findreplace " + arguments[0] + ", " + arguments[1]);

            } 
        }
    }
    public void Println(String s) {
        System.out.println(s);
    }
}
