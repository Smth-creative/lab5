package itmo.labs.commands;

/**
 * Command that prints all tickets whose {@code name} field starts with the
 * specified prefix.
 *
 * <p>Implements the {@link itmo.labs.commands.Command Command} interface.</p>
 *
 * @author Bykov Timur
 * @version 1.0
 */

import itmo.labs.baseClasses.Ticket;
import itmo.labs.utils.CollectionManager;

import java.util.ArrayList;

public class FilterStartsWithNameCommand implements Command {
    private final CollectionManager collectionManager;

    public FilterStartsWithNameCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String arg) {
        if (arg == null || arg.isBlank()) {
            System.out.println("lack of arguments");
        } else {
            try {
                ArrayList<Ticket> names = collectionManager.filter(arg);
                if (!names.isEmpty()) {
                    System.out.println("These are all matching elements:");

                    for (Ticket t : names) {
                        System.out.println(t);
                    }
                } else {
                    System.out.println("There are no matching elements");
                }
            } catch (Exception e) {
                System.out.println("Error while filtering collection's elements");
            }
        }

    }

    @Override
    public String getDescription() {
        return "filter_starts_with_name {name}: show elements that start with given pattern";
    }
}
