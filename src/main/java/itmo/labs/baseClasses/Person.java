package itmo.labs.baseClasses;

/**
 * Describes a person linked to a ticket (passport ID, eye colour and nationality).
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

public class Person {

    @JacksonXmlProperty(localName = "passportID")
    private String passportID; //Значение этого поля должно быть уникальным, Длина строки должна быть не меньше 10, Поле не может быть null

    @JacksonXmlProperty(localName = "eyeColor")
    private Color eyeColor; //Поле может быть null

    @JacksonXmlProperty(localName = "nationality")
    private Country nationality; //Поле не может быть null

    public Person(Color eyeColor, Country nationality) {
        setPassportID();
        setEyeColor(eyeColor);
        setNationality(nationality);
    }

    public Person(String passportID, Color eyeColor, Country nationality) {
        setPassportID(passportID);
        setEyeColor(eyeColor);
        setNationality(nationality);
    }

    public Person() {
        // Нужно для Jackson
    }

    public void setPassportID() {
        passportID = IDGenerator.getNextPersonId();
        if (passportID == null || passportID.length() < 10) {
            throw new IllegalArgumentException("illegal passportID");
        }
    }

    private void setPassportID(String passportID) {
        if (passportID == null || passportID.length() < 10) {
            throw new IllegalArgumentException("illegal passportID");
        }
        long newPassportID = Long.parseLong(passportID.substring(3));
        IDGenerator.updatePersonId(newPassportID);
        this.passportID = passportID;
    }

    public void setEyeColor(Color eyeColor) {
        if (eyeColor == null) {
            throw new IllegalArgumentException("illegal eyeColor");
        } else {
            this.eyeColor = eyeColor;
        }
    }

    public void setNationality(Country nationality) {
        if (nationality == null) {
            throw new IllegalArgumentException("illegal nationality");
        } else {
            this.nationality = nationality;
        }
    }

    public String getPassportID() {
        return passportID;
    }

    public Color getEyeColor() {
        return eyeColor;
    }

    public Country getNationality() {
        return nationality;
    }

    @Override
    public String toString() {
        return "Person{" +
                "passportID='" + passportID + '\'' +
                ", eyeColor=" + eyeColor +
                ", nationality=" + nationality +
                '}';
    }
}
