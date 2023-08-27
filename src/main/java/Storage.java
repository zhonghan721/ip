import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The class that provides the storage for the tasks.
 *
 * @author Zhong Han
 */
public class Storage {
    private ArrayList<Task> tasks;
    private int count;

    /**
     * Constructor for storage.
     * Creates the necessary directory and file if not present.
     * Reads the present content in the file into the ArrayList.
     */
    public Storage() throws FileNotFoundException, IOException {
        this.tasks = new ArrayList<>();
        this.count = 0;

        File dir = new File("./data");
        if (!dir.exists()) {
            if (!dir.mkdir()) {
                throw new DukeException("\t OOPS! The file cannot be created.");
            }
        }
        File f = new File("./data/zean.txt");
        if (!f.exists()) {
            f.createNewFile();
            System.out.println("\tA new folder and file to store your tasks has been created.");
        }
        Scanner sc = new Scanner(f);
        while (sc.hasNext()) {
            this.count += Parser.parseToTask(this.tasks, sc.nextLine());
        }
        sc.close();
    }

    /**
     * Adds a todo task to the array list.
     *
     * @param description The description of the todo task.
     */
    public void add(String description) {
        Todo task = new Todo(description);
        this.tasks.add(task);
        this.count++;
        printAddTask(task);
        addToDisk(task);
    }

    /**
     * Adds a deadline task to the array list.
     *
     * @param description The description of the deadline task.
     * @param by The due date of the deadline task.
     */
    public void add(String description, String by) {
        Deadline task = new Deadline(description, by);
        this.tasks.add(task);
        this.count++;
        printAddTask(task);
        addToDisk(task);
    }

    /**
     * Adds an event task to the array list.
     *
     * @param description The description of the event task.
     * @param from The start date/time of the event task.
     * @param to The end date/time of the event task.
     */
    public void add(String description, String from, String to) {
        Event task = new Event(description, from, to);
        this.tasks.add(task);
        this.count++;
        printAddTask(task);
        addToDisk(task);
    }

    private void printAddTask(Task task) {
        System.out.println("\tGot it. I've added this task:\n\t  " + task);
        this.printNumOfTasks();
    }

    /**
     * Prints the list of tasks that the storage holds.
     */
    public void list() {
        if (this.count == 0) {
            System.out.println("\tThere are currently no tasks in your list:");
        } else {
            System.out.println("\tAs requested, here are the tasks in your list:");
            for (int i = 0; i < this.count; i++) {
                System.out.printf("\t%d.%s\n", i + 1, this.tasks.get(i));
            }
        }
    }

    /**
     * Marks the task corresponding to the index as done.
     *
     * @param index The index of the task seen by the user, which starts from 1.
     * @throws DukeException An exception related to the chatbot.
     */
    public void markTaskDone(int index) throws DukeException {
        if (index > this.count || index <= 0) {
            throw new DukeException("\tHmm, this task does not exist :|");
        }
        System.out.println("\tNice! I've marked this task as done:");
        this.tasks.get(index - 1).markTaskDone();
        rewriteToDisk();
    }

    /**
     * Marks the task corresponding to the index as not done.
     *
     * @param index The index of the task seen by the user, which starts from 1.
     * @throws DukeException An exception related to the chatbot.
     */
    public void markTaskNotDone(int index) {
        if (index > this.count || index <= 0) {
            throw new DukeException("\tHmm, this task does not exist :|");
        }
        System.out.println("\tSure, I've marked this task as not done yet:");
        this.tasks.get(index - 1).markTaskNotDone();
        rewriteToDisk();
    }

    private void printNumOfTasks() {
        if (this.count < 2) {
            System.out.printf("\tNow you have %d task in the list.\n", this.count);
        } else {
            System.out.printf("\tNow you have %d tasks in the list.\n", this.count);
        }
    }

    /**
     * Deletes the task corresponding to the index.
     *
     * @param index The index of the task seen by the user, which starts from 1.
     * @throws DukeException An exception related to the chatbot.
     */
    public void deleteTask(int index) throws DukeException {
        if (index < 1 || index > this.count) {
            throw new DukeException("\tHmm, this task does not exist :|");
        }
        this.tasks.remove(index - 1);
        this.count--;
        System.out.println("\tNoted. I've removed this task.");
        printNumOfTasks();
        rewriteToDisk();
    }

    public void addToDisk(Task task) {
        try {
            FileWriter fw = new FileWriter("./data/zean.txt", true);
            fw.write(task.toStringForFile() + System.lineSeparator());
            fw.close();
        } catch (IOException e) {
            throw new DukeException("\tOOPS! The file is not available!");
        }
    }

    public void rewriteToDisk() {
        try {
            FileWriter fw = new FileWriter("./data/zean.txt");
            for (int i = 0; i < this.count; i++) {
                fw.write(this.tasks.get(i).toStringForFile() + System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            throw new DukeException("\tOOPS! The file is not available!");
        }
    }
}
