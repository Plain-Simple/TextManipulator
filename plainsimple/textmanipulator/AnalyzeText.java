/* Plain+Simple Text Manipulator text analysis functions */

//TODO: rename everything to comply with naming standards!!

package plainsimple.textmanipulator;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class AnalyzeText {
    /* regex pattern to find words */
    private Pattern word_pattern = Pattern.compile("\\w+");
  public int CharCount(String text) {
    return text.replace("\n", "").length();
  }
  public int WordCount(String text) {
      Matcher matcher = word_pattern.matcher(text);
      int instances = 0;
      while(matcher.find()) {
         instances++;
      }
      return instances;
  }
  public int lineCount(String text) {
    return text.length() - text.replace("\n", "").length() + 1;
  }
  public int SentenceCount(String text) {
    int sentence_count = text.length()
                         - text.replace(".", "").replace("?", "").replace("!", "")
                         .length();
    if(sentence_count == 0) {
      sentence_count++;  /* it has to be at least one sentence, even though it may not be grammatically complete */
    }
    return sentence_count;
  }

  public void WordFrequency(String text) { // ToDo: remains to be tested
      String[] words = text.split("\\w+");
      ArrayList<String> unique_words = new ArrayList<>();
      ArrayList<Integer> frequencies = new ArrayList<>();
      for(int i = 0; i < words.length; i++) {
          /* get location of current word in list */
          int index = unique_words.indexOf(words[i]);
          if(index < 0) {
              /* add word to list */
              unique_words.add(words[i]);
              frequencies.add(0);
          } else {
              /* add one to correct index */
              frequencies.set(index, frequencies.get(index) + 1);
          }
      }
  }

  public void CharFrequency(String text) {
      ArrayList<Character> unique_chars = new ArrayList<>();
      ArrayList<Integer> frequencies = new ArrayList<>();
      for(int i = 0; i < text.length(); i++) {
          /* get location of current word in list */
          int index = unique_chars.indexOf(text.charAt(i));
          if(index < 0) {
              /* add char to list */
              unique_chars.add(text.charAt(i));
              frequencies.add(0);
          } else {
              /* add one to correct index */
              frequencies.set(index, frequencies.get(index) + 1);
          }
      }
  }
}
