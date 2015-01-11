/* Plain+Simple Textmanipulator text analysis functions */
package textfunctions;
public class AnalyzeText {
    public static int CharCount(String text) {
        int char_count = text.replace("\n", "").length();
        return char_count;
    }
    public static int WordCount(String text) {
        int word_count = text.length()
                - text.replace(" ", "").replace("\n", "").length() + 1;
        return word_count;
    }
    public static int LineCount(String text) {
        int line_count = text.length() - text.replace("\n", "").length() + 1; /// needs to be tested and possibly corrected
        return line_count;
    }
    public static int SentenceCount(String text) {
        int sentence_count = text.length()
                - text.replace(".", "").replace("?", "").replace("!", "")
                .length();
        if(sentence_count == 0)
            sentence_count++; /* it has to be at least one sentence, even though it may not be grammatically complete */
        return sentence_count;
    }

    public static void WordFrequency(String text) {

    }

    public static void CharFrequency(String text) {

    }
}
