package itmo.labs.commands;

/**
 * Command that displays any ticket whose {@code name} field is minimal.
 *
 * @author Bykov Timur
 * @version 1.0
 */

import itmo.labs.baseClasses.Ticket;
import itmo.labs.utils.CollectionManager;

public class MinByNameCommand implements Command {
    private final CollectionManager collectionManager;

    public MinByNameCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String arg) {
        if (arg != null && !arg.isBlank()) {
            throw new IllegalArgumentException("clear doesn't use additional arguments");
        } else {
            for (Ticket t : collectionManager.minByName()) {
                System.out.println(t);
            }
        }
    }

    @Override
    public String getDescription() {
        return "min_by_name: show elements with smallest names";
    }
}
