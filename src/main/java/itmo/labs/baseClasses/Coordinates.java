package itmo.labs.baseClasses;

/**
 * Represents two-dimensional spatial coordinates associated with a ticket.
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

public class Coordinates {
    @JacksonXmlProperty(localName = "x")
    private Long x; //Максимальное значение поля: 938, Поле не может быть null

    @JacksonXmlProperty(localName = "y")
    private Long y; //Поле не может быть null

    public Coordinates(Long x, Long y) {
        setX(x);
        setY(y);
    }

    public Coordinates() {
        // Нужно для Jackson
    }


    public void setX(Long x) {
        if (x == null || x > 938) {
            throw new IllegalArgumentException("illegal x");
        } else {
            this.x = x;
        }
    }

    public void setY(Long y) {
        if (y == null) {
            throw new IllegalArgumentException("illegal y");
        } else {
            this.y = y;
        }
    }

    public Long getX() {
        return x;
    }

    public Long getY() {
        return y;
    }

    @Override
    public String toString() {
        return "Coordinates{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
