package seedu.address.commons.util;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Arrays;

/**
 * Helper functions for handling strings.
 */
public class StringUtil {

    /**
     * Returns true if the {@code sentence} contains the {@code word}.
     *   Ignores case, but a full word match is required.
     *   <br>examples:<pre>
     *       containsWordIgnoreCase("ABc def", "abc") == true
     *       containsWordIgnoreCase("ABc def", "DEF") == true
     *       containsWordIgnoreCase("ABc def", "AB") == false //not a full word match
     *       </pre>
     * @param sentence cannot be null
     * @param word cannot be null, cannot be empty, must be a single word
     */
    public static boolean containsWordIgnoreCase(String sentence, String word) {
        requireNonNull(sentence);
        requireNonNull(word);

        String preppedWord = word.trim();
        checkArgument(!preppedWord.isEmpty(), "Word parameter cannot be empty");
        checkArgument(preppedWord.split("\\s+").length == 1, "Word parameter should be a single word");

        String preppedSentence = sentence;
        String[] wordsInPreppedSentence = preppedSentence.split("\\s+");

        return Arrays.stream(wordsInPreppedSentence)
                .anyMatch(preppedWord::equalsIgnoreCase);
    }

    /**
     * Returns true if the {@code sentence} contains the {@code word}.
     *   Ignores case, and a full match is not required.
     *   <br>examples:<pre>
     *       containsSubstringIgnoreCase("ABc def", "abc") == true
     *       containsSubstringIgnoreCase("ABc def", "DEF") == true
     *       containsSubstringIgnoreCase("ABc def", "AB") == true
     *       </pre>
     * @param sentence cannot be null
     * @param subString cannot be null, cannot be empty
     */
    public static boolean containsSubstringIgnoreCase(String sentence, String subString) {
        requireNonNull(sentence);
        requireNonNull(subString);

        String preppedString = subString.trim();
        checkArgument(!preppedString.isEmpty(), "Substring parameter cannot be empty");

        return sentence.toLowerCase().contains(preppedString.toLowerCase());
    }

    /**
     * Returns true if the {@code sentence} is equal to the {@code word}.
     *  Ignores case.
     *  <br>examples:<pre>
     *      equalsIgnoreCase("ABc def", "abc def") == true
     *      equalsIgnoreCase("ABc def", "DEF") == false
     *      equalsIgnoreCase("ABc def", "ABc de") == false
     *      </pre>
     * @param sentence cannot be null
     * @param word cannot be null
     * @return true if the {@code sentence} is equal to the {@code word}
     */
    public static boolean equalsIgnoreCase(String sentence, String word) {
        requireNonNull(sentence);
        requireNonNull(word);

        String preppedString = word.trim();
        checkArgument(!preppedString.isEmpty(), "Word parameter cannot be empty");

        return sentence.toLowerCase().equals(preppedString.toLowerCase());
    }

    /**
     * Returns a detailed message of the t, including the stack trace.
     */
    public static String getDetails(Throwable t) {
        requireNonNull(t);
        StringWriter sw = new StringWriter();
        t.printStackTrace(new PrintWriter(sw));
        return t.getMessage() + "\n" + sw.toString();
    }

    /**
     * Returns true if {@code s} represents a non-zero unsigned integer
     * e.g. 1, 2, 3, ..., {@code Integer.MAX_VALUE} <br>
     * Will return false for any other non-null string input
     * e.g. empty string, "-1", "0", "+1", and " 2 " (untrimmed), "3 0" (contains whitespace), "1 a" (contains letters)
     * @throws NullPointerException if {@code s} is null.
     */
    public static boolean isNonZeroUnsignedInteger(String s) {
        requireNonNull(s);

        try {
            int value = Integer.parseInt(s);
            return value > 0 && !s.startsWith("+"); // "+1" is successfully parsed by Integer#parseInt(String)
        } catch (NumberFormatException nfe) {
            return false;
        }
    }

    /**
     * Returns true if {@code s} represents a non-negative unsigned double
     * e.g. 0.0, 1.0, 2.0, 3.0, ..., {@code Double.MAX_VALUE} <br>
     * Will return false for any other non-null string input
     * e.g. empty string, "-1.0", "+1.0", and " 2.0 " (untrimmed),
     * "3.0 0.0" (contains whitespace), "1.0 a" (contains letters)
     * @throws NullPointerException if {@code s} is null.
     */
    public static boolean isNonNegativeUnsignedDouble(String s) {
        requireNonNull(s);

        try {
            double value = Double.parseDouble(s);
            return value >= 0 && !s.startsWith("+"); // "+1.0" is successfully parsed by Double#parseDouble(String)
        } catch (NumberFormatException nfe) {
            return false;
        }
    }
}
