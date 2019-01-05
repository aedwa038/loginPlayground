package org.playground.login.playground.repository;

import org.jooq.Record;
import org.playground.login.playground.error.ApplicatonError;
import org.playground.login.playground.repository.pojo.Stuff;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;


@Repository
public class StuffRepository {
    public static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(StuffRepository.class);

    @Autowired
    private DataService<Stuff> dataService;

    public List<Stuff> getByUserId(int user_id) throws ApplicatonError {
        return dataService.getByField("user_id",user_id,Stuff.class,StuffRepository::create );
    }

    public Stuff getById(int id) throws ApplicatonError {
        List<Stuff> stuffs = dataService.getById( id, Stuff.class,StuffRepository::create );
       if(stuffs.isEmpty()) {
           throw new ApplicatonError(ApplicatonError.ErrorCode.BACKEND_ERROR, "","");
       }

       return stuffs.get(0);
    }

    public Integer insert(Stuff stuff) throws Exception{
        return dataService.insert(stuff, Stuff.class);
    }

    public int update(Stuff stuff) throws ApplicatonError{
        return dataService.update(stuff, Stuff.class);
    }


    public static Stuff create(Record row) {
        LOGGER.info(row.toString());
        Stuff stuff = new Stuff();
        stuff.setId((Integer) row.get("id"));
        stuff.setUser_id((Integer)(row.get("user_id")));
        stuff.setData((String) (row.get("data")));
        stuff.setContentType((String)row.get("content_type"));
        return stuff;
    }

}
