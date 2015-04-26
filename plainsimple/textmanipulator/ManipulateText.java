/* Plain+Simple TextManipulator text manipulation functions */
package plainsimple.textmanipulator;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

class ManipulateText {
  /* adds prefix and suffix to each line */
  @SuppressWarnings("HardCodedStringLiteral")
  /* split into lines */
  public ArrayList<String[]> splitIntoLines(String text) { // todo: fix to recognize empty lines
      /* in this case objects are technically the line breaks */
      ArrayList<String> objects = new ArrayList<>();
      ArrayList<String> delimiters = new ArrayList<>();
      Pattern lines_pattern = Pattern.compile("[^\\r\\n]+");
      Matcher matcher = lines_pattern.matcher(text);
      int location = 0;
      while (matcher.find()) {
          delimiters.add(text.substring(location, matcher.start()));
          location = matcher.end();
          objects.add(matcher.group());
      }
      if(location <= text.length() - 1) /* get any remaining text */
          delimiters.add(text.substring(location));
      ArrayList<String[]> result = new ArrayList<>();
      result.add(delimiters.toArray(new String[delimiters.size()]));
      result.add(objects.toArray(new String[objects.size()]));
      return result;
      /*ArrayList<String> objects = new ArrayList<>();
      ArrayList<String> delimiters = new ArrayList<>();
      Pattern lines_pattern = Pattern.compile("[^\\r\\n]+");
      Matcher matcher = lines_pattern.matcher(text);
      int location = 0;
      while (matcher.find()) {
          delimiters.add(text.substring(location, matcher.start()));
          location = matcher.end();
          objects.add(matcher.group());
      }
      delimiters.add(text.substring(location));
      Println(delimiters.toString());
      Println(objects.toString());
      Println(objects.size() + " lines detected");
      ArrayList<String[]> result = new ArrayList<>();
      result.add(delimiters.toArray(new String[delimiters.size()]));
      result.add(objects.toArray(new String[objects.size()]));
      return result;*/
  }
    /* split into words */
    public ArrayList<String[]> splitIntoWords(String text) {
        ArrayList<String> objects = new ArrayList<>();
        ArrayList<String> delimiters = new ArrayList<>();
        Pattern words_pattern = Pattern.compile("\\w+");
        Matcher matcher = words_pattern.matcher(text);
        int location = 0;
        while (matcher.find()) {
            delimiters.add(text.substring(location, matcher.start()));
            location = matcher.end();
            objects.add(matcher.group());
        }
        if(location <= text.length() - 1) /* get any remaining text */
            delimiters.add(text.substring(location));
        ArrayList<String[]> result = new ArrayList<>();
        result.add(delimiters.toArray(new String[delimiters.size()]));
        result.add(objects.toArray(new String[objects.size()]));
        return result;
        /*ArrayList<String> objects = new ArrayList<>();
        ArrayList<String> delimiters = new ArrayList<>();
        Pattern lines_pattern = Pattern.compile("[\\w']+");
        Matcher matcher = lines_pattern.matcher(text);
        int location = 0;
        while (matcher.find()) {
            Println(matcher.start() + " - " + matcher.end() + " = " + matcher.group());
            delimiters.add(text.substring(location, matcher.start()));
            location = matcher.end();
            objects.add(matcher.group());
        }
        if(location < text.length())
            delimiters.add(text.substring(location));
        for(int i = 0; i < delimiters.size(); i++)
            Println("delimiters.get(" + i + ") = " + delimiters.get(i));
        Println(objects.toString());
        Println(objects.size() + " lines detected");
        ArrayList<String[]> result = new ArrayList<>();
        result.add(delimiters.toArray(new String[delimiters.size()]));
        result.add(objects.toArray(new String[objects.size()]));
        return result;*/
        //return text.split("\\W+"); /* splits at non-word characters */
    }
    /* split into chars */
    public ArrayList<String[]> splitIntoChars(String text) {
        String[] objects = new String[text.length()];
        String[] delimiters = new String[text.length()];
        for(int i = 0; i < text.length(); i++) {
            delimiters[i] = "";
            objects[i] = Character.toString(text.charAt(i));
        }
        ArrayList<String[]> result = new ArrayList<>();
        result.add(delimiters);
        result.add(objects);
        return result;
        //return text.split(".");
    }
    public ArrayList<String[]> splitBySeparator(String text, String separator) {
        // String[] split_text = text.split(separator);
        ArrayList<String> objects = new ArrayList<>();
        ArrayList<String> delimiters = new ArrayList<>();
            Pattern separator_pattern = Pattern.compile(separator);
            Matcher matcher = separator_pattern.matcher(text);
            int location = 0;
            while(matcher.find()) {
                delimiters.add(text.substring(location, matcher.start()));
                objects.add(separator);
                location = matcher.end();
            }
            if(location <= text.length() - 1)
                delimiters.add(text.substring(location));
            ArrayList<String[]> result = new ArrayList<>();
            result.add(delimiters.toArray(new String[delimiters.size()]));
            result.add(objects.toArray(new String[objects.size()]));
            return result;
    }
    /* puts each individual sentence on a separate line */
    //public ArrayList<String[]> splitIntoSentences(String text) { // todo: fix this function
    //    return text.split("(\\d+)");
    //}
    public ArrayList<String[]> getAsArray(String text) {
        ArrayList<String[]> result = new ArrayList<>();
        result.add(new String[] {""});
        result.add(new String[] {text});
        return result;
    }
  public String[] addPrefixSuffix(String[] text, String prefix, String suffix) {
    /* add each line to text with prefix and suffix */
    for(int i = 0; i < text.length; i++)
      text[i] = prefix + text[i] + suffix;
    return text;
  }
  @SuppressWarnings("HardCodedStringLiteral")
  public String[] removeDuplicates(String text[]) { // todo: test
    ArrayList<String> result = new ArrayList<>(Arrays.asList(text));
    for(int i = 0; i < result.size(); i++) {
        for(int j = 1; j < result.size(); j++) {
            if(result.get(j).equals(result.get(i)))
                result.remove(j);
        }
    }
    return result.toArray(new String[result.size()]);
  }
  @SuppressWarnings("HardCodedStringLiteral")
  public String[] removeContaining(String text[], String remove) {
    ArrayList<String> result = new ArrayList<>(Arrays.asList(text));
    for(int i = 0; i < result.size(); i++) {
      if(result.get(i).contains(remove))
          result.remove(i);
    }
    return result.toArray(new String[result.size()]);
  }
  @SuppressWarnings("HardCodedStringLiteral")
  public String[] scrambleObjects(String text[]) {
    String[] result = new String[text.length];
    ArrayList<String> objects_list = new ArrayList<>(Arrays.asList(text));
    int counter = 0;
    while(objects_list.size() > 0) { /* runs until all lines have been copied */
      int line_number = generateRandomNumber(objects_list.size() -
                                             1); /* generate random number within range of list */
      result[counter] = objects_list.get(line_number); /* copy corresponding element to text */
      objects_list.remove(
              line_number); /* remove the element from the list so it cannot be copied again */
      counter++;
    }
    return result;
  }
  @SuppressWarnings("HardCodedStringLiteral")
  public String[] sortObjectsAlphabetically(String text[]) {
    Arrays.sort(text);
    return text;
  }
  @SuppressWarnings("HardCodedStringLiteral")
  public String[] sortObjectsBySize(String text[]) {
    for(int i = 1; i < text.length; i++) { /* start at second element */
      int num = 1;
      String compare = text[i];
      while(num <= i && compare.length() < text[i - num].length()) {
        /* keeps looping while a smaller line
            exists behind the current element and num doesn't cause an arrayindexoutofbounds exception */
        text[i - num + 1] = text[i - num]; /* move larger element one to the right */
        text[i - num] = compare; /* move compare one to the left */
        num++;
      }
    }
    return text;
  }
  @SuppressWarnings("HardCodedStringLiteral")
  public String[] numberObjects(String text[], String prefix, String suffix) {
    for(int i = 0; i < text.length; i++) {
      text[i] = prefix + (i + 1) + suffix + text[i] + "\n";
    }
    return text;
  }
  @SuppressWarnings("HardCodedStringLiteral")
  /* merges merge with text */
  public String[] mergeText(String text[], String merge[]) {
    if(text.length >= merge.length) {
          for(int i = 0; i < merge.length; i++) {
              text[i] = text[i] + merge[i];
          }
        return text;
    } else { /* merge.length > text.length */
      String[] result = merge;
      for(int i = 0; i < text.length; i++) {
          result[i] = text[i] + merge[i];
      }
        return result;
    }
  }
  public String[] findReplace(String text[], String find, String replace) { /* use String.replaceAll(find, replace) */
      for(int i = 0; i < text.length; i++)
          text[i] = text[i].replaceAll(find, replace);
      return text;
  }
  /* removes all instances of 'argument' from 'text' */
  public String[] remove(String text[], String argument) {
      for(int i = 0; i < text.length; i++)
          text[i] = text[i].replaceAll(argument, "");
      return text;
  }
  //public String commaSeparateValues(String text) {
  //    return removePunctuation(removeExtraWhitespace(text)).replace(' ', ',');
  //}
  public String[] removeLineBreaks(String text[]) {
    for(int i = 0; i < text.length; i++)
        text[i] = text[i].replaceAll("\\n|\\r", "");
    return text;
  }
  /* duplicates specified object (line/word/char) a specified number of times */
  public String[] duplicateObject(String text[], int element_number, int num_repetitions) throws NumberFormatException {
    if(num_repetitions < 0)
        throw new NumberFormatException("Error: Number of repetitions must be a positive integer");
    ArrayList<String> result = new ArrayList<>();
    for(int i = 0; i < text.length; i++) {
        result.add(text[i]);
        if (i == element_number)
            for (int j = 0; j < num_repetitions; j++)
                result.add(text[element_number]);
    }
    return result.toArray(new String[result.size()]);
  }
    /* copies text to clipboard */
    public void copyTextToClipboard(String text[]) {
        String as_string = "";
        for(int i = 0; i < text.length; i++)
            as_string += text[i];
        StringSelection stringSelection = new StringSelection(as_string);
        Clipboard clpbrd = Toolkit.getDefaultToolkit().getSystemClipboard();
        try {
            clpbrd.setContents(stringSelection, null);
        } catch(IllegalStateException e) {
            System.out.println("Error: System clipboard is currently unavailable.");
        }
    }
    public ArrayList<Integer> find(String text[], String regex) throws PatternSyntaxException { // todo: improve
        ArrayList<Integer> locations = new ArrayList<>();
        try {
            Pattern expression_to_find = Pattern.compile(regex);
            for (int i = 0; i < text.length; i++) {
                Matcher matcher = expression_to_find.matcher(text[i]);
                while (matcher.find()) {
                    locations.add(matcher.start());
                }
            }
            return locations;
        }catch(PatternSyntaxException e) {
            throw e;
        }
    }
  /* generates a random number between 0 and 'upper_bound' inclusive */
  int generateRandomNumber(int
                           upper_bound) { /// I'm pretty sure there's a java.util.Random library we can use
    //int random = (int) (Math.floor(Math.random() * (upper_bound + 1)));
    return (int) Math.floor(Math.random() * (upper_bound + 1));
  }
  public String[] removeExtraWhitespace(String[] text) { // note: removes tabs and linebreaks
      for(int i = 0; i < text.length; i++)
          text[i] = text[i].replaceAll("\\s+", " ").trim();
      return text;
  }
  /* removes all whitespace */
  public String[] removeWhitespace(String[] text) {
      for(int i = 0; i < text.length; i++)
          text[i] = text[i].replaceAll("\\s", "");
      return text;
  }
  /* removes all non-letter and non-numbers, leaves spaces */
  public String[] removePunctuation(String text[]) {
      for(int i = 0; i < text.length; i++)
          text[i] = text[i].replaceAll("[^\\w\\s]", "");
      return text;
  }
  /* reverses chars of each textobject */
  public String[] reverse(String[] text) {
      for(int i = 0; i < text.length; i++)
          text[i] = new StringBuilder(text[i]).reverse().toString();
      return text;
  }
  public String[] forceUppercase(String text[]) {
    for(int i = 0; i < text.length; i++)
        text[i] = text[i].toUpperCase();
      return text;
  }
  public String[] forceLowercase(String text[]) {
    for(int i = 0; i < text.length; i++)
        text[i] = text[i].toLowerCase();
      return text;
  }
  public void Println(String s) {
      System.out.println(s);
  }
}
