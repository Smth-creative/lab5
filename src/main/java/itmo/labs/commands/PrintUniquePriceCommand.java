package itmo.labs.commands;

/**
 * Command that prints the set of unique ticket prices present in the collection.
 *
 * @author Bykov Timur
 * @version 1.0
 */

import itmo.labs.utils.CollectionManager;

import java.util.HashSet;


public class PrintUniquePriceCommand implements Command {
    private final CollectionManager collectionManager;

    public PrintUniquePriceCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String arg) {
        if (arg != null && !arg.isBlank()) {
            throw new IllegalArgumentException("print_unique_price doesn't use additional arguments");
        } else {
            try {
                HashSet<Float> prices = collectionManager.uniquePrice();
                for (float f : prices) {
                    System.out.println(f);
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public String getDescription() {
        return "print_unique_price: show unique prices of tickets";
    }
}
