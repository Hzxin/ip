import java.util.ArrayList;
import java.util.List;

/**
 * A class that store the task that user key in.
 */
public class Task {
    private static List<Task> taskList = new ArrayList<>();
    private String taskName;
    private static int capacity = 0;
    private int index;
    private String done;
    private String date;
    /**
     * Construct a task object with taskname attached and its index label in the taskList.
     * @param taskName name of the task.
     */

    Task(String taskName) {
        this.taskName = taskName;
        this.index = capacity + 1;
        this.done = " ";
        this.date = "";
        capacity++;
        add(this);
    }

    Task(String taskName, String done, boolean check) {
        this.taskName = taskName;
        this.index = capacity + 1;
        this.done = done;
        this.date = "";
        capacity++;
        add(this);
    }

    Task(String taskName, String date) {
        this.taskName = taskName;
        this.index = capacity + 1;
        this.done = " ";
        this.date = date;
        capacity++;
        add(this);
        if (DateAndTime.converter(date).equals("")) {
            DukeException.DATEFORMATEXCEPTION();
            this.date = date;
        } else {
            this.date = DateAndTime.converter(date);
        }
    }

    Task(String taskName, String date, String done) {
        this.taskName = taskName;
        this.index = capacity + 1;
        this.done = done;
        this.date = date;
        capacity++;
        add(this);
    }

    /**
     * Store the task key in by user in the taskList.
     *
     * @param t Task key in by user.
     */
    private static void add(Task t) {
        taskList.add(t);
    }

    /**
     * Mark a given task as done.
     *
     * @param i the index label of the Task.
     */
    public static final void done(int i) {
        try {
            Task t = taskList.get(i - 1);
            taskList.get(i - 1).done = "X";
            System.out.println(Format.UPPER + "Wah~ You done the task: "
                    + " " + t.toString() + Format.LOWER);
        } catch (IndexOutOfBoundsException e) {
            DukeException.taskErrorException();
        }
    }

    public static final void delete(int i) {
        try {
            Task t = taskList.get(i - 1);
            taskList.remove(i - 1);
            System.out.println(Format.UPPER + "Awww~ You've deleted the task: "
                    + " " + t.toString() + Format.LOWER);
            for (Task task : taskList) {
                if (task.index > i) {
                    task.index--;
                }
            }
        } catch (IndexOutOfBoundsException e) {
            DukeException.taskErrorException();
        }
    }

    /**
     * Get the name of the task.
     *
     * @return a String representation of the task name.
     */
    public String getTaskName() {
        return taskName;
    }

    /**
     * Get the taskList.
     *
     * @return the List of all tasks stored.
     */

    public static final List<Task> getTaskList() {
        return Task.taskList;
    }

    /**
     * get the isDone status of the task.
     *
     * @return a String representation of the isDone status (X for done).
     */
    public String isDone() {
        return done;
    }

    /**
     * get the index label of the task.
     *
     * @return the int representation of the index label.
     */
    public int getIndex() {
        return index;
    }

    public int getType() {
        return 0;
    }

    public String getDate() {
        return date;
    }

    /**
     * get the current capacity of the taskList.
     *
     * @return the int representation of the capacity of the taskList.
     */
    public static final int getCapacity() {
        return Task.capacity;
    }

    @Override
    public String toString() {
        return String.format("[%s] %d. %s", done, index, taskName);
    }
}