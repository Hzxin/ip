package duke.command;

import duke.exceptions.DukeException;
import duke.task.Task;

/**
 * Sub-class of Command that represents and execute the done instruction of user.
 */
public class DoneCommand extends Command {

	/**
	 * Create a DoneCommand object with given task and date.
	 *
	 * @param task the user task.
	 * @param date date of the task.
	 */
	public DoneCommand(String task, String date) {
		super("done", task, date, command -> handleDone(task));
	}

	/**
	 * This method handle the done instruction and return false.
	 *
	 * @param task user task.
	 * @return boolean of false to indicate the program is not ending.
	 */
	private static Boolean handleDone(String task) {
		if (task.length() > 0) {
			try {
				int num = Integer.parseInt(task);
				Task.done(num);
			} catch (NumberFormatException e) {
				DukeException.NumberFormatException();
			}
		}
		return false;
	}


}
