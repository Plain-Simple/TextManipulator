package plainsimple.textmanipulator;

import org.apache.commons.cli.*;

public class Shell {
    Options options = new Options();
    public Shell(String[] args) {
        CommandLineParser parser = new BasicParser(); // todo: try implementing defaultparser
        try {
            CommandLine cmd = parser.parse(options, args);
            if(cmd.hasOption("findreplace")) {
                Println("Option findreplace detected.");
                String[] arguments = cmd.getOptionValues("findreplace");
                for(int i = 0; i < arguments.length; i++)
                    Println(arguments[i]);
            }
        } catch(ParseException e) {
            Println("Error parsing command.");
        }
        for(int i = 0; i < args.length; i++)
            Println("args[" + i + "] = " + args[i]);
        System.exit(0);
    }
    public void setUpOptions() {
        OptionGroup function = new OptionGroup();
        Option findreplace = new Option("findreplace", false, "regex find and replace withing specified text");
        findreplace.setArgs(2);
        findreplace.setValueSeparator(',');
        function.addOption(findreplace);
        options.addOptionGroup(function);
    }
    public void Println(String s) {
        System.out.println(s);
    }
}
