package itmo.labs.commands;

/**
 * Functional interface that represents an executable console command.
 *
 * <p>All concrete command classes implement this interface and provide
 * specific behaviour in their {@link #execute(String)} method.</p>
 *
 * @author Bykov Timur
 * @version 1.0
 */

import java.io.BufferedReader;
import java.io.IOException;

public interface Command {
    public void execute(String arg) throws IOException;

    public String getDescription();

    // Для add и update
    default void executeWithReader(String args, BufferedReader reader) throws IOException {
        execute(args);
    }
}
