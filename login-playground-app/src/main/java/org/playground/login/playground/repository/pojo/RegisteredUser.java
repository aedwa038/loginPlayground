package org.playground.login.playground.repository.pojo;

import org.jooq.Record;
import org.playground.login.playground.repository.anonotations.Column;
import org.playground.login.playground.repository.anonotations.Table;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Objects;


@Table(name = "users")
public class RegisteredUser {
    @Column(name = "id")
    Integer id;

    @Column(name = "username")
    String username;

    @Column(name = "password", sensitive = true)
    String password;

    @Column(name = "email")
    String email;

    @Column(name = "registered_date")
    LocalDateTime registeredDate;

    @Column(name = "last_login")
    LocalDateTime lastLoginDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getRegisteredDate() {
        return registeredDate;
    }

    public void setRegisteredDate(LocalDateTime registeredDate) {
        this.registeredDate = registeredDate;
    }

    public LocalDateTime getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(LocalDateTime lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RegisteredUser user = (RegisteredUser) o;
        return Objects.equals(id, user.id) &&
                Objects.equals(username, user.username) &&
                Objects.equals(password, user.password) &&
                Objects.equals(email, user.email) &&
                Objects.equals(registeredDate, user.registeredDate);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, username, password, email, registeredDate);
    }
}
