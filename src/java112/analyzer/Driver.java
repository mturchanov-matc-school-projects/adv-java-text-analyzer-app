package java112.analyzer;


import java.math.BigDecimal;

/**
 * This program reads a text-file,
 * analyzes its tokens,
 * creates summary-reports on it
 *
 * @author mturchanov
 * @version 3.0
 * trdt
 */
public class Driver {
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        FileAnalysis output = new FileAnalysis();
        output.analyze(args);

    }
}
