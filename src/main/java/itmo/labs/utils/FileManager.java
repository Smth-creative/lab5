package itmo.labs.utils;

/**
 * Utility class that reads the collection from and writes it to the XML file
 * provided via an environment variable.
 *
 * <p>Uses {@code FileReader} for reading and {@code PrintWriter} for writing,
 * while serialisation is delegated to Jackson XML.</p>
 *
 * @author Bykov Timur
 * @version 1.0
 */

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import itmo.labs.baseClasses.*;

import java.io.*;
import java.util.LinkedHashSet;
import java.util.List;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;

public class FileManager {
    private final String filepath;
    private XmlMapper xmlMapper = new XmlMapper();

    public FileManager(String filepath) {
        this.filepath = filepath;
    }

    public void save(LinkedHashSet<Ticket> tickets) {
        try {
            PrintWriter writer = new PrintWriter(new FileWriter(filepath));

            writer.println("<tickets>");
            for (Ticket t : tickets) {
                String xml = xmlMapper.writeValueAsString(t);
                writer.println(xml);
            }
            writer.println("</tickets>");
            writer.close();
        } catch (IOException e) {
            System.out.println("Error while saving tickets");
        }
    }

    // Обёртка для загрузки элементов
    @JacksonXmlRootElement(localName = "tickets")
    private static class TicketsWrapper {

        @JacksonXmlElementWrapper(useWrapping = false)
        @JacksonXmlProperty(localName = "Ticket")
        private List<Ticket> tickets;

        public TicketsWrapper() {
        } // Нужен для Jackson

        public TicketsWrapper(List<Ticket> tickets) {
            this.tickets = tickets;
        }

        public List<Ticket> getTickets() {
            return tickets;
        }

        public void setTickets(List<Ticket> tickets) {
            this.tickets = tickets;
        }
    }

    public LinkedHashSet<Ticket> load() {
        try {
            File file = new File(filepath); 

            // Чтение XML и преобразование его в TicketsWrapper (вспомогательный класс с List<Ticket>)
            TicketsWrapper wrapper = xmlMapper.readValue(file, TicketsWrapper.class);

            // Так как Jackson при чтении <person /> автоматически создаёт объект со всеми null полями, надо вручную менять таких person
            // Проверяем на null, если пустой файл
            if (wrapper.getTickets() != null) {
                for (Ticket t : wrapper.getTickets()) {
                    if (t.getPerson() != null &&
                            t.getPerson().getPassportID() == null &&
                            t.getPerson().getEyeColor() == null &&
                            t.getPerson().getNationality() == null) {
                        t.setPerson(null);
                    }
                }
            }

            // Получаем список Ticket из обёртки
            List<Ticket> list = wrapper.getTickets();

            // Проверяем на null, если пустой файл
            if (list != null) {
                return new LinkedHashSet<>(list);
            } else {
                return new LinkedHashSet<>();
            }

        } catch (IOException e) {
            System.err.println("Error while loading XML-file");
            System.exit(1);
            return new LinkedHashSet<>();
        }
    }
}
