package hajarshaufi.fyp.java;

public class BusBooking {

    private String id;
    private String route;
    private String time;
    private String date;
    private String tickets;

    public BusBooking(String id, String route, String time, String date, String tickets) {
        this.id = id;
        this.route = route;
        this.time = time;
        this.date = date;
        this.tickets = tickets;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTickets() {
        return tickets;
    }

    public void setTickets(String tickets) {
        this.tickets = tickets;
    }
}
