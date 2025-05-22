package itmo.labs.commands;

/**
 * Command that removes from the collection every ticket that exceeds the given
 * reference ticket (according to {@link Comparable} order).
 *
 * @author Bykov Timur
 * @version 1.0
 */

import itmo.labs.utils.CollectionManager;

public class RemoveGreaterCommand implements Command {
    private final CollectionManager collectionManager;

    public RemoveGreaterCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String arg) {
        if (arg == null || arg.isBlank()) {
            throw new IllegalArgumentException("lack_of_arguments");
        } else {
            try {

                try {
                    long id = Long.parseLong(arg);
                } catch (Exception e) {
                    throw new IllegalArgumentException("Argument isn't id");
                }

                collectionManager.removeGreater(Long.parseLong(arg));
                System.out.println("All elements greater than given ticket (id = " + arg + ") have been removed");
            } catch (Exception e) {
                throw new RuntimeException("Error while removing elements greater than id = " + arg);
            }
        }
    }


    @Override
    public String getDescription() {
        return "remove_greater {id}: remove all elements greater than given ticket";
    }
}
