package itmo.labs.commands;

/**
 * Command that removes a ticket from the collection by its unique identifier.
 *
 * @author Bykov Timur
 * @version 1.0
 */

import itmo.labs.utils.CollectionManager;

public class RemoveByIDCommand implements Command {
    private final CollectionManager collectionManager;

    public RemoveByIDCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String arg) {
        if (arg == null || arg.isBlank()) {
            throw new IllegalArgumentException("lack of arguments");
        } else {

            long id = Long.parseLong(arg);
            try {
                collectionManager.removeByID(id);
                System.out.println("Ticket with id = " + arg + " has been successfully removed");
            } catch (Exception e) {
                throw new RuntimeException("Error while removing ticket with id = " + arg);
            }
        }
    }


    @Override
    public String getDescription() {
        return "remove_by_id: remove an element by given id";
    }
}
