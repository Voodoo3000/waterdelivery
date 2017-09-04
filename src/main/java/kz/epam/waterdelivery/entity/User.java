package kz.epam.waterdelivery.entity;

public class User {
    private int userId;
    private int wallet;
    private String firstname;
    private String lastName;
    private String loginEmail;
    private String password;
    private Role role;

    public enum Role {
        ADMIN, CLIENT
    }

    public User() {
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getWallet() {
        return wallet;
    }

    public void setWallet(int wallet) {
        this.wallet = wallet;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLoginEmail() {
        return loginEmail;
    }

    public void setLoginEmail(String loginEmail) {
        this.loginEmail = loginEmail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", wallet=" + wallet +
                ", firstname='" + firstname + '\'' +
                ", lastName='" + lastName + '\'' +
                ", loginEmail='" + loginEmail + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                '}';
    }
}
