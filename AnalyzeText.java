/* Plain+Simple Textmanipulator text analysis functions */
public class AnalyzeText {
    public int CharCount(String text) {
        int char_count = text.replace("\n", "").length();
        return char_count;
    }
    public int WordCount(String text) {
        int word_count = text.length()
                - text.replace(" ", "").replace("\n", "").length() + 1;
        return word_count;
    }
    public int LineCount(String text) {
        int line_count = text.length() - text.replace("\n", "").length() + 1; /// needs to be tested and possibly corrected
        return line_count;
    }
    public int SentenceCount(String text) {
        int sentence_count = text.length()
                - text.replace(".", "").replace("?", "").replace("!", "")
                .length();
        if(sentence_count == 0)
            sentence_count++; /* it has to be at least one sentence, even though it may not be grammatically complete */
        return sentence_count;
    }

    public void WordFrequency(String text) {

    }

    public void CharFrequency(String text) {

    }
}
