package game;

import java.util.ArrayList;
import java.util.List;

// PrinterColumns is a helper class for Printer
// It is used to print the columns of the dungeon in clean columns
// This makes it easier to see the relative relationship of Rooms
// despite their occupancy which affectED their String length.

// This Class is almost 1-1 borrowed from candied_orange's
// response to Stack Overflow question
// "Is there an easy way to output two columns to the console in Java?"
// https://stackoverflow.com/questions/
//  699878/is-there-an-easy-way-to-output-two-columns-to-the-console-in-java
public class PrinterColumns {

    private List<List<String>> lines = new ArrayList<>();
    private List<Integer> maxLengths = new ArrayList<>();
    private int numColumns = -1;


    /**
     * @param rowStrings List<String>
     * @return PrinterColumns
     *
     * This method adds a List of Strings to the PrinterColumns.
     */
    public PrinterColumns addLine(final List<String> rowStrings) {

        if (numColumns == -1) {
            numColumns = rowStrings.size();
            for (int column = 0; column < numColumns; column++) {
                maxLengths.add(0);
            }
        }

        if (numColumns != rowStrings.size()) {
            throw new IllegalArgumentException();
        }

        for (int column = 0; column < numColumns; column++) {
            int length
                = Math.max(maxLengths.get(column),
                rowStrings.get(column).length());
            maxLengths.set(column, length);
        }

        lines.add(rowStrings);

        return this;
    }


    /**
     * This method prints.
     */
    public void print() {
        System.out.println(toString());
    }


    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     *
     * @return String
     *
     * This method turns things into padded Strings.
     */
    public String toString() {
        String result = "";
        for (List<String> line: lines) {
            for (int i = 0; i < numColumns; i++) {
                result += pad(line.get(i), maxLengths.get(i) + 1);
            }
            result += System.lineSeparator();
        }
        return result;
    }


    /**
     * @param word
     * @param newLength
     * @return String
     *
     * This method changes a String length by padding it with
     * white space to match the desired Column width.
     */
    private String pad(final String word, final int newLength) {
        String paddedWord = word;
        while (paddedWord.length() < newLength) {
            paddedWord += " ";
        }
        return paddedWord;
    }
}
