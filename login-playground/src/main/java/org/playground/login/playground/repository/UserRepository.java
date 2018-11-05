package org.playground.login.playground.repository;


import org.playground.login.playground.utils.SQLUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Repository
public class UserRepository {

    public static String USERS = " users ";


    @Autowired
    private JdbcTemplate jdbcTemplate;

    public boolean checkByUsername (String userName) {
        return getByField("username", userName).size() == 0;
    }

    public boolean checkByEmail(String email) {
        return getByField("email", email).size() == 0;
    }

    private List<Map<String, Object>> getByField (String field, String value) {

        return getByField(field, field, value);
    }

    private List<Map<String, Object>> getByField (String selection, String field, String value) {
        StringBuilder builder = new StringBuilder();
        builder.append(SQLUtils.SELECT)
                .append(selection)
                .append(SQLUtils.FROM)
                .append(USERS)
                .append(SQLUtils.WHERE)
                .append(field)
                .append(SQLUtils.EQUALS)
                .append("%s");
        String sql = String.format(builder.toString(), value);
        return jdbcTemplate.queryForList(sql);
    }

    public void updateLastlogin(String username) {

        StringBuilder builder = new StringBuilder();
        builder.append(SQLUtils.UPDATE)
                .append(USERS)
                .append(SQLUtils.SET)
                .append("last_login")
                .append(SQLUtils.EQUALS)
                .append("DEFAULT")
                .append(SQLUtils.WHERE)
                .append("users.username")
                .append(SQLUtils.EQUALS)
                .append(username);

        jdbcTemplate.execute(builder.toString());

    }

    public RegisteredUser getRegisteredUser(String username) {

        List<Map<String, Object>> rows = getByField("*", "username", username);
        RegisteredUser registeredUser = new RegisteredUser();
        for (Map<String, Object> row : rows) {
            System.out.println(row);
            registeredUser.setId((Integer) row.get("id"));
            registeredUser.setLastLoginDate(((Timestamp) row.get("last_login")).toLocalDateTime());
            registeredUser.setRegisteredDate(((Timestamp) row.get("registered_date")).toLocalDateTime());
            registeredUser.setUsername((String) row.get("username"));
            registeredUser.setEmail((String) row.get("email"));

        }

        return registeredUser;
    }


    public String getUserPassword(String userName) {
        List<Map<String, Object>> rows = getByField("password", "username", userName);
        String password = "";
        for (Map<String, Object> row : rows) {
            System.out.println(row);
            password = (String) row.get("password");
        }

        return password;
    }

    public boolean insertUser(RegisteredUser user) {
        StringBuilder builder = new StringBuilder();
        builder.append(SQLUtils.INSERT_INTO);
        builder.append(USERS);
        builder.append("(email, username, password, registered_date)");
        builder.append(SQLUtils.VALUES + "(?, ?, ?, ?)");
        String sql = builder.toString();
        boolean sucess = true;
        try {
            jdbcTemplate.update(sql, new Object[]{user.getEmail(), user.getUsername(), user.getPassword(), LocalDate.now() });
        } catch (Exception ex) {
            sucess =  false;
        }

        return sucess;

    }

}
