package itmo.labs.commands;

/**
 * Command that updates an existing ticket identified by its ID with values
 * provided by the user.
 *
 * <p>In version 1.1 was changed the response to errors when getting illegal args from the console</p>
 *
 * @author Bykov Timur
 * @version 1.1 2025-05-20
 * @since 1.0
 */

import itmo.labs.baseClasses.*;
import itmo.labs.utils.CollectionManager;

import java.io.BufferedReader;
import java.io.IOException;

public class UpdateCommand implements Command {
    private final CollectionManager collectionManager;
    private final BufferedReader reader;

    public UpdateCommand(CollectionManager collectionManager, BufferedReader reader) {
        this.collectionManager = collectionManager;
        this.reader = reader;
    }

    @Override
    public void execute(String arg) {
        if (arg == null || arg.isBlank()) {
            throw new IllegalArgumentException("lack of arguments");
        } else {
            long id = Long.parseLong(arg);
            try {
                Ticket oldTicket = collectionManager.getByID(id);
                collectionManager.removeByID(id);
                try {
                    collectionManager.add(readTicketFromBufferedReader(id));
                    System.out.println("Ticket with id = " + arg + " was successfully updated");
                } catch (Exception e) {
                    collectionManager.add(oldTicket);
                }
            } catch (Exception e) {
                throw new RuntimeException("Error while updating ticket with id = " + arg);
            }
        }
    }

    private Ticket readTicketFromBufferedReader(long id) throws IOException {
        Ticket ticket = new Ticket();
        Coordinates coordinates = new Coordinates();
        Person person = new Person();

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
            Color eyeColor;
            person.setPassportID();

            while (true) {
                try {
                    System.out.print("Enter the eye color (GREEN, BLUE, YELLOW, WHITE, BROWN): ");
                    var value = reader.readLine();
                    if (value == null || value.isBlank()) {
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

        ticket.setCoordinates(coordinates);
        ticket.setPerson(person);
        ticket.setID(id);
        return ticket;
    }

    @Override
    public void executeWithReader(String arg, BufferedReader reader) {
        if (arg == null || arg.isBlank()) {
            System.out.println("lack of arguments");
        } else {
            long id = Long.parseLong(arg);
            try {
                Ticket oldTicket = collectionManager.getByID(id);
                collectionManager.removeByID(id);
                try {
                    collectionManager.add(readTicketFromBufferedReader(id));
                    System.out.println("Ticket with id = " + arg + " was successfully updated");
                } catch (Exception e) {
                    collectionManager.add(oldTicket);
                }
            } catch (Exception e) {
                System.out.println("Error while updating ticket with id = " + arg);
            }
        }
    }

    @Override
    public String getDescription() {
        return "update: update ticket elements by ID";
    }
}
