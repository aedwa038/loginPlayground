package org.playground.login.playground.repository.pojo;

import org.playground.login.playground.repository.anonotations.Column;
import org.playground.login.playground.repository.anonotations.Table;

@Table(name = "stuff")
public class Stuff {


    @Column(name = "user_id")
    private int user_id;

    @Column(name = "id")
    private int  id;
    @Column(name = "data")
    private String data;

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
