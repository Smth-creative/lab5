package itmo.labs.baseClasses;

/**
 * Represents a ticket with unique identifier, coordinates, pricing
 * information and owner details.
 *
 * <p>This class is part of the console application that manages a collection
 * of {@link itmo.labs.baseClasses.Ticket Ticket} objects.</p>
 *
 * <h2>Assignment context</h2>
 * <p>The application stores tickets in a {@code LinkedHashSet}, supports
 * interactive commands, persists data in XML and validates user input.</p>
 *
 * @author Bykov Timur
 * @version 1.0
 */

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import itmo.labs.utils.IDGenerator;

import java.util.Date;

public class Ticket implements Comparable<Ticket> {
    @JacksonXmlProperty(localName = "id")
    private Long id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически

    @JacksonXmlProperty(localName = "name")
    private String name; //Поле не может быть null, Строка не может быть пустой

    @JacksonXmlProperty(localName = "coordinates")
    private Coordinates coordinates; //Поле не может быть null

    @JacksonXmlProperty(localName = "creationDate")
    private java.util.Date creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически

    @JacksonXmlProperty(localName = "price")
    private Float price; //Поле не может быть null, Значение поля должно быть больше 0

    @JacksonXmlProperty(localName = "discount")
    private int discount; //Значение поля должно быть больше 0, Максимальное значение поля: 100

    @JacksonXmlProperty(localName = "type")
    private TicketType type; //Поле не может быть null

    @JacksonXmlProperty(localName = "person")
    private Person person; //Поле может быть null

    public Ticket(String name, Coordinates coordinates, Float price, int discount,
                  TicketType type, Person person) {
        setID();
        setName(name);
        setCoordinates(coordinates);
        setDate();
        setPrice(price);
        setDiscount(discount);
        setType(type);
        setPerson(person);
    }

    public Ticket(Long id, String name, Coordinates coordinates, Float price, int discount,
                  TicketType type, Person person) {
        setID(id);
        setName(name);
        setCoordinates(coordinates);
        setDate();
        setPrice(price);
        setDiscount(discount);
        setType(type);
        setPerson(person);
    }

    public Ticket() {
        // Нужно для Jackson
    }

    public void setID() {
        id = IDGenerator.getNextTicketId();
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("illegal id");
        }
    }

    public void setID(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("illegal id");
        }
        this.id = id;
        IDGenerator.updateTicketId(this.id);
    }

    public void setName(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("illegal name");
        } else {
            this.name = name;
        }
    }

    public void setDate() {
        creationDate = new Date();
    }

    public void setDate(java.util.Date creationDate) {
        if (creationDate == null) {
            throw new IllegalArgumentException("illegal date");
        } else {
            this.creationDate = creationDate;
        }
    }

    public void setCoordinates(Coordinates coordinates) {
        if (coordinates == null) {
            throw new IllegalArgumentException("illegal coordinates");
        } else {
            this.coordinates = coordinates;
        }
    }

    public void setPrice(Float price) {
        if (price == null || price < 0) {
            throw new IllegalArgumentException("illegal price");
        } else {
            this.price = price;
        }
    }

    public void setDiscount(int discount) {
        if (discount < 0 || discount > 100) {
            throw new IllegalArgumentException("illegal discount");
        } else {
            this.discount = discount;
        }
    }

    public void setType(TicketType type) {
        if (type == null) {
            throw new IllegalArgumentException("illegal type");
        } else {
            this.type = type;
        }
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Long getID() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public Float getPrice() {
        return price;
    }

    public int getDiscount() {
        return discount;
    }

    public TicketType getType() {
        return type;
    }

    public Person getPerson() {
        return person;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", coordinates=" + coordinates +
                ", creationDate=" + creationDate +
                ", price=" + price +
                ", discount=" + discount +
                ", type=" + type +
                ", person=" + person +
                '}';
    }

    @Override
    public int compareTo(Ticket another) {
        int res = Float.compare(this.price, another.price);
        if (res != 0) {
            return res;
        } else {
            return Integer.compare(this.discount, another.discount);
        }
    }
}
