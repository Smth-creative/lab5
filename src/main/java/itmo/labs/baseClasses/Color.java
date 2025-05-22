package itmo.labs.baseClasses;

/**
 * Enumeration of eye colors for a {@link itmo.labs.baseClasses.Person Person}.
 *
 * <p>This enum is part of the console application that manages a collection
 * of {@link itmo.labs.baseClasses.Ticket Ticket} objects.</p>
 *
 * <h2>Assignment context</h2>
 * <p>The application stores tickets in a {@code LinkedHashSet}, supports
 * interactive commands, persists data in XML and validates user input.</p>
 *
 * @author Bykov Timur
 * @version 1.0
 */

public enum Color {
    GREEN,
    BLUE,
    YELLOW,
    WHITE,
    BROWN;
}
