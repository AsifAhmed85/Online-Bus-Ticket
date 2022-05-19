package ProjectRoot.Server.BusInformation;

public class Journey {
    private String busname;
    private String start;
    private String destination;
    private String date;
    private String time;
    private BookedTickets bookedSit;

    public Journey(String busname, String start, String destination, String date, String time, String bookedSit) {

        this.busname = busname;
        this.start = start;
        this.destination = destination;
        this.date = date;
        this.time = time;
        this.bookedSit = new BookedTickets(bookedSit);
    }

    public String getBusname() {
        return busname;
    }

    public String getStart() {
        return start;
    }

    public String getDestination() {
        return destination;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public BookedTickets getBookedSit() { return bookedSit; }

    @Override
    public String toString() {
        return "Journey{" +
                "busname='" + busname + '\'' +
                ", start='" + start + '\'' +
                ", destination='" + destination + '\'' +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", bookedSit='" + bookedSit + '\'' +
                '}';
    }
}
