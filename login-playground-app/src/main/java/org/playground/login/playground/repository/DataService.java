package org.playground.login.playground.repository;

import org.jooq.*;
import org.jooq.impl.DSL;
import org.playground.login.playground.error.ApplicatonError;
import org.playground.login.playground.repository.anonotations.Column;
import org.playground.login.playground.repository.anonotations.Table;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import static org.jooq.impl.DSL.*;

@Service
public class DataService <R> {

    @Autowired
    private DSLContext dslContext;

    public static final Logger LOGGER = LoggerFactory.getLogger(DataService.class);

    public List<R> getById (Integer id, Class<R> rClass, Function<Record, R> func) throws ApplicatonError {

        Result<Record> records = dslContext.select(DSL.asterisk())
                .from(table(getTableName(rClass)))
                .where(field("id")
                        .eq(id)).fetch();

        return map(records, func);
    }

    public List<R> getByField (String field, Integer id, Class<R> rClass, Function<Record, R> func) throws ApplicatonError {

        Result<Record> records = dslContext.select(DSL.asterisk())
                .from(table(getTableName(rClass)))
                .where(field(field)
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
        LOGGER.info("insert");
        List<org.jooq.Field<Object>> fields = new ArrayList<>();
        List<Object> objects = new ArrayList<>();
        for (Field field : rClass.getDeclaredFields()) {
            if(field.isAnnotationPresent(Column.class)) {
                Column column = field.getAnnotation(Column.class);
                Object obj = invokeGetter(field, r);
                if(obj != null && !field.getName().equals("id")) {
                    System.out.println(field.getName() + " : " +obj.toString());
                    objects.add(obj);
                    fields.add(field(column.name()));
                }
            }
        }
        boolean sucess = true;
        try {
            String sql = dslContext.insertInto(table(getTableName(rClass)))
                    .columns(fields)
                    .values(objects).getSQL();
            LOGGER.info(sql);
            dslContext.insertInto(table(getTableName(rClass)))
                    .columns(fields)
                    .values(objects).execute();
        } catch (Throwable ex) {
            sucess =  false;
            LOGGER.warn("Error inserting record");
            LOGGER.error("Error: ", ex);
        }

        return sucess;
    }

    public static Object invokeGetter(Field field, Object r) {
        Method method = findGetter(field, r);
        return invoke(method, r);
    }


    private static Method findGetter(Field field, Object r) {
       for (Method method : r.getClass().getMethods()) {
           if (method.getName().startsWith("get") && (method.getName().length() == (field.getName().length() + 3))) {
               if (method.getName().toLowerCase().endsWith(field.getName().toLowerCase())) {
                   return method;
               }
           }
       }
       LOGGER.warn("Unable to find method for class" + r.getClass().getSimpleName());
       return null;
    }

    private static Object invoke(Method method, Object r) {
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
        {
            LOGGER.warn("Could not determine method: " + method.getName());
            LOGGER.error("Error: ", e);
        }

        return null;
    }

    public int update(R r, Class<R> rClass) throws ApplicatonError {
        UpdateSetFirstStep updateSetFirstStep =  dslContext.update(table(getTableName(rClass)));
        UpdateSetMoreStep updateSet = null;
        for (Field field : rClass.getDeclaredFields()) {
            if(field.isAnnotationPresent(Column.class)) {
                Column column = field.getAnnotation(Column.class);
                Object obj = invokeGetter(field, r);
                if(obj != null) {
                    System.out.println(obj.toString());
                    updateSet = updateSetFirstStep.set(field(column.name()), obj);
                }
            }
        }
        if(updateSet == null) {
            LOGGER.warn("updateSet is null");
            throw  new ApplicatonError(ApplicatonError.ErrorCode.BACKEND_ERROR, "Error","Backend Error");
        }
        Field primaryKey = findPrimaryKey(r, rClass);
        updateSet.where(field("id").eq(invokeGetter(primaryKey, r)));
        return dslContext.execute(updateSet);

    }

    public Field findPrimaryKey (R r, Class<R> rClass) {
        for (Field field : rClass.getDeclaredFields()) {
            if(field.getName().equals("id")) {
                return field;
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
