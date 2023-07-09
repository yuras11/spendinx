package by.spendinx.entity;

public class User extends Entity {
    private int id;
    private String login;
    private String password;
    private String surname;
    private String name;
    private String dateOfBirth;

    public User(int id, String login, String password, String surname, String name, String dateOfBirth) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.surname = surname;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
    }

    public int getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getSurname() {
        return surname;
    }

    public String getName() {
        return name;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public String toString() {
        return "User { " + '\n' +
                "id = " + id + '\n' +
                "login = " + login + '\n' +
                "password = " + password + '\n' +
                "surname = " + surname + '\n' +
                "name = " + name + '\n' +
                "dateOfBirth = " + dateOfBirth + '\n' +
                "}";
    }
}
