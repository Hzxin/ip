package Duke.driver;

import java.util.Scanner;
import Duke.ui.Ui;
import Duke.utils.TaskStorage;
import Duke.command.Command;
import Duke.utils.Parser;


/**
 * Driver class of Duke that handles the command key in by user and respond.
 */
public class DukeDriver {

    /**
     * Take in user input and execute the Duke program.
     *
     */
    public static void executeDuke() {
        Scanner sc = new Scanner(System.in);
        System.out.println(Ui.LOGO);
        System.out.println(Ui.GREETING);

        System.out.println(Ui.LOADFILE);

        Ui.SLEEP();

        TaskStorage.loadFiles();

        while(sc.hasNext()) {
            String message = sc.nextLine();
            boolean bye = inputHandler(message);
            if (bye) {
                break;
            }
        }
        System.out.println(Ui.FAREWELL);
    }


    /**
     * The main handler of all the command.
     *
     * @param message String representation of the message key in by user.
     * @return a boolean to whether to exit the program.
     */
    private static final boolean inputHandler(String message) {
        Command command = new Parser(message).parse();
        Boolean end = command.execute();
        return end;
    }




}
