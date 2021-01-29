package duke.utils;

import duke.command.*;
import duke.exceptions.DukeException;
import duke.type.CommandType;
import duke.ui.Ui;

/**
 * This class extract and process the user input and produce the right command to be executed after parsing.
 */
public class Parser {
	private final String input;

	public Parser(String input) {
		this.input = input;
	}

	/**
	 * This method process all the user input and create respective command to be executed.
	 *
	 * @return the respective command to be executed.
	 */
	public final Command parse() {
		String instruction;
		String taskName;
		String date;
		Command command = new ErrorCommand();

		try {
			instruction = extractInstruction(input);
		} catch (DukeException e) {
			System.out.println(e.getMessage());
			return command;
		}

		try {
			taskName = extractTask(input, instruction);
		} catch (DukeException e) {
			System.out.println(e.getMessage());
			return command;
		}

		try {
			date = extractDate(input, instruction);
		} catch (DukeException e) {
			System.out.println(e.getMessage());
			return command;
		}

		switch (instruction) {
		case "bye":
			command = new ExitCommand(taskName, date);
			TaskStorage.writeToFiles();
			break;
		case "list":
			command = new ListCommand();
			break;
		case "done":
			command = new DoneCommand(taskName, date);
			break;
		case "todo": case "deadline": case "event":
			command = new AddCommand(instruction, taskName, date);
			break;
		case "delete":
			command = new DeleteCommand(taskName, date);
			break;
		default:
			break;
		}
		return command;
	}


	/**
	 * extract the command key in by user.
	 *
	 * @param input the input key in by user.
	 * @return String representation of the command word of the user input.
	 */
	static String extractInstruction(String input) throws DukeException {

		String instruction = input.trim().toLowerCase().split(" ")[0];
		if (input.replaceAll(" ", "").equals("")) {
			throw new DukeException(Ui.EMPTY_COMMAND);
		}

		if (CommandType.valueOfType(instruction) == null) {
			throw new DukeException(Ui.COMMAND_ERROR);
		}

		return instruction;

	}

	/**
	 * extracting task name from user input.
	 * @param input user input.
	 * @param command user command.
	 * @return the task name if there is one and return empty string if task name empty.
	 */
	static String extractTask(String input, String command) throws DukeException {
		String body = input.replaceAll(command, "").trim();
		switch (command) {
		case "todo":
			if (body.equals("")) {
				throw new DukeException(Ui.EMPTY_TASK);
			} else {
				return body;
			}
		case "done": case "delete":
			String hasLetter = body.replaceAll("[0-9]", "");
			if (hasLetter.length() > 0) {
				throw new DukeException(Ui.KEY_IN_NUMBER);
			} else {
				return body.replaceAll("[^0-9]", "");
			}
		case "deadline": case "event":
			if (body.equals("")) {
				throw new DukeException(Ui.EMPTY_TASK);
			} else {
				return body.split("/")[0];
			}
		default:
			if (body.length() > 0) {
				throw new DukeException(Ui.COMMAND_ERROR);
			}
			return "";
		}
	}

	/**
	 * extract the date of the task to be done.
	 * @param input user input.
	 * @param instruction user command.
	 * @return the task date in String and return empty if there is no date.
	 */
	static String extractDate(String input, String instruction) throws DukeException {
		String body = input.replaceAll(instruction, "").trim();
		String[] parts = body.split("/", 2);
		if (parts.length == 2) {
			String date = DateAndTime.converter(parts[1]);
			if (date.equals(Ui.WRONG_DATE_FORMAT)) {
				throw new DukeException(Ui.WRONG_DATE_FORMAT);
			}
			return date;
		} else {
			if (instruction.equals("deadline")
					|| instruction.equals("event")) {
				throw new DukeException(Ui.MISSING_DATE);
			}
			return "";
		}
	}


}
