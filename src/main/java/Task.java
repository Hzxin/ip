public class Task {
    private static Task[] taskList = new Task[100];
    private String taskName;
    private static int capacity = 0;
    private int index;
    private String done;
    Task(String taskName) {
        this.taskName = taskName;
        this.index = capacity + 1;
        this.done = "";
        capacity++;
        add(this);
    }

    private static void add(Task t) {
        taskList[capacity - 1] = t;
    }


    public static final void done(int i) {
        if (taskList[i - 1] != null) {
            taskList[i - 1].done = "X";
            System.out.println(Format.UPPER + "Wah~ You done the task: "
                    + " " + taskList[i - 1].toString() + Format.LOWER);
        } else {
            System.out.println(Format.chatBox("Walao!No such task!"));
        }
    }

    public String getTaskName() {
        return taskName;
    }

    public static final Task[] getTaskList() {
        return Task.taskList;
    }

    public String isDone() {
        return done;
    }

    public int getIndex() {
        return index;
    }

    public static final int getCapacity() {
        return Task.capacity;
    }
    @Override
    public String toString() {
        return String.format("[%s] %d. %s", done, index, taskName);
    }
}
