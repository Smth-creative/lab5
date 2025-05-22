package itmo.labs.commands;

/**
 * Command that executes a sequence of commands from the specified script file.
 *
 * <p>The script is read line-by-line, exactly as if the user entered the commands
 * interactively.</p>
 *
 * @author Bykov Timur
 * @version 1.0
 */

import itmo.labs.console.CommandManager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ExecuteScriptCommand implements Command {
    private final CommandManager commandManager;
    private int scriptCount;

    public ExecuteScriptCommand(CommandManager commandManager) {
        this.commandManager = commandManager;
    }

    @Override
    public void execute(String args) {
        if (args == null || args.isBlank()) {
            throw new IllegalArgumentException("No file name");
        }

        String fileName = args.trim();

        File file = new File(fileName);
        if (!file.exists() || !file.canRead()) {
            throw new IllegalArgumentException("File can't be found or accessed: " + fileName);
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;

                String[] parts = line.split(" ", 2);
                String commandName = parts[0];
                String arg;

                if (parts.length > 1) {
                    arg = parts[1].trim();
                } else {
                    arg = "";
                }

                Command cmd = commandManager.getCommand(commandName);
                if (cmd == null) {
                    System.out.println("Unknown command " + commandName);
                    continue;
                }

                if (cmd instanceof ExecuteScriptCommand) {
                    scriptCount++;
                }

                if (scriptCount > 10) {
                    throw new RuntimeException("recursion detected");
                }

                if (cmd instanceof AddCommand || cmd instanceof UpdateCommand) {
                    cmd.executeWithReader(arg, reader);
                } else {
                    cmd.execute(arg);
                }
            }
        } catch (IOException e) {
            System.out.println("Error while reading script" + e.getMessage());
        }
    }

    @Override
    public String getDescription() {
        return "execute_script {file_path}: execute commands from given script";
    }

}
