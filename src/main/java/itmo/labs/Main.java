package itmo.labs;

/**
 * Application entry point. Bootstraps all managers, loads the collection from
 * file and starts the interactive command loop.
 *
 * @author Bykov Timur
 * @version 1.0
 */

import itmo.labs.console.CommandManager;
import itmo.labs.utils.CollectionManager;
import itmo.labs.utils.FileManager;

public class Main {
    public static void main(String[] args) {
        String filepath = System.getenv("FILE_PATH");

        if (filepath == null) {
            System.out.println("fix your enviromental variable");
            System.exit(1);
        }

        FileManager fileManager = new FileManager(filepath);
        CollectionManager collectionManager = new CollectionManager(fileManager);
        CommandManager commandManager = new CommandManager(collectionManager, fileManager);
        commandManager.run();
    }
}
