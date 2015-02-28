/* Plain+Simple TextManipulator text manipulation functions */
package plainsimple.textmanipulator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.regex.Pattern;

class ManipulateText {
  /* adds prefix and suffix to each line */
  @SuppressWarnings("HardCodedStringLiteral")
  /* create an array that holds each individual line */
  public String[] splitIntoLines(String text) {
      return text.split("\\r?\\n");
  }
  public String addPrefixSuffix(String[] text, String prefix, String suffix) {
      String result = "";
    /* add each line to text with prefix and suffix */
    for(int i = 0; i < text.length; i++)
      result += prefix + text[i] + suffix + "\n";
    return result;
  }
  @SuppressWarnings("HardCodedStringLiteral")
  public String removeDuplicateLines(String text[]) {
    ArrayList<Integer> duplicates = new ArrayList<>();
      String result = "";
    for(int i = 0; i < text.length; i++) {
      for(int j = i + 1; j < text.length;
          j++) { /* check to see if any of the later elements match */
        if(text[j].equals(text[i])) /* duplicate found */
          duplicates.add(j); /* add element position to list */
      }
    }
    for(int i = 0; i < text.length; i++) {
      boolean copy_element = true; /* true if element is not a duplicate */
      for(int j = 0; j < duplicates.size(); j++) {
        if(i == duplicates.get(j))
          copy_element = false;
      }
      if(copy_element) {
          result += text[i];
      }
    }
    return result;
  }
  @SuppressWarnings("HardCodedStringLiteral")
  public String removeLinesContaining(String text[], String remove) {
      String result = "";
    for(int i = 0; i < text.length; i++) { /* for each line... */
      if(text[i].indexOf(remove) < 0)  /* could not find String remove in line */
        result += text[i] + "\n";
    }
    return result;
  }
  @SuppressWarnings("HardCodedStringLiteral")
  public String scrambleLines(String text[]) {
      String result = "";
    ArrayList<String> lines_list = new ArrayList<>(Arrays.asList(text));
    while(lines_list.size() > 0) { /* runs until all lines have been copied */
      int line_number = generateRandomNumber(lines_list.size() -
                                             1); /* generate random number within range of list */
      result += lines_list.get(line_number) +
             "\n"; /* copy corresponding element to text */
      lines_list.remove(
        line_number); /* remove the element from the list so it cannot be copied again */
    }
    return result;
  }
  @SuppressWarnings("HardCodedStringLiteral")
  public String sortLinesAlphabetically(String text[]) {
    String result = "";
    Arrays.sort(text);
    for(int i = 0; i < text.length; i++)
      result += text[i] + "\n";
    return result;
  }
  @SuppressWarnings("HardCodedStringLiteral")
  public String sortLinesBySize(String text[]) {
    String result = "";
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
    for(int i = 0; i < text.length; i++) {
      result += text[i] + "\n";
    }
    return result;
  }
  @SuppressWarnings("HardCodedStringLiteral")
  public String numberLines(String text[], String prefix, String suffix) {
    String result = "";
    for(int i = 0; i < text.length; i++) {
      result += prefix + (i + 1) + suffix + text[i] + "\n";
    }
    return result;
  }
  @SuppressWarnings("HardCodedStringLiteral")
  public String removeEmptytext(String text[]) {
    String result = "";
    for(int i = 0; i < text.length; i++) {
      boolean copy = false;
      for(int j = 0; j < text[i].length(); j++) {
        if(!(Character.isWhitespace(text[i].charAt(
                                      j)))) {/* condition will be true if a character in text[i] is NOT whitespace */
          copy = true;
          break;
        }
      }
      if(copy)
        result += text[i] + "\n";
    }
    return result;
  }
  public String mergeText(String text[], String text2) {
    String[] merge_lines = text2.split("\\r?\\n");
    String result = "";
    for(int i = 0; i < text.length; i++) {
      if(i < merge_lines.length) { /* will prevent merge_lines from going out of bounds if it is smaller than text */
        result += text[i] + merge_lines[i] + "\n";
      } else {
        result  += text[i] +
                   "\n";  /* all elements of lines2 have been transferred */
      }
    }
    if(merge_lines.length > text.length) {
      for(int i = text.length; i < merge_lines.length;
          i++) { /* merge any remaining elements from lines2 */
        result += merge_lines[i] + "\n";
      }
    }
    return result;
  }
  public String findReplace(String text, String find,
                            String replace) { /// just the basic algorithm for now. May need to be fixed.
    int instances = 0;
    while(text.indexOf(find) >
          -1) { /* runs until String find is no longer found */ /// no longer sure if this will work correctly, although testing was fine.
      text = text.substring(0,
                            text.indexOf(find)) + replace + text.substring(text.indexOf(
                                  find) + find.length(), text.length());
      instances++;
    }
    return text;
  }
  /* removes all instances of 'argument' from 'text' */
  public String removeArgument(String text, String argument) {
    String result = text;
      int arg_length = argument.length();
      int index = result.indexOf(argument);
      while(index > -1) {
          result = result.substring(0, index) + result.substring(index + arg_length);
          index = result.indexOf(argument);
      }
    return result;
  }
  public String commaSeparateValues(String text) {
      return removePunctuation(removeExtraWhitespace(text)).replace(' ', ',');
  }
  public String lineSeparateValues(String text,
                                   char separator) { /// mistake when 'separator' is at the beginning of a line
    String new_text = "", word = "";
    for(int i = 0; i < text.length(); i++) {
      if(text.charAt(i) != separator) {
        word = word + text.charAt(i);
      } else if(
        !word.equals("")) { /* this avoids empty line breaks when there is more than one separator in a row */
        new_text = new_text + word + "\n"; /* add 'word' and a newline */
        word = ""; /* reset word */
      }
    }
    return new_text + word; /* make sure to get the last word */
  }
  /* puts each individual sentence on a separate line */
  public String splitSentences(String text) {
      return "";
  }
  public void compareLines(String text, int line_number1, int line_number2) {
  }
  /* generates a random number between 0 and 'upper_bound' inclusive */
  int generateRandomNumber(int
                           upper_bound) { /// I'm pretty sure there's a java.util.Random library we can use
    //int random = (int) (Math.floor(Math.random() * (upper_bound + 1)));
    return (int) Math.floor(Math.random() * (upper_bound + 1));
  }
  public String removeExtraWhitespace(String
                                      text) { // note: removes and tabs or linebreaks
    return text.replaceAll("\\s+", " ").trim();
  }
  /* removes all non-letter and non-numbers, leaves spaces */
  public String removePunctuation(String text) {
    String new_text = "";
    for(int i = 0; i < text.length(); i++) {
      char c = text.charAt(i);
      if(!((c > 32 && c < 48) || (c > 57 && c < 65) || (c > 90 && c < 97) || (c > 122
           && c < 127))) {
        new_text = new_text + c;
      }
    }
    return new_text;
  }


  public String forceUppercase(String text) {
    return text.toUpperCase();
  }
  public String forceLowercase(String text) {
    return text.toLowerCase();
  }
  public void Print(String s) {
    System.out.print(s);
  }
  public void Println(String s) {
    System.out.println(s);
  }
}
