package itmo.labs.commands;

/**
 * Command that prints information about the collection
 * (type, initialisation time, number of elements, etc.).
 *
 * @author Bykov Timur
 * @version 1.0
 */

import itmo.labs.utils.CollectionManager;

public class InfoCommand implements Command {
    private final CollectionManager collectionManager;

    public InfoCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String arg) {
        if (arg != null && !arg.isBlank()) {
            throw new IllegalArgumentException("add doesn't use additional arguments");
        } else {
            System.out.println(collectionManager.info());
        }
    }

    @Override
    public String getDescription() {
        return "info: get information about the collection";
    }

}
