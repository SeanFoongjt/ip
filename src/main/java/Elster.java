import com.sun.source.tree.CaseTree;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Elster {
    public static void main(String[] args) {
        boolean byeSentinel = true;
        String input;
        TaskList taskList = new TaskList();
        ArrayList<String> list = new ArrayList<>();
        Scanner myScanner = new Scanner(System.in);

        String logo = "___________.__            __\n" +
                    "\\_   _____/|  |   _______/  |_  ___________\n" +
                    " |    __)_ |  |  /  ___/\\   __\\/ __ \\_  __ \\\n" +
                    " |        \\|  |__\\___ \\  |  | \\  ___/|  | \\/\n" +
                    "/_______  /|____/____  > |__|  \\___  >__|\n" +
                    "        \\/           \\/            \\/";
        System.out.println(logo);
        printLine();

        System.out.println("    Hello, \"greetings\" from your friendly neighbourhood chatbot Elster..");
        System.out.println("    How can I help you today :|");
        printLine();

        while (byeSentinel) {
            input = myScanner.nextLine().strip();
            if (input.equals("bye")) {
                byeSentinel = false;

            } else if (input.equals("list")) {
                taskList.printList();

            } else if (input.startsWith("deadline")) {
                DeadlineTask task = DeadlineTask.of(input);
                if (!(task == null)) {
                    taskList.addToList(task);
                }

            } else if (input.startsWith("event")) {
                EventTask task = EventTask.of(input);
                if (!(task == null)) {
                    taskList.addToList(task);
                }

            } else if (input.startsWith("delete")) {
                taskList.deleteTask(Integer.parseInt(input.substring(7).strip()));

            } else if (input.startsWith("todo")) {
                ToDoTask task = ToDoTask.of(input);
                if (!(task == null)) {
                    taskList.addToList(task);
                }

                try {
                    taskList.writeToFile();
                } catch (IOException e) {
                    System.out.println(e);
                }

            } else if (input.startsWith("mark")) {
                taskList.markTaskAsDone(Integer.parseInt(input.substring(5,6)));

            } else if (input.startsWith("unmark")) {
                taskList.unmarkTaskAsUndone(Integer.parseInt(input.substring(7,8)));

            } else {
                printLine();
                System.out.println("    Could you, like, write a sensible command please?");
                printLine();
            }
        }

        printLine();
        System.out.println("    See you next time.");
        printLine();
    }

    static private void printLine() {
        System.out.println("    ____________________________________________________________________________");
    }


}
