import java.nio.file.Paths;
import java.util.ArrayList;
import java.nio.file.Path;
import java.nio.file.Files;

public class TaskList {
    ArrayList<Task> list = new ArrayList<>();

    public void addToList(Task task) {
        printLine();
        System.out.println("    The task hath been added");
        System.out.println("      " + task);
        list.add(task);
        if (list.size() == 1) {
            System.out.println("    thou now hath " + list.size() + " task to complete");
        } else {
            System.out.println("    thou now hath " + list.size() + " tasks to complete");
        }
        printLine();
        System.out.println();
    }

    private void printLine() {
        System.out.println("    ____________________________________________________________________________");
    }

     public void printList() {
        if (list.isEmpty()) {
            printLine();
            System.out.println("    No tasks to do? that's pretty goooood.");
            printLine();
        } else {
            printLine();
            System.out.println("    So here's the tasks in your list, you should proooobably do them");
            for (int i = 0; i < list.size(); i++) {
                System.out.println("    " + (i + 1) + ". " + list.get(i));
            }
            printLine();
        }
    }

    public void markTaskAsDone(int index) {
        printLine();
        Task task;
        try {
            task = list.get(index - 1);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("    Ain't no such task in the middle of these woods");
            printLine();
            return;
        }

        if (task.markAsDone()) {
            System.out.println("    Yes boss, marked the task as done.");
            System.out.println("     " + task.toString());
        }
        printLine();
    }

    public void unmarkTaskAsUndone(int index) {
        printLine();
        Task task;
        try {
            task = list.get(index - 1);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("    Ain't no such task in the middle of these woods");
            printLine();
            return;
        }

        if (task.unmarkAsUndone()) {
            System.out.println("    Interesting choice but I've marked the task as not done.");
            System.out.println("      " + task.toString());
        }
        printLine();
    }

    public void deleteTask(int index) {
        printLine();
        Task task;

        try {
            task = list.remove(index - 1);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("    Ain't no such task in the middle of these woods");
            printLine();
            return;
        }

        System.out.println("    Your bidding has been done, removed:");
        System.out.println("      " + task.toString());
        if (list.size() == 1) {
            System.out.println("    thou now hath " + list.size() + " task to complete");
        } else if (list.size() == 0 ) {
            System.out.println("    thou hath no tasks to be completed");
        } else {
            System.out.println("    thou now hath " + list.size() + " tasks to complete");
        }
        printLine();
    }

    private void writeToFile() {
        Path path = Paths.get("iP", "data");
        boolean directoryExists = Files.exists(path);


    }

    @Override
    public String toString() {
        return list.toString();
    }
}
