package ProjectRoot.Client.Booking;

import javafx.beans.property.SimpleStringProperty;

public class journey {
    private final SimpleStringProperty name,date,time,seat;

    public journey(String name, String date ,String time, String seat ){
        this.name = new SimpleStringProperty(name);
        this.date = new SimpleStringProperty(date);
        this.time = new SimpleStringProperty(time);
        this.seat = new SimpleStringProperty(seat);
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public void setDate(String date) {
        this.date.set(date);
    }

    public void setTime(String time) {
        this.time.set(time);
    }

    public void setseat(String seat) {
        this.seat.set(seat);
    }

    public String getName() {

        return name.get();
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
}
