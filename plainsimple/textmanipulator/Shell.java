package plainsimple.textmanipulator;

import org.apache.commons.cli.*;

public class Shell {
    Options options = new Options();
    public Shell(String[] args) {
        CommandLineParser parser = new BasicParser(); // todo: try implementing defaultparser
        setUpOptions();
        try {
            CommandLine cmd = parser.parse(options, args);
            if(cmd.hasOption("findreplace")) {
                String[] arguments = cmd.getOptionValues("findreplace");
                Println("findreplace " + arguments[0] + ", " + arguments[1]);

            }
            if(cmd.hasOption("f")) {
                Println("File to manipulate: " + cmd.getOptionValue("f"));
            }
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
    public void Println(String s) {
        System.out.println(s);
    }
}
