package duke.command;

import duke.ui.Ui;

<<<<<<< HEAD
/**
 * Sub-class of Command that represents and execute the "list" instruction of user.
 */
=======

>>>>>>> branch-A-CodingStandard
public class ListCommand extends Command{
	public ListCommand() {
		super("", "", "", command -> {
			Ui.LISTING();
			return false;
		});
	}
}
