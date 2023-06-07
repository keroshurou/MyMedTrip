package hajarshaufi.fyp.java;

public class Establishment {

    private String id;
    private String type;
    private String establishmentName;
    private String address;
    private String days;
    private String hours;
    private String days2;
    private String hours2;
    private String city;
    private String tag1;
    private String tag2;

    public Establishment(String id, String type, String establishmentName, String address, String days, String hours, String days2, String hours2, String city, String tag1, String tag2) {
        this.id = id;
        this.type = type;
        this.establishmentName = establishmentName;
        this.address = address;
        this.days = days;
        this.hours = hours;
        this.days2 = days2;
        this.hours2 = hours2;
        this.city = city;
        this.tag1 = tag1;
        this.tag2 = tag2;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEstablishmentName() {
        return establishmentName;
    }

    public void setEstablishmentName(String establishmentName) {
        this.establishmentName = establishmentName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }

    public String getHours() {
        return hours;
    }

    public void setHours(String hours) {
        this.hours = hours;
    }

    public String getDays2() {
        return days2;
    }

    public void setDays2(String days2) {
        this.days2 = days2;
    }

    public String getHours2() {
        return hours2;
    }

    public void setHours2(String hours2) {
        this.hours2 = hours2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getTag1() {
        return tag1;
    }

    public void setTag1(String tag1) {
        this.tag1 = tag1;
    }

    public String getTag2() {
        return tag2;
    }

    public void setTag2(String tag2) {
        this.tag2 = tag2;
    }
}
