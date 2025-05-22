package itmo.labs.commands;

/**
 * Command that outputs the last eleven commands entered by the user
 * (without their arguments).
 *
 * @author Bykov Timur
 * @version 1.0
 */

import java.util.Deque;

public class HistoryCommand implements Command {
    private final Deque<String> history;

    public HistoryCommand(Deque<String> history) {
        this.history = history;
    }

    @Override
    public void execute(String args) {
        if (args != null && !args.isBlank()) {
            throw new IllegalArgumentException("history doesn't use arguments");
        }

        if (history.isEmpty()) {
            System.out.println("History is empty");
            return;
        }

        System.out.println("Last commands:");
        history.forEach(System.out::println);
    }

    @Override
    public String getDescription() {
        return "history: show last commands (up to eleven)";
    }
}
