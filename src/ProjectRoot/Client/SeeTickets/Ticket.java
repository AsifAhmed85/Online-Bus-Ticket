package ProjectRoot.Client.SeeTickets;

import javafx.beans.property.SimpleStringProperty;

public class Ticket {
    private final SimpleStringProperty name;
    private final SimpleStringProperty start;
    private final SimpleStringProperty end;
    private final SimpleStringProperty date;
    private final SimpleStringProperty time;
    private final SimpleStringProperty seat;

    public Ticket(String name, String start, String end, String date, String time, String seat) {
        this.name = new SimpleStringProperty(name);
        this.start = new SimpleStringProperty(start);
        this.end = new SimpleStringProperty(end);
        this.date = new SimpleStringProperty(date);
        this.time = new SimpleStringProperty(time);
        this.seat = new SimpleStringProperty(seat);
    }

    public String getName() {
        return name.get();
    }

    public String getStart() {
        return start.get();
    }

    public String getEnd() {
        return end.get();
    }

    public String getDate() {
        return date.get();
    }

    public String getTime() {
        return time.get();
    }

    public String getSeat() {
        return seat.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public void setStart(String start) {
        this.start.set(start);
    }

    public void setEnd(String destination) {
        this.end.set(destination);
    }

    public void setDate(String date) {
        this.date.set(date);
    }

    public void setTime(String time) {
        this.time.set(time);
    }

    public void setSeat(String seat) {
        this.seat.set(seat);
    }
}
