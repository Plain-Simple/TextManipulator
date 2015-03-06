/* Plain+Simple Text Manipulator text analysis functions */

//TODO: rename everything to comply with naming standards!!

package plainsimple.textmanipulator;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class AnalyzeText {
    /* regex pattern to find words */
    private Pattern word_pattern = Pattern.compile("\\w+");
  public int charCount(String text) {
    return text.replace("\n", "").length();
  }
  public int wordCount(String text) {
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
    // ToDo: testing and look into hashmaps
  public void getObjectFrequencies(String text[], ArrayList<String> unique_words, ArrayList<Integer> frequencies) {
      /* make sure lists are empty before adding to them */
      unique_words.clear();
      frequencies.clear();
      for(int i = 0; i < text.length; i++) {
          /* get location of current word in list */
          int index = unique_words.indexOf(text[i]);
          if(index < 0) {
              /* add word to list */
              unique_words.add(text[i]);
              frequencies.add(0);
          } else {
              /* add one to correct index */
              frequencies.set(index, frequencies.get(index) + 1);
          }
      }
  }
}
