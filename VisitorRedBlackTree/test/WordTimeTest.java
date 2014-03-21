import mymap.MyMap;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;


/**
 * WordTimeTest tests the performance of MyMap using a large set of words
 * These wordlists are random, and sorted lexicographically
 *
 * @author Adam Edgett edgett.a@husky.neu.edu
 * @version 3/21/14
 */
public class WordTimeTest {
    private StringByLex lexComparator;
    private MyMap<String, Integer> mymap;
    private int numString;
    private Iterator<String> it;

    private String containsFile = "contains_list.txt";
    private String[] fileList = {"random_order.txt",
                                 "lexicographically_ordered.txt"};
    private int[] numStrings = {2000, 4000, 8000, 16000};

    private FileWriter fileWriter;

    /**
     * Runs the tests
     * @param args the arguments
     */
    public static void main(String[] args) {
        WordTimeTest w = new WordTimeTest();
        w.execute();
    }

    /**
     * Constructor for WordTimeTest
     */
    public WordTimeTest() {
        this.lexComparator = new StringByLex();
    }

    /**
     * Executes the tests
     */
    public void execute() {
        try {
            this.fileWriter = new FileWriter("wordoutput.csv");
            fileWriter.append("file,comparator,num words,size,build (ms),");
            fileWriter.append("iterator(ms),iterate(ms),");
            fileWriter.append("num contained,contains(ms)\n");
        }
        catch (IOException e) {
            System.err.println("Error opening file");
        }
        for (String file : fileList) {
            for (int num : numStrings) {
                output(file + ",");
                output("StringByLex,");
                output(num + ",");
                this.numString = num;
                this.mymap = MyMap.empty(this.lexComparator);
                buildMap(file);
                createIterator();
                iterate();
                verifyMap();
                output("\n");
            }
        }
        try {
            fileWriter.flush();
            fileWriter.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Builds the map using the given file
     * @param filename the file to use
     */
    private void buildMap(String filename) {
        long timeBefore = System.currentTimeMillis();
        Iterator<String> sit = new StringIterator(filename);
        int count = 0;
        while (count < numString && sit.hasNext()) {
            count++;
            this.mymap = this.mymap.include(sit.next(), count);
        }
        output(this.mymap.size() + ",");
        output((System.currentTimeMillis() - timeBefore) + ",");
    }

    /**
     * Creates an iterator for the map
     */
    private void createIterator() {
        long timeBefore = System.currentTimeMillis();
        this.it = this.mymap.iterator();
        output((System.currentTimeMillis() - timeBefore) + ",");
    }

    /**
     * Iterates through the map
     */
    private void iterate() {
        long timeBefore = System.currentTimeMillis();
        while (it.hasNext()) {
            it.next();
        }
        output((System.currentTimeMillis() - timeBefore) + ",");
    }

    /**
     * Verifies the contents of the map
     */
    private void verifyMap() {
        long timeBefore = System.currentTimeMillis();
        int countContainsTrue = 0;
        StringIterator si = new StringIterator(containsFile);
        for (String s : si) {
            if (this.mymap.containsKey(s)) {
                countContainsTrue++;
            }
        }
        output(countContainsTrue + ",");
        output((System.currentTimeMillis() - timeBefore) + "");
    }

    /**
     * Outputs to a file
     * @param str the string to output
     */
    private void output(String str) {
        try {
            fileWriter.append(str);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
