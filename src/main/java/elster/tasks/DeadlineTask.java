package elster.tasks;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Deadline tasks are tasks that have a deadline on top of the base task functionalities
 */
public class DeadlineTask extends Task {
    private final LocalDateTime deadline;
    private DeadlineTask(String description, LocalDateTime deadline) {
        super(description);
        this.deadline = deadline;
    }

    /**
     * Factory method for a deadline task.
     * Parses input string to create a deadline task with a description and a deadline.
     *
     * @param input Input from terminal to be parsed.
     * @return Created deadline task.
     */
    public static DeadlineTask of(String input) {
        String[] splitInput = input.split("\\s+");
        LocalDateTime deadline;

        if (input.strip().equals("deadline")) {
            printLine();
            System.out.println("    Elster begs of you to have details for your task");
            printLine();
            return null;

        } else if (!input.contains("/by")) {
            printLine();
            System.out.println("    Elster begs of you to have a yknow, deadline with /by");
            printLine();
            return null;

        }

        try {
            String deadlineStr = input.substring(input.indexOf("/by") + 4).strip();

            if (deadlineStr.length() == 10) {
                deadline = LocalDate.parse(deadlineStr).atTime(23, 59);

            } else {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                deadline = LocalDateTime.parse(deadlineStr, formatter);
            }

        } catch (Exception e) {
            printLine();
            System.out.println("    for /by, Elster requires a yyyy-mm-dd or yyyy-mm-dd HH:mm "
                    + "format please and thanks");
            printLine();
            return null;
        }

        return new DeadlineTask(
                input.substring(8, input.indexOf("/by")).strip(),
                deadline
        );
    }

    @Override
    public String toString() {
        if (this.status) {
            return "[D][X] " + this.description + " (by: " + deadline + ")";
        } else {
            return "[D][ ] " + this.description + " (by: " + deadline + ")";
        }
    }

    /**
     * Returns a string representation of the deadline task suitable for writing to the save file.
     *
     * @return String representation of deadline formatted for file writing.
     */
    @Override
    public String toFileString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        if (this.status) {
            return "D | 1 | " + this.description + " | " + this.deadline.format(formatter);
        } else {
            return "D | 0 | " + this.description + " | " + this.deadline.format(formatter);
        }
    }
}
