package org.playground.login.playground.repository;



import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Record1;
import org.jooq.Result;
import org.playground.login.playground.error.ApplicatonError;
import org.playground.login.playground.LoginRestService;
import org.playground.login.playground.repository.pojo.RegisteredUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;
import static org.jooq.impl.DSL.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.CompletableFuture;


@Repository
public class UserRepository {

    public static String USERS = " users ";


    @Autowired
    private DSLContext dslContext;

    @Autowired
    private DataService<RegisteredUser> dataService;

    public static final Logger LOGGER = LoggerFactory.getLogger(LoginRestService.class);

    @Async
    public CompletableFuture<Boolean> checkByUsername (String userName) throws Exception {
        return CompletableFuture.completedFuture(dataService.getByField("username", userName, RegisteredUser.class, UserRepository::create).size() == 0);
    }

    @Async
    public CompletableFuture<Boolean> checkByEmail(String email) throws Exception {
        return CompletableFuture.completedFuture(dataService.getByField("email", email, RegisteredUser.class, UserRepository::create).size() == 0);
    }

    public void updateLastlogin(String username) {
        dslContext.update(table(USERS))
                .set(field("last_login"), LocalDateTime.now())
                .where(field("username")
                        .eq(username)).execute();
    }

    public RegisteredUser getRegisteredUser(String username) throws ApplicatonError {

        List<RegisteredUser> users  = dataService.getByField( "username", username, RegisteredUser.class, UserRepository::create);
        if(users.size() < 1) {
            throw new ApplicatonError("","");
        }

        return users.get(0);
    }

    public RegisteredUser getById(int id) throws ApplicatonError {
        List<RegisteredUser> users  = dataService.getById(  id, RegisteredUser.class, UserRepository::create);
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
            LOGGER.error("Failing to insert record: ", ex);
        }
        return false;
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
