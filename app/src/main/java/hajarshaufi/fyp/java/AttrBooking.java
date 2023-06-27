package hajarshaufi.fyp.java;

public class AttrBooking {

    private String id;
    private String attractions;
    private String time;
    private String date;
    private String tickets;

    public AttrBooking(String id, String attractions, String time, String date, String tickets) {
        this.id = id;
        this.attractions = attractions;
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

    public String getAttractions() {
        return attractions;
    }

    public void setAttractions(String attractions) {
        this.attractions = attractions;
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
