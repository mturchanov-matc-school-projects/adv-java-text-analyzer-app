package java112.labs1;

/**
 * The Lab three continues to
 * implement and practice TDD principles.
 */
public class LabThree {

    /**
     * Outputs first argument of the command line
     *
     * @param inputValue the first command line string/arg
     */
    public void run(String inputValue) {
        System.out.println("input: " + inputValue);
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Please enter one argument on the command line");
        } else {
            LabThree labThreeOutput = new LabThree();
            labThreeOutput.run(args[0]);
        }
    }
}
