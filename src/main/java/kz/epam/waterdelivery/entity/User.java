package kz.epam.waterdelivery.entity;

public class User extends Entity {
    private int wallet;
    private String firstName;
    private String lastName;
    private String loginEmail;
    private String password;
    private Role role;

    public enum Role {
        ADMIN, CLIENT
    }

    public User() {
    }

    public int getWallet() {
        return wallet;
    }

    public void setWallet(int wallet) {
        this.wallet = wallet;
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
                "wallet=" + wallet +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", loginEmail='" + loginEmail + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                '}';
    }
}
