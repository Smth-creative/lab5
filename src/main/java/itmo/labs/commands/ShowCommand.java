package itmo.labs.commands;

/**
 * Command that prints all tickets in the collection using their
 * {@link java.lang.Object#toString()} representation.
 *
 * @author Bykov Timur
 * @version 1.0
 */

import itmo.labs.utils.CollectionManager;

public class ShowCommand implements Command {
    private final CollectionManager collectionManager;

    public ShowCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String arg) {
        if (arg != null && !arg.isBlank()) {
            System.out.println("show doesn't use additional arguments");
        } else {
            if (collectionManager.getTickets().isEmpty()) {
                System.out.println("Collection is Empty");
            } else {
                collectionManager.getTickets().forEach(System.out::println);
            }
        }
    }

    @Override
    public String getDescription() {
        return "show: print all collection elements";
    }

}

