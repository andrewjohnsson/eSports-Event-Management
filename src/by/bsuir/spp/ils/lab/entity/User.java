package by.bsuir.spp.ils.lab.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.*;

/**
 * Class for person entity
 */
@Entity
public class User {

    /**
     * User's ID
     */
    private int userId;
    /**
     * User's name
     */
    private String name;
    /**
     * User's email
     */
    private String email;
    /**
     * User's documents
     */
    //private Document[] docs;
    /**
     * User's age
     */
    private int age;

    /**
     * User's login
     */
    private String login;

    /**
     * User's password
     */
    private String password;
    /**
     * Default constructor
     */
    public User() {
    }

    /**
     * User class constructor
     * @param name New user's name
     * @param sName New user's surname
     * @param age New user's age
     * @param email New user's email
     * @param docs New user's documents array
     */
    public void User(String name, String sName, int age, String email/*, Document docs*/) {
        // TODO implement here
    }

    /**
     * Returns user's name
     */
    @Basic
    @Column(name = "name")
    public String getName() {
        return this.name;
    }
    /**
     * Sets user's name
     * @param name String with user's  new name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns user's email
     */
    @Basic
    @Column(name = "email")
    public String getEmail() {
        return  this.email;
    }

    /**
     * Sets user's email
     * @param email String with user's new email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "age")
    /**
     * Returns user's age
     */
    public int getAge() {
        return this.age;
    }

    /**
     * Sets user's age
     * @param age Age to set
     */
    public void setAge(int age) {
        this.age = age;
    }

    @Id
    @Column(name = "user_id")
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "login")
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (userId != user.userId) return false;
        if (login != null ? !login.equals(user.login) : user.login != null) return false;
        if (password != null ? !password.equals(user.password) : user.password != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userId;
        result = 31 * result + (login != null ? login.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        return result;
    }
}