package plainsimple;

import c10n.C10N;
import org.apache.commons.cli.*;
import plainsimple.util.TextUtil;

import java.util.ArrayList;

public class Shell {
    /* used to configure Apache cli */
    private Options options = new Options();
    /* used to access textmanipulation methods */
    private static final TextUtil manip = new TextUtil();
    /* used to access and write settings/data */
    private Settings settings = new Settings();
    /* used to access C10N messages */
    private static final Messages msg = C10N.get(Messages.class);
    public Shell(String[] args) {
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
        options.addOptionGroup(getBatchGroup());
        options.addOption(getDestructiveOption());
        options.addOption(getDoEmptyOption());
    }
    /* set up option group for main function */
    public OptionGroup getFunctionGroup() {
        OptionGroup function = new OptionGroup();
        //function.setRequired(true);
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
        Option count = new Option("count", false, "counts number of specified textobject (use -w,-l,-c,-sep, etc.");
        count.setArgs(1);
        function.addOption(count);
        return function;
    }
    /* set up option group to get batch or file name */
    private OptionGroup getTextTypeGroup() {
        OptionGroup object_type = new OptionGroup();
        //object_type.setRequired(true);
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
    private OptionGroup getBatchGroup() { // todo: many of these functions should be extended to files
        OptionGroup batch_commands = new OptionGroup();
        Option new_batch = new Option("new", false, "create a new batch with specified name");
        new_batch.setArgs(1);
        batch_commands.addOption(new_batch);
        Option add_file = new Option("add", false, "add specified file to specified batch");
        add_file.setArgs(2);
        batch_commands.addOption(add_file);
        Option remove_duplicates = new Option("removeduplicates", false, "removes duplicate files from batch");
        remove_duplicates.setArgs(1);
        batch_commands.addOption(remove_duplicates);
        Option rename = new Option("rename", false, "renames batch");
        rename.setArgs(2);
        batch_commands.addOption(rename);
        Option delete = new Option("delete", false, "deletes batch");
        delete.setArgs(1);
        batch_commands.addOption(delete);
        return batch_commands;
    }
    private Option getDestructiveOption() {
        return new Option("destructive", false, "destroy delimiters?");
    }
    private Option getDoEmptyOption() {
        return new Option("doEmpty", false, "execute on empty textobjects");
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
                Println(msg.file_error(read_file.getFileName()));
            }
        } else if(cmd.hasOption("b")) { /* read in batch */
            String batch_name = cmd.getOptionValue("b");
            if(settings.batchExists(batch_name)) {
                files = settings.getBatch(batch_name).getFiles();
            } else
                Println(msg.batch_error(batch_name));
        } else if(cmd.hasOption("new")) {
            FileBatch new_batch = new FileBatch(cmd.getOptionValue("new"));
            settings.addBatch(new_batch);
            Println(msg.batch_created(new_batch.getName()));
        } else if(cmd.hasOption("add")) {
            String[] options = cmd.getOptionValues("add");
            if(settings.batchExists(options[0])) { /* batch exists */
                /* retrieve batch */
                FileBatch specified_batch = settings.getBatch(options[0]);
                FileBatch new_batch = specified_batch;
                TextFile add_file = new TextFile(options[1]);
                if (new_batch.addFile(add_file)) { /* false if textfile is invalid */
                    if(settings.replaceBatch(specified_batch, new_batch)) /* overwrite old batch */
                        Println(msg.file_added(add_file.getFileName(), new_batch.getName()));
                    else
                        Println("Error adding file");
                } else
                    Println(msg.file_error(add_file.getFileName()));
            } else
                Println(msg.batch_error(options[0]));
        } else if(cmd.hasOption("removeduplicates")) {
            String batch_name = cmd.getOptionValue("removeduplicates");
            if(settings.batchExists(batch_name)) {
                settings.replaceBatch(settings.getBatch(batch_name), settings.getBatch(batch_name).removeDuplicates());
            } else
                Println(msg.batch_error(batch_name));
        } else if(cmd.hasOption("rename")) {
            String[] batches = cmd.getOptionValues("rename");
            if(settings.batchExists(batches[0])) {
                settings.replaceBatch(settings.getBatch(batches[0]), settings.getBatch(batches[0]).rename(batches[1]));
            } else
                Println(msg.batch_error(batches[0]));
        } else if(cmd.hasOption("delete")) {
            String batch_name = cmd.getOptionValue("delete");
            if(settings.batchExists(batch_name))
                settings.removeBatch(settings.getBatch(batch_name));
            else
                Println(msg.batch_error(batch_name));
        }
        else { /* cannot execute function if file/batch is not specified */
            Println(msg.no_target());
        }
        /* text objects to be manipulated */

        for(int i = 0; i < files.size(); i++) {
            ArrayList<String[]> split_result;
            String[] manipulated_text;
            String[] delimiters;
            String[] function_args;
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
