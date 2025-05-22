package itmo.labs.commands;

/**
 * Command that prints a help message listing every available command with a
 * brief description.
 *
 * @author Bykov Timur
 * @version 1.0
 */

import java.util.Map;

public class HelpCommand implements Command {
    private final Map<String, Command> commands;

    public HelpCommand(Map<String, Command> commands) {
        this.commands = commands;
    }

    @Override
    public void execute(String args) {
        if (args == null || args.isBlank()) {
            System.out.println("All commands:");
            for (Map.Entry<String, Command> entry : commands.entrySet()) {
                System.out.println(entry.getValue().getDescription());
            }
        } else {
            String[] requested = args.trim().split("\\s+");
            for (String name : requested) {
                Command cmd = commands.get(name);
                if (cmd != null) {
                    System.out.println(cmd.getDescription());
                } else {
                    throw new IllegalArgumentException("Command '" + name + "' not found");
                }
            }
        }
    }

    @Override
    public String getDescription() {
        return "help: get information about available commands";
    }
}

