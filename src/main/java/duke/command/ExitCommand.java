package duke.command;

/**
 * Sub-class of command that represents and execute the "bye" instruction of user.
 */
public class ExitCommand extends Command {
	public ExitCommand(String task, String date) {
		super("bye", task, date, command -> handleBye(task, date));
	}

	/**
	 * handle bye command by returning true or false.
	 *
	 * @param task name of the user task.
	 * @param date date of the task that user key in.
	 * @return a boolean of whether to exit the program.
	 */
	private static boolean handleBye(String task, String date) {
		return task.equals("") && date.equals("");
	}

}
