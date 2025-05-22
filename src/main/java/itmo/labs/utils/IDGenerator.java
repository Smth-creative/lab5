package itmo.labs.utils;

/**
 * Utility class that generates unique IDs for tickets and persons, guaranteeing
 * their uniqueness within a running session.
 *
 * @author Bykov Timur
 * @version 1.0
 */

public class IDGenerator {
    private static long ticketId = 1;
    private static long personId = 1;

    public static long getNextTicketId() {
        return ticketId++;
    }

    public static String getNextPersonId() {
        // %07d - недостающая часть из нулей, 7 знаков, цифры
        return String.format("PID%07d", personId++);
    }

    // Потом подтягиваем id из xml
    public static void updateTicketId(long id) {
        if (id >= ticketId) {
            ticketId = id + 1;
        }
    }

    public static void updatePersonId(long id) {
        if (id >= personId) {
            personId = id + 1;
        }
    }

    public static void reset() {
        ticketId = 1;
        personId = 1;
    }
}
