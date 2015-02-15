/* Plain+Simple TextManipulator text manipulation functions */
package plainsimple.textmanipulator;
import java.io.*; // may be unnecessary
import java.util.Arrays;
import java.lang.Character; // may be unnecessary
import java.util.ArrayList;
class ManipulateText {
    /* adds prefix and suffix to each line */
    public String AddPrefixSuffix(String text, String prefix, String suffix) {
        //String delims = "[\\r\\n]+"; /* splits text into separate lines */
        //String[] lines = text.split(delims); /* create an array that holds each individual line */
        String[] lines = text.split("\\r?\\n"); /* create an array that holds each individual line */
        text = ""; /* clear variable once it has been split into lines*/
        for(int i = 0; i < lines.length; i++)
            text = text + prefix + lines[i] + suffix + "\n"; /* add each line to text with prefix and suffix */
        return text;
    }
    public String RemoveDuplicateLines(String text) { /// a little messy, but works. Feel free to refactor
        ArrayList<Integer> duplicates = new ArrayList<Integer>();
        String[] lines = text.split("\\r?\\n"); /* create an array that holds each individual line */
        text = ""; /* clear variable once it has been split into lines*/
        for(int i = 0; i < lines.length; i++) {
            for(int j = i + 1; j < lines.length; j++) { /* check to see if any of the later elements match */
                if(lines[j].equals(lines[i])) {/* duplicate found */
                    duplicates.add(j); /* add element position to list */
                }
            }
        }
        ArrayList<String> new_lines = new ArrayList<String>(); /* arraylist to hold the non-duplicate lines */
        for(int i = 0; i < lines.length; i++) {
            boolean copy_element = true; /* true if element is not a duplicate */
            for(int j = 0; j < duplicates.size(); j++) {
                if(i == duplicates.get(j))
                    copy_element = false;
            }
            if(copy_element)
                new_lines.add(lines[i]);
        }
        for(int i = 0; i < new_lines.size(); i++)
            text = text + new_lines.get(i) + "\n";
        return text;
    }
    public String RemoveLinesContaining(String text, String remove) {
        String[] lines = text.split("\\r?\\n"); /* create an array that holds each individual line */
        text = ""; /* clear variable once it has been split into lines*/
        for(int i = 0; i < lines.length; i++) { /* for each line... */
            if(lines[i].indexOf(remove) < 0) /* could not find String remove in line */
                text = text + lines[i] + "\n";
        }
        return text;
    }
    public String ScrambleLines(String text) {
        String[] lines = text.split("\\r?\\n"); /* create an array that holds each individual line */
        /* copy array to arraylist for easier manipulation */
        ArrayList<String> lines_list = new ArrayList<String>(Arrays.asList(lines));
        text = ""; /* clear variable once it has been split into lines*/
        while(lines_list.size() > 0) { /* runs until all lines have been copied */
            int line_number = GenerateRandomNumber(lines_list.size() - 1); /* generate random number within range of list */
            text = text + lines_list.get(line_number) + "\n"; /* copy corresponding element to text */
            lines_list.remove(line_number); /* remove the element from the list so it cannot be copied again */
        }
        return text;
    }
    public String SortLinesAlphabetically(String text) {
        String[] lines = text.split("\\r?\\n"); /* create an array that holds each individual line */
        text = ""; /* clear variable once it has been split into lines*/
        Arrays.sort(lines);
        for(int i = 0; i < lines.length; i++)
            text = text + lines[i] + "\n";
        return text;
    }
    public String SortLinesBySize(String text) {
        String[] lines = text.split("\\r?\\n"); /* create an array that holds each individual line */
        text = ""; /* clear variable once it has been split into lines*/
        for(int i = 1; i < lines.length; i++) { /* start at second element */
            int num = 1;
            String compare = lines[i];
            while(num <= i && compare.length() < lines[i - num].length()) { /* keeps looping while a smaller line
                exists behind the current element and num doesn't cause an arrayindexoutofbounds exception */
                lines[i - num + 1] = lines[i - num]; /* move larger element one to the right */
                lines[i - num] = compare; /* move compare one to the left */
                num++;
            }
        }
        for(int i = 0; i < lines.length; i++)
            text = text + lines[i] + "\n";
        return text;
    }
    public String NumberLines(String text, String prefix, String suffix) {
        /* prefix is what goes before the number itself, suffix is what goes after the number but before the line.
        For example: "1. " has no prefix and ". " as the suffix */
        String[] lines = text.split("\\r?\\n"); /* create an array that holds each individual line */
        text = ""; /* clear variable once it has been split into lines*/
        for(int i = 0; i < lines.length; i++) {
            text = text + prefix + (i + 1) + suffix + lines[i] + "\n";
        }
        return text;
    }
    public String RemoveEmptyLines(String text) {
        String[] lines = text.split("\\r?\\n"); /* create an array that holds each individual line */
        text = ""; /* clear variable once it has been split into lines*/
        for(int i = 0; i < lines.length; i++) {
            boolean copy = false;
            for(int j = 0; j < lines[i].length(); j++) {
                if(!(Character.isWhitespace(lines[i].charAt(j)))) {/* condition will be true if a character in lines[i] is NOT whitespace */
                    copy = true;
                    break;
                }
            }
            if(copy)
                text = text + lines[i] + "\n";
        }
        return text;
    }
    public String MergeText(String text, String text2) {
        String[] lines = text.split("\\r?\\n");
        String[] lines2 = text2.split("\\r?\\n");
        String new_text = "";
        for(int i = 0; i < lines.length; i++) {
            if(i < lines2.length) /* will prevent lines2 from going out of bounds if it is smaller than lines */
                new_text = new_text + lines[i] + lines2[i] + "\n";
            else
                new_text = new_text + lines[i] + "\n"; /* all elements of lines2 have been transferred */
        }
        if(lines2.length > lines.length) {
            for(int i = lines.length; i < lines2.length; i++) /* merge any remaining elements from lines2 */
                new_text = new_text + lines2[i] + "\n";
        }
        return new_text;
    }
    public String FindReplace(String text, String find, String replace) { /// just the basic algorithm for now. May need to be fixed.
        int instances = 0;
        while(text.indexOf(find) > -1) { /* runs until String find is no longer found */ /// no longer sure if this will work correctly, although testing was fine.
            text = text.substring(0, text.indexOf(find)) + replace + text.substring(text.indexOf(find) + find.length(), text.length());
            instances++;
        }
        return text;
    }
    /* removes all instances of 'argument' from 'text' */
    public String RemoveArgument(String argument, String text) {
        String new_text = "";
        ArrayList<Integer> locations = new ArrayList<Integer>(); /* will be used to store the indexes where argument is found */
        int from_index = 0;
        while(text.indexOf(argument, from_index) > -1) {
            int index = text.indexOf(argument, from_index);
            locations.add(index); /* store locations of all instances */
            from_index = index + argument.length(); /* increment from_index so it won't keep finding the same string over and over */
        }
        int j = 0, argument_length = argument.length();
        for(int i = 0; i < text.length(); i++) {
            if(j < locations.size() && i == locations.get(j)) { /* i has reached one of the instances */
                i = i + argument_length - 1; /* increment i to skip the instance */
                j++;
            }
            else
                new_text = new_text + text.charAt(i);
        }
        return new_text;
    }
    public String CommaSeparateValues(String text) {
        String new_text = "";
        for(int i = 0; i < text.length(); i++)
            if(text.charAt(i) == ' ')
                new_text = new_text + ",";
            else
                new_text = new_text + text.charAt(i);
        String new_text1 = "";
        for(int i = 0; i < new_text.length() - 1; i++) {
            if(!(new_text.charAt(i) == ',' && new_text.charAt(i + 1) == ',')) /* remove duplicate commas */
                new_text1 = new_text1 + new_text.charAt(i);
        }
        return new_text1 + new_text.charAt(new_text.length() - 1); /* ensures last char is not missed */
    }
    public String LineSeparateValues(String text, char separator) { /// mistake when 'separator' is at the beginning of a line
        String new_text = "", word = "";
        for(int i = 0; i < text.length(); i++) {
            if(text.charAt(i) != separator)
                word = word + text.charAt(i);
            else if(!word.equals("")) { /* this avoids empty line breaks when there is more than one separator in a row */
                new_text = new_text + word + "\n"; /* add 'word' and a newline */
                word = ""; /* reset word */
            }
        }
        return new_text + word; /* make sure to get the last word */
    }
    //public void LineAnalysis(String text) {
    //    String[] lines = text.split("\\r?\\n");
    //    for(int i = 0; i < lines.length; i++) {
    //        Println("Line " + (i + 1) + ": " + WordCount(lines[i]) + " word(s), " + CharCount(lines[i]) + " character(s), "
    //        + SentenceCount(lines[i]) + " sentence(s).");
    //    }
    //}
    /* puts each individual sentence on a separate line */
    public String SplitSentences(String text) {
        return "";
    }
    public void CompareLines(String text, int line_number1, int line_number2) {

    }
    /* generates a random number between 0 and 'upper_bound' inclusive */
    int GenerateRandomNumber(int upper_bound) { /// I'm pretty sure there's a java.util.Random library we can use
        //int random = (int) (Math.floor(Math.random() * (upper_bound + 1)));
        return (int) Math.floor(Math.random() * (upper_bound + 1));
    }
    /* removes all non-letter and non-numbers, leaves spaces */
    public String RemovePunctuation(String text) {
        String new_text = "";
        for(int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if(!((c > 32 && c < 48) || (c > 57 && c < 65) || (c > 90 && c < 97) || (c > 122 && c < 127)))
                new_text = new_text + c;
        }
        return new_text;
    }


    public String ForceUppercase(String text) {return text.toUpperCase();}
    public String ForceLowercase(String text) {return text.toLowerCase();}
    public void Print(String s) { System.out.print(s); }
    public void Println(String s) { System.out.println(s); }
}
