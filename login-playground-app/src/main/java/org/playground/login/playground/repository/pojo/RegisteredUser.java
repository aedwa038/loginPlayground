package org.playground.login.playground.repository.pojo;

import org.jooq.Record;
import org.playground.login.playground.repository.anonotations.Column;
import org.playground.login.playground.repository.anonotations.Table;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Map;


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

    public static RegisteredUser create(Map<String, Object> row) {
        RegisteredUser registeredUser = new RegisteredUser();
        registeredUser.setId((Integer) row.get("id"));
        registeredUser.setLastLoginDate(((Timestamp) row.get("last_login")).toLocalDateTime());
        registeredUser.setRegisteredDate(((Timestamp) row.get("registered_date")).toLocalDateTime());
        registeredUser.setUsername((String) row.get("username"));
        registeredUser.setEmail((String) row.get("email"));
        return registeredUser;
    }

    public static RegisteredUser create(Record row) {
        RegisteredUser registeredUser = new RegisteredUser();
        registeredUser.setId((Integer) row.get("id"));
        registeredUser.setLastLoginDate(((Timestamp) row.get("last_login")).toLocalDateTime());
        registeredUser.setRegisteredDate(((Timestamp) row.get("registered_date")).toLocalDateTime());
        registeredUser.setUsername((String) row.get("username"));
        registeredUser.setEmail((String) row.get("email"));
        return registeredUser;
    }



}
