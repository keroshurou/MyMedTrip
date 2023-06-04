package hajarshaufi.fyp.java;

public class Account {

    private String accountID;
    private String username;
    private String password;
    private String retypePass;
    private String name;
    private String email;

    public Account(String accountID, String username, String password, String retypePass, String name, String email) {
        this.accountID = accountID;
        this.username = username;
        this.password = password;
        this.retypePass = retypePass;
        this.name = name;
        this.email = email;
    }

    public String getAccountID() {
        return accountID;
    }

    public void setAccountID(String accountID) {
        this.accountID = accountID;
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

    public String getRetypePass() {
        return retypePass;
    }

    public void setRetypePass(String retypePass) {
        this.retypePass = retypePass;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
