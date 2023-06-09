package hajarshaufi.fyp.java;

public class Admin {

    private String id;
    private String username;
    private String password;
    private String name;
    private String staffNo;

    public Admin(String id, String username, String password, String name, String staffNo) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.staffNo = staffNo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStaffNo() {
        return staffNo;
    }

    public void setStaffNo(String staffNo) {
        this.staffNo = staffNo;
    }
}
