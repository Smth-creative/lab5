package itmo.labs.commands;

/**
 * Command that removes all tickets from the collection.
 *
 * <p>Implements the {@link itmo.labs.commands.Command Command} interface.</p>
 *
 * @author bykov Timur
 * @version 1.0
 */


import itmo.labs.utils.CollectionManager;

public class ClearCommand implements Command {
    private final CollectionManager collectionManager;

    public ClearCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String arg) {
        if (arg != null && !arg.isBlank()) {
            throw new IllegalArgumentException("clear doesn't use additional arguments");
        } else {
            try {
                collectionManager.clear();
                System.out.println("Collection was cleared successfully");
            } catch (Exception e) {
                System.out.println("Error while clearing the collection");
            }
        }
    }

    @Override
    public String getDescription() {
        return "clear: clear the entire collection";
    }
}
