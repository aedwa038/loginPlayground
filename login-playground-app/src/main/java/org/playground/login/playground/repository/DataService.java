package org.playground.login.playground.repository;

import org.jooq.*;
import org.jooq.impl.DSL;
import org.jooq.meta.derby.sys.Sys;
import org.playground.login.playground.ApplicatonError;
import org.playground.login.playground.LoginRestService;
import org.playground.login.playground.repository.anonotations.Column;
import org.playground.login.playground.repository.anonotations.Table;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static org.jooq.impl.DSL.*;

@Service
public class DataService <R> {

    @Autowired
    private DSLContext dslContext;

    public static final Logger LOGGER = LoggerFactory.getLogger(DataService.class);

    private List<R> getById (Integer id, Class<R> rClass, Function<Record, R> func) throws ApplicatonError {

        Result<Record> records = dslContext.select(DSL.asterisk())
                .from(table(getTableName(rClass)))
                .where(field("id")
                        .eq(id)).fetch();

        return map(records, func);
    }


    public List<R> getByField (String field, String value, Class<R> rClass, Function<Record, R> func) throws ApplicatonError {
        Result<Record> records = dslContext.select(asterisk())
                .from(table(getTableName(rClass)))
                .where(field(field).eq(param(field, value))).fetch();

        return map(records, func);
    }


    public Result<Record> query(String sql) {
        return dslContext.fetch(sql);
    }

    public List<R> queryForList(String sql, Function<Record,R> func) {
        return map(dslContext.fetch(sql), func);
    }


    public void execute(String sql) {
        dslContext.execute(sql);
    }

    public Result<Record1<Object>> getField (String selection, String field, String value, Class<R> rClass) throws Exception {

        Result<Record1<Object>> records = dslContext.select(field(selection))
                .from(table(getTableName(rClass)))
                .where(field(field)
                        .eq(param(field, value))).fetch();

        return records;
    }



    public boolean insert (R r, Class<R> rClass) throws Exception {
        System.out.println("insert");
        List<org.jooq.Field<Object>> fields = new ArrayList<>();
        List<Object> objects = new ArrayList<>();
        for (Field field : rClass.getDeclaredFields()) {
            if(field.isAnnotationPresent(Column.class)) {
                Column column = field.getAnnotation(Column.class);
                fields.add(field(column.name()));
                Object obj = invokeGetter(field, r);
                if(obj != null) {
                    System.out.println(obj.toString());
                    objects.add(obj);
                }
            }
        }
        boolean sucess = true;
        try {
            dslContext.insertInto(table(getTableName(rClass)))
                    .columns(fields)
                    .values(objects).execute();
        } catch (Exception ex) {
            sucess =  false;
        }

        return sucess;
    }

    public static Object invokeGetter(Field field, Object r) {
        for (Method method : r.getClass().getMethods()) {
            if (method.getName().startsWith("get")&& (method.getName().length() == (field.getName().length() + 3))) {
                if (method.getName().toLowerCase().endsWith(field.getName().toLowerCase()))
                {
                    try
                    {
                        return method.invoke(r);
                    }
                    catch (IllegalAccessException e)
                    {
                        LOGGER.warn("Could not determine method: " + method.getName());
                        LOGGER.error("Error: ", e);
                    }
                    catch (InvocationTargetException e)
                    { ;
                        LOGGER.warn("Could not determine method: " + method.getName());
                        LOGGER.error("Error: ", e);
                    }
                }
            }
        }
        return null;
    }


    public String getTableName(Class<R> rClass) throws ApplicatonError {
        if(!rClass.isAnnotationPresent(Table.class)) {
            throw new ApplicatonError("","");
        }
        return rClass.getAnnotation(Table.class).name();
    }

    public static <R> List<R> map( Result<Record> rows, Function<Record, R> func) {
        List<R> list = new ArrayList<>();
        rows.forEach(stringObjectMap -> list.add(func.apply(stringObjectMap)));
        return list;
    }
}
