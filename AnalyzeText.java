/* Plain+Simple Text Manipulator text analysis functions */
public class AnalyzeText {
    public int CharCount(String text) {
        return text.replace("\n", "").length();
    }
    public int WordCount(String text) {
        return text.length()
                - text.replace(" ", "").replace("\n", "").length() + 1;
    }
    public int LineCount(String text) {
        return text.length() - text.replace("\n", "").length() + 1;
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
