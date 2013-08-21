package be.ordina.droidina.fsm.model;

public class Agent {
    public static final String KIND="agent";
    public static final String FIRSTNAME="firstName";
    public static final String LASTNAME="lastName";
    public static final String USERNAME="userName";
    public static final String PASSWORD="password";

    private String key;
    private String firstName, lastName, userName;

    //Encrypted
    private String password;

    public String getKey() {
        return key;
    }
    public void setKey(String key) {
        this.key = key;
    }

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}