package org.playground.login.playground.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class Mapper {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public static <T, R> List<R> map(List<T> rows, Function<T, R> func) {
        List<R> list = new ArrayList<>();
        rows.forEach(stringObjectMap -> list.add(func.apply(stringObjectMap)));
        return list;
    }
}
