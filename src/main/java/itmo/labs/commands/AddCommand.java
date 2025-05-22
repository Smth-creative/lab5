package itmo.labs.commands;

/**
 * Command that reads a {@link itmo.labs.baseClasses.Ticket Ticket} from user
 * input (interactive mode or script) and adds it to the collection.
 *
 * <p>Implements the {@link itmo.labs.commands.Command Command} interface.</p>
 * <p>In version 1.1 was changed the response to errors when getting illegal args from the console</p>
 *
 * @author Bykov Timur
 * @version 1.1, 2025-05-19
 * @since 1.0
 */

import itmo.labs.baseClasses.*;
import itmo.labs.utils.CollectionManager;

import java.io.BufferedReader;
import java.io.IOException;

public class AddCommand implements Command {
    private final CollectionManager cm;
    private final BufferedReader reader;

    public AddCommand(CollectionManager cm, BufferedReader reader) {
        this.cm = cm;
        this.reader = reader;
    }

    @Override
    public void execute(String arg) {
        if (arg != null && !arg.isBlank()) {
            throw new IllegalArgumentException("add doesn't use additional arguments");
        } else {
            try {
                Ticket ticket = readTicketFromBufferedReader(reader, cm);
                cm.add(ticket);
                System.out.println("Added ticket with ID:" + ticket.getID());
            } catch (IOException e) {
                throw new RuntimeException("Error while adding a new element");
            }
        }
    }

    private Ticket readTicketFromBufferedReader(BufferedReader reader, CollectionManager cm) throws IOException {
        Ticket ticket = new Ticket();
        Coordinates coordinates = new Coordinates();
        Person person = new Person();
        try {

            while (true) {
                try {
                    System.out.print("Enter the ticket name: ");
                    String name = reader.readLine().trim();
                    ticket.setName(name);
                    break;
                } catch (IllegalArgumentException e) {
                    System.out.println("Illegal input. Try again");
                }
            }

            while (true) {
                try {
                    System.out.print("Enter the X coordinate (Long): ");
                    Long x = Long.parseLong(reader.readLine().trim());
                    coordinates.setX(x);
                    break;
                } catch (IllegalArgumentException e) {
                    System.out.println("Illegal input. Try again");
                }
            }

            while (true) {
                try {
                    System.out.print("Enter the Y coordinate (Long): ");
                    Long y = Long.parseLong(reader.readLine().trim());
                    coordinates.setY(y);
                    break;
                } catch (IllegalArgumentException e) {
                    System.out.println("Illegal input. Try again");
                }
            }

            while (true) {
                try {
                    System.out.print("Enter the price (Float): ");
                    Float price = Float.parseFloat(reader.readLine().trim());
                    ticket.setPrice(price);
                    break;
                } catch (IllegalArgumentException e) {
                    System.out.println("Illegal input. Try again");
                }
            }

            while (true) {
                try {
                    System.out.print("Enter the discount (int): ");
                    int discount = Integer.parseInt(reader.readLine().trim());
                    ticket.setDiscount(discount);
                    break;
                } catch (IllegalArgumentException e) {
                    System.out.println("Illegal input. Try again");
                }
            }

            while (true) {
                try {
                    System.out.print("Enter the ticket type (VIP, USUAL, BUDGETARY, CHEAP): ");
                    TicketType type = TicketType.valueOf(reader.readLine().trim().toUpperCase());
                    ticket.setType(type);
                    break;
                } catch (IllegalArgumentException e) {
                    System.out.println("Illegal input. Try again");
                }
            }

            System.out.print("Is there any info about the passenger? (y/n): ");
            String hasPerson = reader.readLine().trim().toLowerCase();

            while (!hasPerson.equals("y") && !hasPerson.equals("n")) {
                System.out.println("Illegal input. Try again");
                System.out.print("Is there any info about the passenger? (y/n): ");
                hasPerson = reader.readLine().trim().toLowerCase();
            }

            if (hasPerson.equals("y")) {
                System.out.print("Enter the eye color (GREEN, BLUE, YELLOW, WHITE, BROWN): ");
                person.setPassportID();
                Color eyeColor;

                while (true) {
                    try {
                        var value = reader.readLine();
                        if (value == null) {
                            eyeColor = null;
                        } else {
                            eyeColor = Color.valueOf(value.trim().toUpperCase());
                        }
                        person.setEyeColor(eyeColor);
                        break;

                    } catch (IllegalArgumentException e) {
                        System.out.println("Illegal input. Try again");
                    }
                }

                while (true) {
                    try {
                        System.out.print("Enter the nationality (RUSSIA, SPAIN, SOUTH_KOREA, NORTH_KOREA, JAPAN): ");
                        Country nationality = Country.valueOf(reader.readLine().trim().toUpperCase());
                        person.setNationality(nationality);
                        break;

                    } catch (IllegalArgumentException e) {
                        System.out.println("Illegal input. Try again");
                    }
                }
            } else {
                person = null;
            }

            ticket.setID();
            ticket.setDate();
            ticket.setPerson(person);
            ticket.setCoordinates(coordinates);
            return ticket;
        } catch (IllegalArgumentException e) {
            System.out.println("Error while analysing user input");
            System.out.println("Try again");
            return readTicketFromBufferedReader(reader, cm);
        }
    }

    @Override
    public void executeWithReader(String args, BufferedReader reader) throws IOException {
        Ticket ticket = readTicketFromBufferedReader(reader, cm);
        cm.add(ticket);
        System.out.println("Added ticket with ID:" + ticket.getID());
    }

    @Override
    public String getDescription() {
        return "add {element}: adds a new element to the collection";
    }
}


