package itmo.labs.utils;

/**
 * Utility class that stores and manipulates the {@code LinkedHashSet<Ticket>}
 * collection: addition, removal, querying, sorting and filtering.
 *
 * <p>Used by command classes to perform operations on the dataset.</p>
 *
 * @author Bykov Timur
 * @version 1.0
 */

import itmo.labs.baseClasses.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class CollectionManager {
    private LinkedHashSet<Ticket> tickets;
    private final FileManager fileManager;
    private final LocalDateTime initTime;

    public CollectionManager(FileManager fileManager) {
        this.fileManager = fileManager;
        this.tickets = fileManager.load();
        this.initTime = LocalDateTime.now();

        if (tickets != null) {
            // Проверка id при загрузке
            Set<Long> ids = new HashSet<>();
            for (Ticket t : tickets) {
                if (!ids.add(t.getID())) {
                    System.out.println(ids);
                    throw new IllegalArgumentException("Found non-unique ID:" + t.getID());
                }
            }

            // Проверка passportID при загрузке
            Set<String> passportIDs = new HashSet<>();
            for (Ticket t : tickets) {
                Person p = t.getPerson();
                if (p != null) {
                    String passportID = p.getPassportID();
                    if (!passportIDs.add(passportID)) {
                        throw new IllegalArgumentException("Found non-unique passportID: " + passportID);
                    }
                }
            }
        }
    }

    public void add(Ticket ticket) {
        // Проверяем id
        if (getByID(ticket.getID()) != null) {
            throw new IllegalArgumentException("Tried to add ticket with illegal ID:" + ticket.getID());
        }

        // Проверяем passportID
        if (ticket.getPerson() != null) {
            for (Ticket t : tickets) {
                Person p = t.getPerson();
                if (p != null && p.getPassportID().equals(ticket.getPerson().getPassportID())) {
                    throw new IllegalArgumentException("Tried to add ticket with illegal passportID:" + p.getPassportID());
                }
            }
        }
        tickets.add(ticket);
    }

    public boolean removeByID(long id) {
        return tickets.removeIf(ticket -> ticket.getID() == id);
    }

    public void clear() {
        tickets.clear();
    }

    public LinkedHashSet<Ticket> getTickets() {
        return tickets;
    }

    public Ticket getByID(long id) {
        if (tickets != null) {
            for (Ticket ticket : tickets) {
                if (ticket.getID() == id) return ticket;
            }
        }
        return null;
    }

    public void removeGreater(long id) {
        Ticket ticket = getByID(id);

        Iterator<Ticket> iterator = tickets.iterator();
        while (iterator.hasNext()) {
            Ticket t = iterator.next();
            if (t.compareTo(ticket) > 0) {
                iterator.remove();  // безопасное удаление во время итерации
            }
        }
    }

    public void removeLower(long id) {
        Ticket ticket = getByID(id);

        Iterator<Ticket> iterator = tickets.iterator();
        while (iterator.hasNext()) {
            Ticket t = iterator.next();
            if (t.compareTo(ticket) < 0) {
                iterator.remove();  // безопасное удаление во время итерации
            }
        }
    }

    public List<Ticket> minByName() {
        List<Ticket> result = new ArrayList<>();
        List<Integer> names = new ArrayList<>();

        for (Ticket t : tickets) {
            names.add(t.getName().length());
        }

        int minNameLength = Collections.min(names);
        for (Ticket t : tickets) {
            if (t.getName().length() == minNameLength) {
                result.add(t);
            }
        }
        return result;
    }

    public ArrayList<Ticket> filter(String pattern) {
        ArrayList<Ticket> filtered = new ArrayList<>();

        for (Ticket t : tickets) {
            if (t.getName().startsWith(pattern)) {
                filtered.add(t);
            }
        }

        return filtered;
    }

    public HashSet<Float> uniquePrice() {
        HashSet<Float> prices = new HashSet<>();

        for (Ticket t : tickets) {
            prices.add(t.getPrice());
        }

        return prices;
    }

    public String info() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return "Type: " + tickets.getClass().getName() + "\n"
                + "Initialization date: " + initTime.format(formatter) + "\n"
                + "Collection size: " + tickets.size();
    }
}
