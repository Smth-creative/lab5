package itmo.labs.commands;

/**
 * Command that removes from the collection every ticket smaller than the given
 * reference ticket (according to {@link Comparable} order).
 *
 * @author Bykov Timur
 * @version 1.0
 */

import itmo.labs.utils.CollectionManager;

public class RemoveLowerCommand implements Command {
    private final CollectionManager collectionManager;

    public RemoveLowerCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String arg) {
        if (arg == null || arg.isBlank()) {
            throw new IllegalArgumentException("lack of arguments");
        } else {
            try {

                try {
                    long id = Long.parseLong(arg);
                } catch (Exception e) {
                    throw new IllegalArgumentException("Argument isn't id");
                }

                collectionManager.removeLower(Long.parseLong(arg));
                System.out.println("All elements lower than given ticket (id = " + arg + ") have been removed");
            } catch (Exception e) {
                throw new RuntimeException("Error while removing elements lower than id = " + arg);
            }
        }
    }

    @Override
    public String getDescription() {
        return "remove_lower {id}: remove all elements lower than given ticket";
    }
}
