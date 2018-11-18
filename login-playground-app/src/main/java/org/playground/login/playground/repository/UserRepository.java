package org.playground.login.playground.repository;



import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Record1;
import org.jooq.Result;
import org.jooq.meta.derby.sys.Sys;
import org.playground.login.playground.ApplicatonError;
import org.playground.login.playground.LoginRestService;
import org.playground.login.playground.repository.pojo.RegisteredUser;
import org.playground.login.playground.utils.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import static org.jooq.impl.DSL.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Repository
public class UserRepository {

    public static String USERS = " users ";


    @Autowired
    private DSLContext dslContext;

    @Autowired
    private DataService<RegisteredUser> dataService;

    public static final Logger LOGGER = LoggerFactory.getLogger(LoginRestService.class);


    public boolean checkByUsername (String userName) throws Exception {
        return dataService.getByField("username", userName, RegisteredUser.class, RegisteredUser::create).size() == 0;
    }

    public boolean checkByEmail(String email) throws Exception {
        return dataService.getByField("email", email, RegisteredUser.class, RegisteredUser::create).size() == 0;
    }



    public void updateLastlogin(String username) {
        dslContext.update(table(USERS))
                .set(field("last_login"), "DEFAULT")
                .where(field("username")
                        .eq(username)).execute();
    }

    public RegisteredUser getRegisteredUser(String username) throws ApplicatonError {

        List<RegisteredUser> users  = dataService.getByField( "username", username, RegisteredUser.class, RegisteredUser::create);
        if(users.size() < 1) {
            throw new ApplicatonError("","");
        }

        return users.get(0);
    }

    public String getUserPassword(String userName) throws Exception {
        Result<Record1<Object>> rows = dataService.getField("password", "username", userName, RegisteredUser.class);
        final StringBuilder password = new StringBuilder("");
        rows.stream().forEach(stringObjectMap -> password.append(stringObjectMap.get("password")));
        System.out.println(password);
        return password.toString();
    }

    public boolean insertUser(RegisteredUser user) {
        try {
           return dataService.insert(user, RegisteredUser.class);
        } catch (Exception ex) {

        }
        return false;
    }

}
