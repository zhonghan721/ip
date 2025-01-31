package zean;

/**
 * The class which deals with interactions with the user.
 *
 * @author Zhong Han
 */
public class Ui {
    private static final String DIVIDER = "__________________________________";
    private static final String ERROR_DIVIDER = "**********************************";

    public Ui() {

    }

    /**
     * Prints the welcome message.
     *
     * @param name The name of the chatbot.
     */
    public void greet(String name) {
        printDivider();
        printLogo();
        System.out.println("Hi there! I'm " + name);
        System.out.println("How can I help you today?");
        printDivider();
    }

    /**
     * Prints the exit message.
     */
    public void exit() {
        printDivider();
        System.out.println("Bye. Have a nice day!");
        printDivider();
    }

    /**
     * Prints the normal horizontal divider.
     */
    public void printDivider() {
        System.out.println(Ui.DIVIDER);
    }

    private void printLogo() {
        String logo = "++      ++      ++\n"
                + "||      ||      ||\n"
                + "| +----+ |      ||\n"
                + "| +----+ |      ||\n"
                + "||      ||      ||\n"
                + "++      ++      ++\n";
        System.out.println(logo);
    }

    /**
     * Prints the error message with a horizontal error divider.
     *
     * @param msg The error message to be printed.
     */
    public void showError(String msg) {
        System.out.println(ERROR_DIVIDER);
        System.out.println(msg);
        System.out.println(ERROR_DIVIDER);
    }

    /**
     * Prints the input String in the console enclosed in two horizontal dividers.
     *
     * @param msg The message to be printed.
     */
    public void printOutput(String msg) {
        if (msg.isBlank()) {
            return;
        }
        this.printDivider();
        System.out.println(msg);
        this.printDivider();
    }
}
