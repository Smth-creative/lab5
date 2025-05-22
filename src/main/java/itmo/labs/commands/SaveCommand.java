package itmo.labs.commands;

/**
 * Command that writes the current state of the collection to the XML file
 * specified by the environment variable.
 *
 * @author Bykov Timur
 * @version 1.0
 */

import itmo.labs.utils.CollectionManager;
import itmo.labs.utils.FileManager;

public class SaveCommand implements Command {
    private final CollectionManager collectionManager;
    private final FileManager fileManager;

    public SaveCommand(CollectionManager collectionManager, FileManager fileManager) {
        this.collectionManager = collectionManager;
        this.fileManager = fileManager;
    }

    @Override
    public void execute(String arg) {
        if (arg != null && !arg.isBlank()) {
            throw new IllegalArgumentException("save doesn't use additional arguments");
        } else {
            try {
                fileManager.save(collectionManager.getTickets());
                System.out.println("Collection has been successfully saved");
            } catch (RuntimeException e) {
                System.out.println("Error while saving collection");
            }
        }
    }

    @Override
    public String getDescription() {
        return "save: save all your changes in the file";
    }
}

