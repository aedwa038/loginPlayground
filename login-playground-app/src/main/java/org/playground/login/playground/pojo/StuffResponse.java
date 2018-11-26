package org.playground.login.playground.pojo;

import org.playground.login.playground.repository.pojo.Stuff;

import java.util.ArrayList;
import java.util.List;

public class StuffResponse {

    List<Stuff> stuffList = new ArrayList<>();

    public StuffResponse(List<Stuff> stuffList) {
        this.stuffList = stuffList;
    }

    public List<Stuff> getStuffList() {
        return stuffList;
    }

    public void setStuffList(List<Stuff> stuffList) {
        this.stuffList = stuffList;
    }
}
