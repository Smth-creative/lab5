package itmo.labs.console;

/**
 * Central registry that maps textual command names to their corresponding
 * implementations and delegates execution.
 *
 * <p>Acts as a facade between the interactive/script front-end and individual
 * command objects.</p>
 *
 * @author Bykov Timur
 * @version 1.0
 */

import itmo.labs.commands.*;
import itmo.labs.utils.CollectionManager;
import itmo.labs.utils.FileManager;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class CommandManager {
    private final Map<String, Command> commands = new HashMap<>();
    private final Deque<String> history = new ArrayDeque<>();
    private final CollectionManager collectionManager;
    private final FileManager fileManager;
    private final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    Scanner scanner = new Scanner(System.in);

    public CommandManager(CollectionManager cm, FileManager fm) {
        this.collectionManager = cm;
        this.fileManager = fm;

        commands.put("help", new HelpCommand(commands));
        commands.put("info", new InfoCommand(collectionManager));
        commands.put("show", new ShowCommand(collectionManager));
        commands.put("add", new AddCommand(collectionManager, reader));
        commands.put("update", new UpdateCommand(collectionManager, reader));
        commands.put("remove_by_id", new RemoveByIDCommand(collectionManager));
        commands.put("clear", new ClearCommand(collectionManager));
        commands.put("save", new SaveCommand(collectionManager, fileManager));
        commands.put("execute_script", new ExecuteScriptCommand(this));
        commands.put("exit", new ExitCommand(reader, scanner));
        commands.put("remove_greater", new RemoveGreaterCommand(collectionManager));
        commands.put("remove_lower", new RemoveLowerCommand(collectionManager));
        commands.put("history", new HistoryCommand(history));
        commands.put("min_by_name", new MinByNameCommand(collectionManager));
        commands.put("filter_starts_with_name", new FilterStartsWithNameCommand(collectionManager));
        commands.put("print_unique_price", new PrintUniquePriceCommand(collectionManager));

    }

    public void run() {
        System.out.println("For help use: help");
        while (true) {
            System.out.print("> ");
            if (!scanner.hasNextLine()) break;

            String line = scanner.nextLine().trim();

            String[] parts = line.split(" ", 2);
            String name = parts[0];
            String arg;
            if (parts.length > 1) {
                arg = parts[1].trim();
            } else {
                arg = "";
            }

            Command cmd = commands.get(name);
            if (cmd == null) {
                System.out.println("Unknown command: " + name);
                continue;
            }

            try {
                cmd.execute(arg);
            } catch (Exception e) {
                System.out.println("Error while executing command: " + name);
                System.out.println(e.toString());
            }

            // Добавим в историю
            history.add(name);
            if (history.size() > 11) {
                history.removeFirst();
            }

        }
        scanner.close();
    }

    public Command getCommand(String name) {
        return commands.get(name);
    }

}
