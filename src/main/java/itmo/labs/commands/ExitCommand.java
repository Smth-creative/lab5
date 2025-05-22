package itmo.labs.commands;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Scanner;

/**
 * Command that terminates the application without saving the collection.
 *
 * <p>Implements the {@link itmo.labs.commands.Command Command} interface.</p>
 *
 * @author Bykov Timur
 * @version 1.0
 */

public class ExitCommand implements Command {
    private final BufferedReader reader;
    private final Scanner scanner;

    public ExitCommand(BufferedReader reader, Scanner scanner) {
        this.reader = reader;
        this.scanner = scanner;
    }

    @Override
    public void execute(String arg) throws IOException {
        if (arg != null && !arg.isBlank()) {
            throw new IllegalArgumentException("clear doesn't use additional arguments");
        } else {
            System.out.println("Program is closed");
            scanner.close();
            reader.close();
            System.exit(0);
        }
    }

    @Override
    public String getDescription() {
        return "exit: finish program (without saving)";
    }
}